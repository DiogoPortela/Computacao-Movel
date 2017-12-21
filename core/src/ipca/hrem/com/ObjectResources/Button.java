package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.ResourceManagers.TextureManager;

public class Button extends GameObject {

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

    @Override
    public void act(Object object) {

    }

    @Override
    public void update(float deltaTime) {

    }
}
