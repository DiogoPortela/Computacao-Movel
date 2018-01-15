package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.ResourceManagers.TextureManager;

public class Table extends Item {
    //-------------------------Variables-------------------------//
    boolean isUsed;

    //-------------------------GetSetters-------------------------//


    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    //-------------------------Constructor-------------------------//
    public Table(Vector2 position) {
        super(position, 1);
        sprite = new Sprite(new TextureRegion(TextureManager.loadTexture(GridCell.tileTexture), 416, 160, 96, 64));
        sprite.setPosition(position.x * scale, position.y * scale);
        sprite.setSize(3, 2);
        isUsed = false;
    }
}
