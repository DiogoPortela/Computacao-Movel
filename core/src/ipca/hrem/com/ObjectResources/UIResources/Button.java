package ipca.hrem.com.ObjectResources.UIResources;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.ResourceManagers.TextureManager;

public class Button extends   UIObject{

    private Sprite sprite;
    private String text;

    public Button(String textureName, Vector2 position, Vector2 size, String text){
        super();
        this.sprite = new Sprite(TextureManager.loadTexture(textureName));
        this.sprite.setPosition(position.x, position.y);
        this.sprite.setSize(size.x, size.y);
        this.text = text;
    }

    public boolean isVectorInside(Vector2 position){
        return sprite.getBoundingRectangle().contains(position);
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
        //DRAW TEXT.
    }
    public void dispose() {
        sprite.getTexture().dispose();
    }
}
