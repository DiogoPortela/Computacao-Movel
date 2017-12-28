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

        startPoint.X = MathUtils.clamp(startPoint.X, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridWidth());
        startPoint.Y = MathUtils.clamp(startPoint.Y, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridHeight());
        endPoint.X = MathUtils.clamp(endPoint.X, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridWidth());
        endPoint.Y = MathUtils.clamp(endPoint.Y, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridHeight());

        gridWidth = Math.abs(startPoint.X - endPoint.X) + 1;
        gridHeight = Math.abs(startPoint.Y - endPoint.Y) + 1;


        cells = new GridCell[gridWidth][gridHeight];
        wallList = new ArrayList<GridCell>();
        cornerList = new ArrayList<GridCell>();
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                cells[i][j] = MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X + i * xDirection, startPoint.Y + j * yDirection));
                if(i == 0){
                    wallList.add(MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X - 1 * xDirection, startPoint.Y + j * yDirection)));
                    if(j == 0)
                        cornerList.add(MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X - 1 * xDirection, startPoint.Y - 1 * yDirection)));
                    else if(j == gridHeight - 1)
                        cornerList.add(MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X  - 1 * xDirection, startPoint.Y + gridHeight * yDirection)));
                } else if (i == gridWidth - 1){
                    wallList.add(MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X + gridWidth * xDirection, startPoint.Y + j * yDirection)));
                    if(j == 0)
                        cornerList.add(MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X + gridWidth * xDirection, startPoint.Y - 1 * yDirection)));
                    else if(j == gridHeight - 1)
                        cornerList.add(MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X + gridWidth * xDirection, startPoint.Y + gridHeight * yDirection)));
                }
                if(j == 0){
                    wallList.add(MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X + i * xDirection, startPoint.Y - 1 * yDirection)));
                } else if(j == gridHeight - 1)
                    wallList.add(MainGame.currentPlayer.currentMap.getGridCell(new Point(startPoint.X + i * xDirection, startPoint.Y + gridHeight * yDirection)));
            }
        }

        ArrayList<Grid> otherGrids = getIntersectingGrids();
        if (otherGrids.isEmpty()) {
            for (GridCell[] row : cells) {
                for (GridCell cell : row) {
                    cell.setParentGrid(this);
                }
            }
        } else {
            updateCells(otherGrids);
        }

        if (gridType == GridType.interior) {
            for (GridCell[] row : cells) {
                for (GridCell cell : row) {
                    cell.setIsInterior(true);
                }
            }
        }
    }

    //-------------------------Functions-------------------------//
    public void generateMap() {          //Generates firstMap
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                cells[i][j] = new GridCell(GridCell.CellType.grass, new Vector2(i, j), Map.CELL_SCALE);
            }
        }
    }

    public void findWalls() {
        wallList = new ArrayList<GridCell>();
        cornerList = new ArrayList<GridCell>();

        for(int i = 0; i < gridWidth; i++){
            for(int j = 0; j < gridHeight; j++){
                if(cells[i][j] != null){
                    Point coordinates = Point.fromVector2(cells[i][j].getPosition());
                    int counter = 0;
                    for(int ii = -1; ii < 2; ii++){
                        for(int jj = -1; jj <2; jj++){
                            if(jj == 0 && ii == 0)
                                continue;
                            else if(!containsGridCell(MainGame.currentPlayer.currentMap.getGridCell(new Point(coordinates.X + ii, coordinates.Y + jj))))
                                counter++;
                        }
                    }

                    if(counter == 3 || counter == 2)
                        wallList.add(cells[i][j]);
                    else if(counter == 1 || counter == 4 || counter == 5)
                        cornerList.add(cells[i][j]);
                }
            }
        }
    }

    public boolean containsGridCell(GridCell cell){
        for (GridCell[] row: cells) {
            for(GridCell c: row){
                if(c == cell)
                    return true;
            }
        }
        return false;
    }

    private void updateCells(ArrayList<Grid> collidingGrids) {
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
        }

        findWalls();

        for (GridCell[] row : cells) {
            for (GridCell cell : row) {
                if (cell != null)
                    cell.setParentGrid(this);
            }
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
        for(GridCell cell : wallList) {
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
