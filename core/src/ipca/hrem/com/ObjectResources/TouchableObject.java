package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class TouchableObject {
    public abstract  boolean isVectorInside(Vector2 position);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
