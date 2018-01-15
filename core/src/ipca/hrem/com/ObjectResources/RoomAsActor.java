package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

/**
 * Created by Ferna on 02/01/2018.
 */

public abstract class RoomAsActor extends Actor {
    protected ArrayList<Image> roomObjects;
    protected Image floor;
    protected float size;
    protected float maxSize;

    public RoomAsActor(Vector2 position, float size) {

        roomObjects = new ArrayList<Image>();
        floor = GetFloor(roomObjects);
        if (!(floor.getName().equals(null))) {
            setBounds(floor.getImageX(), floor.getImageY(), floor.getImageWidth(), floor.getImageHeight());
            if (CheckIfFits(size, this.maxSize))
                setSize(floor.getImageWidth(), floor.getImageHeight());
        }
        setPosition(position.x, position.y);


    }

    public boolean CheckIfFits(float size, float maxSize) {
        if (size > maxSize) {
            this.size = maxSize;
            return false;
        } else
            return true;
    }

    protected Image GetFloor(ArrayList<Image> roomObjects) {
        Image floor = new Image();
        for (Image image : roomObjects) {
            if (image.getName().equals("Floor")) {
                floor = image;
                return floor;
            }

        }
        return floor;
    }

        @Override
        public void draw (Batch batch,float parentAlpha){
            for (Image image : roomObjects) {
                image.draw(batch, 1);
            }
        }
    }
