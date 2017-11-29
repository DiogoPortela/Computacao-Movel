package ipca.hrem.com.BasicResources;


import com.badlogic.gdx.math.Vector2;

//Vector2 but whit ints.
public class Point {
    //-------------------------Variables-------------------------//
    public int X;
    public int Y;

    //-------------------------GetSetters-------------------------//

    //-------------------------Instances-------------------------//
    public static Point Zero = new Point(0, 0);

    //-------------------------Constructor-------------------------//
    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public Point(Vector2 vector) {
        this.X = (int) vector.x;
        this.Y = (int) vector.y;
    }

    //-------------------------Functions-------------------------//
    public static Vector2 toVector2(Point p) {
        return new Vector2(p.X, p.Y);
    }

    public static Point fromVector2(Vector2 v) {
        return new Point((int) v.x, (int) v.y);
    }
}
