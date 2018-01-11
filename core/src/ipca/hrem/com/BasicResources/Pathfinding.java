package ipca.hrem.com.BasicResources;

// Simple pathfinding algorithm with support for tile prices
// Main class to find the best path to walk from A to B.
// Usage example:
// Grid grid = new Grid(width, height, tiles_costs);
// List<Point> path = Pathfinding.FindPath(grid, from, to);


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class Pathfinding {
    // Find a path between two points.
    //boolean ignorePrices = false;
    public static ArrayList<Point> FindPath(GraphGrid graphGrid, Point startPos, Point targetPos, boolean ignorePrices ){
        // Find path
        ArrayList<Node> nodes_path = ImpFindPath(graphGrid, startPos, targetPos, ignorePrices);

        // Convert to a list of points and return
        ArrayList<Point> ret = new ArrayList<Point>();
        if (nodes_path != null)
        {
            for (Node node : nodes_path ){
                ret.add(new Point(node.gridX, node.gridY));
            }
        }

        return ret;
    }

    // Internal function that implements the path-finding algorithm.
    // graphgrid - Graphgrid to search
    // startPos - Starting position
    // targetPos - Ending position
    // ignorePrices - If true, will ignore tile price (cost to walk on)
    private static ArrayList<Node> ImpFindPath(GraphGrid graphGrid, Point startPos, Point targetPos, boolean ignorePrices)
    {
        Node startNode = graphGrid.nodes[startPos.X][startPos.Y];
        Node targetNode = graphGrid.nodes[targetPos.X][targetPos.Y];

        ArrayList<Node> openSet = new ArrayList<Node>();
        HashSet<Node> closedSet = new HashSet<Node>();
        openSet.add(startNode);

        while (openSet.size() > 0) {
            Node currentNode = openSet.get(0);
            for (int i = 1; i < openSet.size(); i++) {
                if (openSet.get(i).fCost() < currentNode.fCost() || openSet.get(i).fCost() == currentNode.fCost() && openSet.get(i).hCost < currentNode.hCost) {
                    currentNode = openSet.get(i);
                }
            }
            openSet.remove(currentNode);
            closedSet.add(currentNode);

            if (currentNode == targetNode) {
                return RetracePath(graphGrid, startNode, targetNode);
            }

            for (Node neighbor : graphGrid.GetNeighbors(currentNode)) {
                if (!neighbor.walkable || closedSet.contains(neighbor)) {
                    continue;
                }

                int newMovementCostToNeighbour = currentNode.gCost + GetDistance(currentNode, neighbor) * (ignorePrices ? 1 : (int) (10.f * neighbor.price));
                if (newMovementCostToNeighbour < neighbor.gCost || !openSet.contains(neighbor)) {
                    neighbor.gCost = newMovementCostToNeighbour;
                    neighbor.hCost = GetDistance(neighbor, targetNode);
                    neighbor.parent = currentNode;

                    if (!openSet.contains(neighbor))
                    {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null;
    }

        // Retrace path between two points
        // graphgrid - Graphgrid to search
        // startNode - Starting position
        // endNode - Ending position
        private static ArrayList<Node> RetracePath(GraphGrid graphGrid, Node startNode, Node endNode)
        {
            ArrayList<Node> path = new ArrayList<Node>();
            Node currentNode = endNode;

            while (currentNode != startNode)
            {
                path.add(currentNode);
                currentNode = currentNode.parent;
            }
            Collections.reverse(path);
            return path;
        }

        // Get distance between two nodes
        // nodeA - First node
        // nodeB - Second node
        private static int GetDistance(Node nodeA, Node nodeB)
        {
            return GetHeuristic(nodeA,nodeB);
//            int dstX = Math.abs(nodeA.gridX - nodeB.gridX);
//            int dstY = Math.abs(nodeA.gridY - nodeB.gridY);
//
//            if (dstX > dstY)
//            {
//                return 14 * dstY + 10 * (dstX - dstY);
//            }
//            return 14 * dstX +10 * (dstY - dstX);
        }
        //Heuristics
        private static int GetHeuristic(Node nodeA, Node nodeB){
            //Diagonal Movement Heuristic, permits movement in 8 different directions
            //Since Diagonal movement has the same cost as normal movement this distance is known as Chebyshev Distance
            //Loosely described as the normal movement a king can do in a chess game
            int D = 1, D2 = 1;
            int dx = Math.abs(nodeA.gridX - nodeB.gridX);
            int dy = Math.abs(nodeA.gridY - nodeB.gridY);
            return D * (dx + dy) + (D2 - 2 * D) * Math.min(dx, dy);
        }
    }

