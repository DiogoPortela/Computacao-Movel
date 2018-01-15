package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

import ipca.hrem.com.BasicResources.Pathfinding;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.InputManagers.GameInput;
import ipca.hrem.com.MainGame;
import ipca.hrem.com.ResourceManagers.TextureManager;
import ipca.hrem.com.States.LiveState;
import ipca.hrem.com.States.State;
import ipca.hrem.com.Player;
import sun.applet.Main;

public class Client extends GameObject {
    //-------------------------Variables-------------------------//
    private Vector2 targetPosition;
    private float moveSpeed = 1.0f;
    private boolean isMoving, isEating, isWaiting, isLeaving;
    private float patience;
    private ArrayList<Point> path = new ArrayList<Point>();
    private String name;
    private nameType nameGender;
    private int randomNumb;
    private float waitingMeter;
    BitmapFont bitmapFont;
    private float money;

    public enum nameType {male, female, apelido}

    public Item usingItem;

    //-------------------------GetSetters-------------------------//
    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    public int getRandomNumb() {
        return randomNumb;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLeaving() {
        return isLeaving;
    }

    public float getPatience() {
        return patience;
    }

    public float getMoney() {
        return money;
    }

    //-------------------------Constructor-------------------------/
    public Client(Vector2 position, float scale, int randomNumb, nameType nameGender) {
        super(position, scale);
        sprite = new Sprite(TextureManager.loadTexture("Cliente001.png"));
        sprite.setPosition(position.x * scale, position.y * scale);
        sprite.setSize(scale, scale);
        this.targetPosition = position;
        this.randomNumb = randomNumb;
        this.nameGender = nameGender;
        isWaiting = false;
        isMoving = false;
        isEating = false;
        isLeaving = false;
        Random randPatience = new Random();
        patience = 300 + randPatience.nextInt(100) - 50;
        money = 20 + randPatience.nextInt(80);
        waitingMeter = 0;
        bitmapFont = new BitmapFont(Gdx.files.internal("anotherarialsmaller.fnt"));
        bitmapFont.getData().setScale(0.05f);
    }

    public Client(Vector2 position, float scale) {
        super(position, scale);
        sprite = new Sprite(TextureManager.loadTexture("Cliente001.png"));
        sprite.setPosition(position.x * scale, position.y * scale);
        sprite.setSize(scale, scale);
        this.targetPosition = position;
        this.randomNumb = 0;
        this.nameGender = nameType.male;
        isWaiting = false;
        isMoving = false;
        isEating = false;
        isLeaving = false;
        Random randPatience = new Random();
        patience = 100 + randPatience.nextInt(100) - 50;
        waitingMeter = 1;
    }

    //-------------------------Functions-------------------------//
    @Override
    public void act(Object object) {
        if (object.getClass() == Vector2.class) {
            this.targetPosition = (Vector2) object;
            this.targetPosition.sub(new Vector2((scale / 2.0f), scale / 2.0f));
            this.path = CalculatePath();
        }

        if (object instanceof Table) {
            usingItem = (Table) object;
            this.targetPosition = ((Table) object).position;
            //this.targetPosition.sub(new Vector2((scale /2.0f), scale / 2.0f));
            this.path = CalculatePath();
            isEating = true;
            isWaiting = false;
        }
    }

    public ArrayList<Point> CalculatePath() {
        return Pathfinding.FindPath(Player.graphPath, Point.fromVector2(this.position), Point.fromVector2(this.targetPosition), true);
    }

    @Override
    public void render(SpriteBatch batch) {
        //Para fazer aparecer as letras
        //bitmapFont.draw(batch, this.getName(), this.position.x-1 ,this.position.y+1);
        super.render(batch);
    }

    @Override
    public void update(float deltaTime) {
        if (!isEating && !isLeaving) {
            isWaiting = true;
        }
        if ((isWaiting || isMoving) && (!isEating || !isLeaving)) {
            if (waitingMeter > 1f)
                waitingMeter = 1;
            else
                waitingMeter += deltaTime * 0.005f;

            sprite.setColor(1f, 1f - waitingMeter, 1f - waitingMeter, 1);

            patience -= 1 * waitingMeter*0.05f;
        }

        if (patience <= 0) {
            leave();
        }


        if (this.path != null && this.path.size() != 0)

        {
            Vector2 aux = new Vector2(path.get(0).X - this.position.x, path.get(0).Y - this.position.y);
            if (aux.len() < 0.1f)
                path.remove(0);
            if (!path.isEmpty()) {
                isMoving = true;
                Vector2 aux2 = new Vector2(path.get(0).X - this.position.x, path.get(0).Y - this.position.y);
                if (aux2.len() > 0.1f) {
                    aux2.nor().scl(0.04f);
                    this.position.add(aux2);
                    sprite.setPosition(position.x * scale, position.y * scale);
                }
            } else {
                isMoving = false;
            }
        }

        if (isEating)

        {
            patience += 2 * deltaTime;
            if (patience >= 150) {
                leave();
                ((Table) usingItem).setUsed(false);
                usingItem = null;
                isEating = false;
            }

        }

        if (isLeaving)

        {
            Vector2 aux2 = new Vector2(0 - this.position.x, 5 - this.position.y);
            if (aux2.len() < 0.1f) {
                ((LiveState) MainGame.getCurrentState()).removeItem(this);
            }
        }

    }


    private void leave() {
        if (isLeaving == false) {
            isLeaving = true;
            this.targetPosition = new Vector2(0, 5);
            //this.targetPosition.sub(new Vector2((scale / 2.0f), scale / 2.0f));
            this.path = CalculatePath();
        }

    }

    private int DepletingMeter(int wainting, State state, float deltaTime) {
        return 0;
    }
}