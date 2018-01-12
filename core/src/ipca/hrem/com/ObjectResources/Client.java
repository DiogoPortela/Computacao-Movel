package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

import ipca.hrem.com.BasicResources.GraphGrid;
import ipca.hrem.com.BasicResources.Pathfinding;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.InputManagers.GameInput;
import ipca.hrem.com.ResourceManagers.TextureManager;

public class Client extends GameObject {
    //-------------------------Variables-------------------------//
    private Vector2 targetPosition;
    private float moveSpeed = 1.0f;
    private static GraphGrid clientGrid = new GraphGrid();
    private boolean isMoving, isEating, isWaiting;
    private float patience;
    private ArrayList<Point> path = new ArrayList<Point>();
    //-------------------------GetSetters-------------------------//

    //-------------------------Constructor-------------------------/
    public Client(Vector2 position, float scale) {
        super(position, scale);
        sprite = new Sprite(TextureManager.loadTexture("Cliente001.png"));
        sprite.setPosition(position.x * scale, position.y * scale);
        sprite.setSize(scale, scale);
        this.targetPosition = position;
        isWaiting = false;
        isMoving = false;
        isEating = false;
        Random randPatience = new Random();
        patience = 100 + randPatience.nextInt(100) - 50;
    }

    //-------------------------Functions-------------------------//
    @Override
    public void act(Object object) {
        if (object.getClass() == Vector2.class) {
            this.targetPosition = (Vector2) object;
            this.targetPosition.sub(new Vector2((scale /2.0f), scale / 2.0f));
            this.path = CalculatePath();
        }
    }

    public ArrayList<Point> CalculatePath()
    {
        return Pathfinding.FindPath(clientGrid, Point.fromVector2(this.position), Point.fromVector2(this.targetPosition), true);
    }

    @Override
    public void update(float deltaTime) {
        if (isWaiting && !isEating && !isMoving)
            patience -= 1 * deltaTime;

        if (this.path != null)
        {
            Vector2 aux = new Vector2( path.get(0).X - this.position.x, path.get(0).Y - this.position.y);
            if (aux.len() < 0.1f)
                path.remove(0);
            if (!path.isEmpty())
            {
                isMoving = true;
                Vector2 aux2 = new Vector2( path.get(0).X - this.position.x, path.get(0).Y - this.position.y);
                if (aux2.len() > 0.1f)
                {
                    aux2.nor().scl(0.04f);
                    this.position.add(aux2);
                    sprite.setPosition(position.x * scale, position.y * scale);
                }
            }
            else {
                isMoving = false;
            }

        }



        /*Vector2 aux = new Vector2(this.targetPosition.x - this.position.x, this.targetPosition.y - this.position.y);
        if (aux.len() > 0.1f){
            aux.nor().scl(0.04f);
            this.position.add(aux);
            sprite.setPosition(position.x * scale, position.y * scale);
        }*/
    }
}