package ipca.hrem.com.BasicResources;

// Represent a grid of nodes we can search paths on.
// A 2D grid of nodes we pathfind
// The grid marks which tiles are walkable and which are not

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GraphGrid {
    Node[][] nodes = new Node[1][];

    int gridSizeX, gridSizeY;

    // Create a new grid with tile prices
    // width - graphgrid width
    // height - graphgrid height
    // tiles_costs - a 2d array, matching width and height, of tile prices.
    // 0.0f = Unwalkable tile || 1.0f = Walkable normal tile
    // > 1.0f = costly tile || < 1.0f = cheap tile
    // public GraphGrid(int width, int height, float[][] tiles_costs ){
    //     gridSizeX = width;
    //     gridSizeY = height;
    //     nodes = new Node[width][height];
    //
    // for (int x = 0; x < width; x++){
    //         for (int y = 0; y <height; y++)
    //        {
    //             nodes[x, y] = new Node(tiles_costs[x, y], x, y);
    //         }
    //     }
    // }

    // Create a new graphgrid without tile prices, just walkable and unwalkable tiles
    // width - graphgrid width
    // height - graphgrid height
    // tiles_costs - a 2d array, matching width and height, which tiles are walkable and which are not
    public GraphGrid(int width, int height, boolean[][] walkable_tiles){
        gridSizeX = width;
        gridSizeY = height;
        nodes = new Node[width][height];

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                nodes[x][y] = new Node(walkable_tiles[x][y] ? true : false, x, y);
            }
        }
    }

    // Get all the neighbors of a given tile in the grid
    // node - Node to get neighbors for
    public List<Node> GetNeighbors(Node node){
        List<Node> neighbors = new ArrayList<Node>();

        for (int x = -1; x <= 1; x++){
            for (int y = -1; y <= 1; y++){
                if (x == 0 && y == 0)
                    continue;

                int checkX = node.gridX + x;
                int checkY = node.gridY + y;

                if (checkX >= 0 && checkX < gridSizeX && checkY >= 0 && checkY < gridSizeY)
                {
                    neighbors.add(nodes[checkX][checkY]);
                }
            }
        }

        return neighbors;
    }

}
