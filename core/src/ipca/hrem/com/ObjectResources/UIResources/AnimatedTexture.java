package ipca.hrem.com.ObjectResources.UIResources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.ResourceManagers.TextureManager;

public class AnimatedTexture extends UIObject {
    //-------------------------Variables-------------------------//
    private TextureRegion textureRegion;
    private Rectangle rectangle;

    //-------------------------GetSetters-------------------------//
    public Vector2 getPosition(){
        return new Vector2(rectangle.getX(), rectangle.getY());
    }

    public void setPosition(Vector2 position){
        rectangle.setPosition(position.x, position.y);
    }

    public void changeRegion(Vector2 newPosition, Vector2 regionSize){
        textureRegion.setRegion((int)newPosition.x, (int)newPosition.y, (int)regionSize.x, (int)regionSize.y);
    }
    //-------------------------Constructor-------------------------//
    public AnimatedTexture(String textureName, Vector2 position, Vector2 size) {
        textureRegion = new TextureRegion(TextureManager.loadTexture(textureName));
        rectangle = new Rectangle(position.x, position.y, size.x, size.y);
    }
    public AnimatedTexture(String textureName, Vector2 position, Vector2 size, Vector2 regionPosition, Vector2 regionSize) {
        textureRegion = new TextureRegion(TextureManager.loadTexture(textureName), (int)regionPosition.x, (int)regionPosition.y, (int)regionSize.x, (int)regionSize.y);
        rectangle = new Rectangle(position.x, position.y, size.x, size.y);
    }

    //-------------------------Functions-------------------------//
    @Override
    public boolean isVectorInside(Vector2 position) {
        return rectangle.contains(position);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(textureRegion, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public void dispose() {
        textureRegion.getTexture().dispose();
        rectangle = null;
    }
}
