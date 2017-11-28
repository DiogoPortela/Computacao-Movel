package ipca.hrem.com.BasicResources;



// Class that holds a group of cells as a grid.
public class Grid {
    public enum GridType {              //ENUMERATOR FOR ALL GRID TYPES
        wholeMap, interior, exterior,
    }

    public GridType gridType;
    protected GridCell[][] cells;
    private int gridHeight;
    private int gridWidth;


    public Grid(GridType gridType, int gridWidth, int gridHeight, Point start) {
        this.gridType = gridType;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        cells = new GridCell[gridWidth][gridHeight];

        for (int i = 0 ; i < gridWidth; i++){
            for(int j = 0; j < gridHeight; j++){
                cells[i][j] = Map.fullMap.cells[start.getX() + i][start.getY() + j];
            }
        }
    }
}
