package ipca.hrem.com.BasicResources;


import com.badlogic.gdx.math.Vector2;

//Vector2 but whit ints.
public class Point {
    private int X;
    private int Y;

    public int getX() {
        return X;
    }
    public void setX(int x) {
        X = x;
    }
    public int getY() {
        return Y;
    }
    public void setY(int y) {
        Y = y;
    }

    public static Point Zero = new Point(0,0);

    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public Point(Vector2 vector)
    {
        this.X = (int)vector.x;
        this.Y = (int)vector.y;
    }
}
