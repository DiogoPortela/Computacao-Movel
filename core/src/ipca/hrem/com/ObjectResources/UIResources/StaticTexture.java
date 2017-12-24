package ipca.hrem.com.ObjectResources.UIResources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.ResourceManagers.TextureManager;

public class StaticTexture extends UIObject {
    //-------------------------Variables-------------------------//
    private Texture texture;
    private Rectangle rectangle;

    //-------------------------Constructor-------------------------//
    public StaticTexture(String textureName, Vector2 position, Vector2 size) {
        texture = TextureManager.loadTexture(textureName);
        rectangle = new Rectangle(position.x, position.y, size.x, size.y);
    }

    //-------------------------Functions-------------------------//
    @Override
    public boolean isVectorInside(Vector2 position) {
        return rectangle.contains(position);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public void dispose() {
        texture.dispose();
        rectangle = null;
    }
}
