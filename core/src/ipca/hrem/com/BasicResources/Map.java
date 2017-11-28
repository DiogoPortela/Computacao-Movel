package ipca.hrem.com.BasicResources;

import java.util.ArrayList;

public class Map {

    //To use only one instance of cell this grid must contain all cells.
    public static Grid fullMap;
    public static ArrayList<Grid> gridList;

    public Map(int Width, int Height) {
        fullMap = new Grid(Grid.GridType.wholeMap, Width, Height, Point.Zero);
    }
}
