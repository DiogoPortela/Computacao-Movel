package ipca.hrem.com.BasicResources;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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

    //-------------------------GetSetters-------------------------//
    public GridType getGridType() {
        return gridType;
    }

    //-------------------------Constructor-------------------------//
    public Grid(GridType gridType, int gridWidth, int gridHeight, Point start) {
        this.gridType = gridType;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        cells = new GridCell[gridWidth][gridHeight];

        if(gridType != GridType.wholeMap) {
            for (int i = 0; i < gridWidth; i++) {
                for (int j = 0; j < gridHeight; j++) {
                    cells[i][j] = Map.fullMap.cells[start.X + i][start.Y + j];
                }
            }
        }
        else{
            generateMap();
        }
    }

    //-------------------------Functions-------------------------//
    public void generateMap(){          //Generates firstMap
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                cells[i][j] = new GridCell(GridCell.CellType.test, new Vector2(i,j), Map.CELL_SCALE);
            }
        }
    }
    public void render(SpriteBatch batch){
        for (GridCell[] row: cells) {
            for (GridCell cell: row) {
                cell.render(batch);
            }
        }
    }

    public void dispose(){
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                cells[i][j].dispose();
            }
        }
        gridType = null;
    }
}
