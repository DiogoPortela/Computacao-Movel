package ipca.hrem.com.ObjectResources.UIResources;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.ResourceManagers.TextureManager;

public abstract class Button extends UIObject {
    //-------------------------Variables-------------------------//
    private Sprite sprite;
    private String text;

    //-------------------------GetSetters-------------------------//

    public void changeTexture(String textureName) {
        this.sprite.setTexture(TextureManager.loadTexture(textureName));
    }

    public void changeTexture(Vector2 regionPosition, Vector2 regionSize) {
        this.sprite.setRegion((int) regionPosition.x, (int) regionPosition.y, (int) regionSize.x, (int) regionSize.y);
    }

    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }

    //-------------------------Constructor-------------------------//
    public Button(String textureName, Vector2 position, Vector2 size) {
        super();
        this.sprite = new Sprite(TextureManager.loadTexture(textureName));
        this.sprite.setPosition(position.x, position.y);
        this.sprite.setSize(size.x, size.y);
        this.text = "";
    }
    public Button(String textureName, Vector2 position, Vector2 size, Vector2 regionPosition, Vector2 regionSize) {
        super();
        this.sprite = new Sprite(new TextureRegion(TextureManager.loadTexture(textureName), (int) regionPosition.x, (int) regionPosition.y, (int) regionSize.x, (int) regionSize.y));
        this.sprite.setPosition(position.x, position.y);
        this.sprite.setSize(size.x, size.y);
        this.text = "";
    }
    public Button(Vector2 position, Vector2 size, Vector2 regionPosition, Vector2 regionSize) {
        super();
        this.sprite = new Sprite(new TextureRegion(TextureManager.loadTexture("buttonsSheet.png"), (int) regionPosition.x, (int) regionPosition.y, (int) regionSize.x, (int) regionSize.y));
        this.sprite.setPosition(position.x, position.y);
        this.sprite.setSize(size.x, size.y);
        this.text = "";
    }

    public Button(String textureName, Vector2 position, Vector2 size, String text) {
        super();
        this.sprite = new Sprite(TextureManager.loadTexture(textureName));
        this.sprite.setPosition(position.x, position.y);
        this.sprite.setSize(size.x, size.y);
        this.text = text;
    }

    //-------------------------Functions-------------------------//
    public boolean isVectorInside(Vector2 position) {
        return sprite.getBoundingRectangle().contains(position);
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
        //DRAW TEXT.
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }

    //-------------------------Abstracts-------------------------//
    public abstract void onClick();

}
