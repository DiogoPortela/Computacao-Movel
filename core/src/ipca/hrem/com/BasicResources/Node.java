package ipca.hrem.com.BasicResources;

//A single node in the Pathfinding grid

public class Node {

    // Walkable node???
    public boolean walkable;
    public int gridX;
    public int gridY;
    public float price;

    // calculated values while finding path
    public int gCost;
    public int hCost;
    public Node parent;

    // Create the grid node
    // Price - Price to walk on this node (set to 1.0f to ignore)
    // _gridX - Node x
    // _gridY - Node y
     public Node(float _price, int _gridX, int _gridY)
     {
        walkable = _price != 0.0f;
        price = _price;
        gridX = _gridX;
        gridY = _gridY;
     }

    // Create the grid node
    // _walkable - Is this tile walkable?
    // _gridX - Node x
    // _gridY - Node y
    public Node(boolean _walkable, int _gridX, int _gridY)
    {
        walkable = _walkable;
        price = _walkable ? 1f : 0f;
        gridX = _gridX;
        gridY = _gridY;
    }

    // Get fCost of this node
    public int fCost() {
        return gCost + hCost;
    }
}
