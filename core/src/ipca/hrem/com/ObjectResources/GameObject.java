package ipca.hrem.com.ObjectResources;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject extends TouchableObject {
    protected Sprite sprite;
    protected Vector2 position;
    protected float scale;

    //-------------------------GetSetters-------------------------//
    public Sprite getSprite() {
        return sprite;
    }
    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
        this.sprite.setPosition(position.x * scale, position.y * scale);
    }
    public float getScale() {
        return scale;
    }
    public void setScale(float scale) {
        this.scale = scale;
        this.sprite.setScale(scale);
    }

    //-------------------------Constructor-------------------------//
    public GameObject(){
    }
    public GameObject(Vector2 position, float scale) {
        this.position = position;
        this.scale = scale;
    }

    //-------------------------Functions-------------------------//
    public abstract void act(Object object);
    public abstract void update(float deltaTime);
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
    public void dispose() {
        sprite.getTexture().dispose();
        position = null;
        scale = 0.0f;
    }

}
