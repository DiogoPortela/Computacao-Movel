package ipca.hrem.com.BasicResources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Map {

    public static final float CELL_SCALE = 1.0f;
    //-------------------------Variables-------------------------//
    public static Grid fullMap;                 //To use only one instance of cell this grid must contain all cells.
    public static ArrayList<Grid> gridList;     //This will hold all grids that there are on a map. Should not be drawn.

    //-------------------------Constructor-------------------------//
    public Map(int Width, int Height) {
        fullMap = new Grid(Grid.GridType.wholeMap, Width, Height, Point.Zero);
        gridList = new ArrayList<Grid>();
    }

    //-------------------------Functions-------------------------//
    public void render(SpriteBatch batch){
        fullMap.render(batch);
    }

    public void dispose(){
        gridList.clear();
        fullMap.dispose();
    }
}
