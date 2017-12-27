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
    private int gridHeight;
    private int gridWidth;
    private ArrayList<GridCell> wallList;

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
                cell.setCellType(cellType);
            }
        }
    }

    //-------------------------Constructor-------------------------//
    public Grid(GridType gridType, int gridWidth, int gridHeight, Point start) {
        this.gridType = gridType;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        cells = new GridCell[gridWidth][gridHeight];

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

        int yDirection = -1;
        int xDirection = -1;
        if (startPosition.y - endPosition.y < 0)
            yDirection = 1;
        if (startPosition.x - endPosition.x < 0)
            xDirection = 1;

        startPoint.X = MathUtils.clamp(startPoint.X, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridWidth());
        startPoint.Y = MathUtils.clamp(startPoint.Y, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridHeight());
        endPoint.X = MathUtils.clamp(endPoint.X, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridWidth());
        endPoint.Y = MathUtils.clamp(endPoint.Y, 1, MainGame.currentPlayer.currentMap.getFullMap().getGridHeight());

        gridWidth = Math.abs(startPoint.X - endPoint.X) + 1;
        gridHeight = Math.abs(startPoint.Y - endPoint.Y) + 1;


        cells = new GridCell[gridWidth][gridHeight];
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                cells[i][j] = MainGame.currentPlayer.currentMap.getTile(new Point(startPoint.X + i * xDirection, startPoint.Y + j * yDirection));
            }
        }
        if (gridType == GridType.interior) {
            for (GridCell[] row : cells) {
                for (GridCell cell : row) {
                    cell.setIsInterior(true);
                }
            }
        }
    }

    public Grid(Grid one, Grid two) {
        //merge grids;
    }

    //-------------------------Functions-------------------------//
    public void generateMap() {          //Generates firstMap
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                cells[i][j] = new GridCell(GridCell.CellType.grass, new Vector2(i, j), Map.CELL_SCALE);
            }
        }
    }

    public ArrayList<GridCell> findWalls() {
        wallList = new ArrayList<GridCell>();

        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                if (i == 0 || j == 0 || i == gridWidth || j == gridHeight && cells[i][j] != null)
                    wallList.add(cells[i][j]);
                else if (cells[i + 1][j] == null || cells[i][j + 1] == null || cells[i - 1][j] == null || cells[i][j - 1] == null)
                    wallList.add(cells[i][j]);
            }
        }
        return wallList;
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
