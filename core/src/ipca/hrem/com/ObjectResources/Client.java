package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.BasicResources.Pathfinding;
import ipca.hrem.com.ResourceManagers.TextureManager;

public class Client extends GameObject {
    //-------------------------Variables-------------------------//
    private Vector2 targetPosition;
    private float moveSpeed = 2.0f;

    //-------------------------GetSetters-------------------------//

    //-------------------------Constructor-------------------------/
    public Client(Vector2 position, float scale) {
        super(position, scale);
        sprite = new Sprite(TextureManager.loadTexture("Cliente001.png"));
        sprite.setPosition(position.x * scale, position.y * scale);
        sprite.setSize(scale, scale);
        this.targetPosition = position;
    }

    //-------------------------Functions-------------------------//
    @Override
    public void act(Object object) {
        if (object.getClass() == Vector2.class) {
            this.targetPosition = (Vector2) object;
            this.targetPosition.sub(new Vector2((scale /2.0f), scale / 2.0f));
        }
    }

    @Override
    public void update(float deltaTime) {
        Vector2 aux = new Vector2(this.targetPosition.x - this.position.x, this.targetPosition.y - this.position.y);
        if (aux.len() > 0.1f){
            aux.nor().scl(0.04f);
            this.position.add(aux);
            sprite.setPosition(position.x * scale, position.y * scale);
        }
    }
}