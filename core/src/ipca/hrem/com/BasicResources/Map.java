package ipca.hrem.com.BasicResources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import ipca.hrem.com.ObjectResources.GridCell;

public class Map {
    public static final float CELL_SCALE = 1.0f;

    //-------------------------Variables-------------------------//
    private Grid fullMap;                 //To use only one instance of cell this grid must contain all cells.
    private ArrayList<Grid> gridList;     //This will hold all grids that there are on a map. Should not be drawn.

    //-------------------------GetSetters-------------------------//
    public Grid getFullMap() {
        return fullMap;
    }

    public void setFullMap(Grid fullMap) {
        this.fullMap = fullMap;
    }

    public ArrayList<Grid> getGridList() {
        return gridList;
    }

    public void setGridList(ArrayList<Grid> gridList) {
        this.gridList = gridList;
    }

    public void setGridCell(Point tileCoordinates, GridCell.CellType cellType) {
        fullMap.cells[tileCoordinates.X][tileCoordinates.Y].setCellType(cellType);
    }

    public GridCell getGridCell(Point position) {
        if (position.X < fullMap.getGridWidth() && position.X >= 0 && position.Y < fullMap.getGridHeight() && position.Y >= 0)
            return fullMap.cells[position.X][position.Y];
        return null;
    }

    //-------------------------Constructor-------------------------//
    public Map(int Width, int Height) {
        fullMap = new Grid(Grid.GridType.wholeMap, Width, Height, Point.Zero);
        gridList = new ArrayList<Grid>();
    }

    //-------------------------Functions-------------------------//
    public void render(SpriteBatch batch) {
        fullMap.render(batch);
    }

    public void dispose() {
        gridList.clear();
        fullMap.dispose();
    }
}
