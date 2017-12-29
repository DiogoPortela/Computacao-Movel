package ipca.hrem.com.BasicResources;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.GridCell;

// Class that holds a group of cells as a grid.
public class Grid {
    public enum GridType {              //ENUMERATOR FOR ALL GRID TYPES
        wholeMap, interior, exterior,
    }

    //-------------------------Variables-------------------------//
    public GridType gridType;
    protected GridCell[][] cells;
    private Point coordinates;
    private int gridHeight;
    private int gridWidth;
    private ArrayList<GridCell> wallList;
    private ArrayList<GridCell> cornerList;

    //-------------------------GetSetters-------------------------//
    public GridType getGridType() {
        return gridType;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public ArrayList<GridCell> getWallList() {
        return wallList;
    }

    public void setCellTypeForAll(GridCell.CellType cellType) {
        for (GridCell[] row : cells) {
            for (GridCell cell : row) {
                if (cell != null)
                    cell.setCellType(cellType);
            }
        }
    }

    //-------------------------Constructor-------------------------//
    public Grid(GridType gridType, int gridWidth, int gridHeight, Point start) {
        this.gridType = gridType;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        this.cells = new GridCell[gridWidth][gridHeight];
        this.wallList = null;
        this.cornerList = null;

        this.coordinates = start;

        if (gridType != GridType.wholeMap) {
            for (int i = 0; i < gridWidth; i++) {
                for (int j = 0; j < gridHeight; j++) {
                    cells[i][j] = MainGame.currentPlayer.currentMap.getFullMap().cells[start.X + i][start.Y + j];
                }
            }
        } else {
            generateMap();
        }
    }

    public Grid(GridType gridType, Vector2 startPosition, Vector2 endPosition) {
        this.gridType = gridType;

        Point startPoint = Point.fromVector2(startPosition);
        Point endPoint = Point.fromVector2(endPosition);

        this.coordinates = endPoint;

        int yDirection = -1;
        int xDirection = -1;
        if (startPosition.y - endPosition.y < 0) {
            yDirection = 1;
            coordinates = new Point(coordinates.X, startPoint.Y);
        }
        if (startPosition.x - endPosition.x < 0) {
            xDirection = 1;
            coordinates = new Point(startPoint.X, coordinates.Y);
        }

        startPoint.X = MathUtils.clamp(startPoint.X, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridWidth() - 2);
        startPoint.Y = MathUtils.clamp(startPoint.Y, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridHeight() - 2);
        endPoint.X = MathUtils.clamp(endPoint.X, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridWidth() - 2);
        endPoint.Y = MathUtils.clamp(endPoint.Y, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridHeight() - 2);

        gridWidth = Math.abs(startPoint.X - endPoint.X) + 1;
        gridHeight = Math.abs(startPoint.Y - endPoint.Y) + 1;

        cells = new GridCell[gridWidth][gridHeight];
        wallList = new ArrayList<GridCell>();
        cornerList = new ArrayList<GridCell>();

        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                cells[i][j] = MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X + i * xDirection, startPoint.Y + j * yDirection));
            }
        }

        if (gridType == GridType.interior) {
            updateWallInterior();
        }

        calculateExternalWallsOnArea(coordinates, new Point(coordinates.X + gridWidth, coordinates.Y + gridHeight));

        ArrayList<Grid> otherGrids = getIntersectingGrids();
        if (!otherGrids.isEmpty()) {
            updateCells(otherGrids);
        }

        updateParents();
        updateWallSprites();
    }

    public Grid(int array[][], int arrayWidth, int arrayHeight, Point position) {
        this.gridType = GridType.interior;
        this.cells = new GridCell[arrayWidth][arrayHeight];
        this.gridWidth = arrayWidth;
        this.gridHeight = arrayHeight;
        this.coordinates = position;

        for (int i = 0; i < arrayWidth; i++) {
            for (int j = 0; j < arrayHeight; j++) {
                cells[i][j] = MainGame.currentPlayer.currentMap.getGridCell(new Point(position.X + i, position.Y + j));
                cells[i][j].setParentGrid(this);
                cells[i][j].setIsInterior(true);
            }
        }

        wallList = new ArrayList<GridCell>();
        cornerList = new ArrayList<GridCell>();

        for (int i = 0; i < arrayWidth; i++) {
            for (int j = 0, jj = arrayHeight - 1; j < arrayHeight; j++, jj--) {
                switch (array[jj][i]) {
                    case 0:
                        cells[i][j].clearParentGrid();
                        cells[i][j].setIsInterior(false);
                        cells[i][j] = null;
                        break;
                    case 1:
                        cells[i][j].setCellType(GridCell.CellType.wallInterior);
                        wallList.add(cells[i][j]);
                        break;
                    case 2:
                        cells[i][j].setCellType(GridCell.CellType.woodenFloor);
                        break;
                    case 3:
                        cells[i][j].setCellType(GridCell.CellType.kitchenFloor);
                        break;
                    case 4:
                        cells[i][j].setCellType(GridCell.CellType.bathroomFloor);
                        break;
                }
            }
        }
        calculateCorners();
        updateWallSprites();
    }

    //-------------------------Functions-------------------------//
    public void generateMap() {          //Generates firstMap
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                cells[i][j] = new GridCell(GridCell.CellType.grass, new Vector2(i, j), Map.CELL_SCALE);
            }
        }
    }

    private void calculateCorners() {
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                if (cells[i][j] != null) {
                    int counter = 0;
                    for (int ii = -1; ii < 2; ii++) {
                        for (int jj = -1; jj < 2; jj++) {
                            if (jj == 0 && ii == 0)
                                continue;
                            else if (!containsGridCell(MainGame.currentPlayer.currentMap.getGridCell(new Point(this.coordinates.X + i + ii, this.coordinates.Y + j + jj))))
                                counter++;
                        }
                    }
                    if (counter == 1 || counter == 4 || counter == 5) {
                        if (!cornerList.contains(cells[i][j]))
                            cornerList.add(cells[i][j]);
                        if (wallList.contains(cells[i][j]))
                            wallList.remove(cells[i][j]);
                    }
                }
            }
        }
    }

    public void calculateExternalWallsOnArea(Point areaMin, Point areaMax) {
        for (int i = areaMin.X - coordinates.X; i < areaMax.X - coordinates.X; i++) {
            for (int j = areaMin.Y - coordinates.Y; j < areaMax.Y - coordinates.Y; j++) {
                if (cells[i][j] != null) {
                    int counter = 0;
                    for (int ii = -1; ii < 2; ii++) {
                        for (int jj = -1; jj < 2; jj++) {
                            if (jj == 0 && ii == 0)
                                continue;
                            else if (!containsGridCell(MainGame.currentPlayer.currentMap.getGridCell(new Point(this.coordinates.X + i + ii, this.coordinates.Y + j + jj))))
                                //else if (MainGame.currentPlayer.currentMap.getGridCell(new Point(this.coordinates.X + i + ii, this.coordinates.Y + j + jj)) != null &&
                                //        !MainGame.currentPlayer.currentMap.getGridCell(new Point(this.coordinates.X + i + ii, this.coordinates.Y + j + jj)).getIsInterior())
                                counter++;
                        }
                    }

                    if (counter == 3 || counter == 2) {
                        if (!wallList.contains(cells[i][j]))
                            wallList.add(cells[i][j]);
                        if (cornerList.contains(cells[i][j]))
                            cornerList.remove(cells[i][j]);
                    } else if (counter == 1 || counter == 4 || counter == 5) {
                        if (!cornerList.contains(cells[i][j]))
                            cornerList.add(cells[i][j]);
                        if (wallList.contains(cells[i][j]))
                            wallList.remove(cells[i][j]);
                    } else {
                        if (wallList.contains(cells[i][j])) {
                            if (cells[i][j].getCellType() == GridCell.CellType.wallInterior)
                                cells[i][j].setCellType(GridCell.CellType.justBuilt);
                            wallList.remove(cells[i][j]);
                        }
                        if (cornerList.contains(cells[i][j])) {
                            if (cells[i][j].getCellType() == GridCell.CellType.wallInterior)
                                cells[i][j].setCellType(GridCell.CellType.justBuilt);
                            cornerList.remove(cells[i][j]);
                        }
                    }
                }
            }
        }
    }

    private void updateWallSprites() {
        ArrayList<GridCell> needABottom = new ArrayList<GridCell>();

        for (GridCell wall : wallList) {
            wall.setCellType(GridCell.CellType.wallInterior);
        }
        for (GridCell corner : cornerList) {
            corner.setCellType(GridCell.CellType.wallInterior);
        }

        for (GridCell wall : wallList) {
            boolean array[][] = checkSurroundings((int) wall.getPosition().x, (int) wall.getPosition().y, GridCell.CellType.wallInterior);

            if (!array[1][0])
                needABottom.add(wall);

            if (!array[1][2]) {
                if (array[0][1] && array[2][1]) {
                    wall.setRegion(416, 64, 32, 32);
                } else if (array[0][1]) {
                    wall.setRegion(448, 64, 32, 32);
                } else if (array[2][1]) {
                    wall.setRegion(384, 64, 32, 32);
                } else  {
                    wall.setRegion(480, 64, 32, 32);
                }
            } else{
                if (array[0][1] && array[2][1]) {
                    wall.setRegion(416, 96, 32, 32);
                } else if (!array[0][1] && array[2][1]) {
                    wall.setRegion(384, 96, 32, 32);
                } else if (array[0][1]) {
                    wall.setRegion(448, 96, 32, 32);
                } else{
                    if (array[0][2] && array[2][2]) {
                        wall.setRegion(416, 0, 32, 32);
                    } else if (!array[0][2] && array[2][2]) {
                        wall.setRegion(384, 0, 32, 32);
                    } else if (array[0][2]) {
                        wall.setRegion(448, 0, 32, 32);
                    } else{
                        wall.setRegion(480, 96, 32, 32);
                    }
                }
            }
        }
        for (GridCell corner : cornerList) {

            boolean array[][] = checkSurroundings((int) corner.getPosition().x, (int) corner.getPosition().y, GridCell.CellType.wallInterior);

            if (!array[1][0])
                needABottom.add(corner);

            if (!array[1][2]) {
                if (array[0][1] && array[2][1]) {
                    corner.setRegion(416, 64, 32, 32);
                } else if (array[0][1]) {
                    corner.setRegion(448, 64, 32, 32);
                } else if (array[2][1]) {
                    corner.setRegion(384, 64, 32, 32);
                }
            } else{
                if (array[0][1] && array[2][1]) {
                    corner.setRegion(416, 32, 32, 32);
                } else if (array[0][1]) {
                    corner.setRegion(448, 32, 32, 32);
                } else if (array[2][1]) {
                    corner.setRegion(384, 32, 32, 32);
                }
            }
        }

        for (GridCell wallInterior: needABottom) {
            GridCell wall = MainGame.currentPlayer.currentMap.getGridCell(new Point((int)wallInterior.getPosition().x,(int)wallInterior.getPosition().y - 1));
                wall.setCellType(GridCell.CellType.wall);
            boolean array[][] = checkSurroundings((int) wall.getPosition().x, (int) wall.getPosition().y, GridCell.CellType.wallInterior);

            if(array[0][2] && array[2][2]){
                wall.setRegion(416, 128, 32, 32);
            }else if(array[0][2]){
                wall.setRegion(448, 128, 32, 32);
            }else if(array[2][2]){
                wall.setRegion(384, 128, 32, 32);
            }else{
                wall.setRegion(480, 128, 32, 32);
            }
        }
    }

    private boolean[][] checkSurroundings(int x, int y, GridCell.CellType cellType) {
        boolean[][] array = new boolean[3][3];
        for (boolean[] row : array)
            for (boolean b : row)
                b = false;

        for (int ii = -1; ii < 2; ii++) {
            for (int jj = -1; jj < 2; jj++) {
                if (jj == 0 && ii == 0)
                    continue;
                else if (MainGame.currentPlayer.currentMap.getGridCell(new Point(x + ii, y + jj)) != null &&
                        MainGame.currentPlayer.currentMap.getGridCell(new Point(x + ii, y + jj)).getCellType() == cellType)
                    array[ii + 1][jj + 1] = true;
            }
        }
        return array;
    }

    private void updateWallInterior() {
        for (GridCell[] row : cells) {
            for (GridCell cell : row) {
                if (cell != null && !cell.getIsInterior()) {
                    cell.setIsInterior(true);
                    cell.setCellType(GridCell.CellType.justBuilt);
                }
            }
        }
    }

    private void updateParents() {
        for (GridCell[] row : cells) {
            for (GridCell cell : row) {
                if (cell != null)
                    cell.setParentGrid(this);
            }
        }
    }

    public boolean containsGridCell(GridCell cell) {
        if (cell != null) {
            for (GridCell[] row : cells) {
                for (GridCell c : row) {
                    if (c == cell)
                        return true;
                }
            }
        }
        return false;
    }

    private void updateCells(ArrayList<Grid> collidingGrids) {
        Point minIntersect = this.coordinates;
        Point maxIntersect = new Point(this.coordinates.X + gridWidth, this.coordinates.Y + gridHeight);

        for (Grid grid : collidingGrids) {
            int thisGridMaxX = gridWidth + coordinates.X;
            int thisGridMaxY = gridHeight + coordinates.Y;
            int otherGridMaxX = grid.getGridWidth() + grid.coordinates.X;
            int otherGridMaxY = grid.getGridHeight() + grid.coordinates.Y;
            int newMaxX = (thisGridMaxX > otherGridMaxX) ? thisGridMaxX : otherGridMaxX;
            int newMaxY = (thisGridMaxY > otherGridMaxY) ? thisGridMaxY : otherGridMaxY;
            int newMinX = (coordinates.X < grid.coordinates.X) ? coordinates.X : grid.coordinates.X;
            int newMinY = (coordinates.Y < grid.coordinates.Y) ? coordinates.Y : grid.coordinates.Y;

            this.coordinates = new Point(newMinX, newMinY);
            this.gridWidth = newMaxX - newMinX;
            this.gridHeight = newMaxY - newMinY;
            GridCell[][] newArray = new GridCell[gridWidth][gridHeight];
            for (GridCell[] row : cells) {
                for (GridCell cell : row) {
                    if (cell != null) {
                        int x = (int) cell.getPosition().x - newMinX;
                        int y = (int) cell.getPosition().y - newMinY;
                        newArray[x][y] = cell;
                    }
                }
            }
            for (GridCell[] row : grid.cells) {
                for (GridCell cell : row) {
                    if (cell != null) {
                        int x = (int) cell.getPosition().x - newMinX;
                        int y = (int) cell.getPosition().y - newMinY;
                        newArray[x][y] = cell;
                    }
                }
            }
            this.cells = newArray;
            for (GridCell cell : grid.wallList) {
                if (!wallList.contains(cell))
                    wallList.add(cell);
            }
            for (GridCell cell : grid.cornerList) {
                if (!cornerList.contains(cell))
                    cornerList.add(cell);
            }
            calculateExternalWallsOnArea(minIntersect, maxIntersect);
        }
    }

    private ArrayList<Grid> getIntersectingGrids() {
        ArrayList<Grid> result = new ArrayList<Grid>();
        for (GridCell[] row : cells) {
            for (GridCell cell : row) {
                if (cell.getParentGrid() != null && !result.contains(cell.getParentGrid()))
                    result.add(cell.getParentGrid());
            }
        }
        for (GridCell cell : wallList) {
            if (cell.getParentGrid() != null && !result.contains(cell.getParentGrid()))
                result.add(cell.getParentGrid());
        }
        return result;
    }

    public void render(SpriteBatch batch) {
        for (GridCell[] row : cells) {
            for (GridCell cell : row) {
                cell.render(batch);
            }
        }
    }

    public void dispose() {
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                cells[i][j].dispose();
            }
        }
        gridType = null;
    }
}
