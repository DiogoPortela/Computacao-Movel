package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ipca.hrem.com.ResourceManagers.TextureManager;

public class GridCell extends GameObject {

    public enum CellType {                      //ENUMERATOR FOR ALL CELL TYPES: THIS IS SUPPOSED TO GENERATE THE SPRITE AND STUFF.
        empty, test, grass, dirt, concrete, woodenFloor     //....
    }

    //-------------------------Variables-------------------------//
    public static final String tileTexture = "TileTexture.png";
    private Vector2 position;
    private float scale;
    private Sprite sprite;
    private CellType cellType;
    private boolean isInterior;
    //Some statistics here

    //-------------------------GetSetters-------------------------//
    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;

        switch (cellType) {
            case empty:
                break;
            case test:
                sprite = new Sprite(new TextureRegion(TextureManager.loadTexture(tileTexture), 0, 0, 32, 32));
                break;
            case grass:
                Random random = new Random();
                sprite = new Sprite(new TextureRegion(TextureManager.loadTexture(tileTexture), 0, random.nextInt(3) * 32, 32, 32));
                setIsInterior(false);
                break;
            case dirt:
                sprite = new Sprite(new TextureRegion(TextureManager.loadTexture(tileTexture), 0, 128 * 32, 32, 32));
                break;
        }
        sprite.setPosition(position.x, position.y);
        sprite.setSize(scale, scale);
    }

    public boolean getIsInterior() {
        return isInterior;
    }

    public void setIsInterior(boolean interior) {
        isInterior = interior;
    }

    //-------------------------Constructor-------------------------//
    public GridCell(CellType cellType, Vector2 position, float scale) {
        this.position = position;
        this.scale = scale;
        setCellType(cellType);
    }
    //-------------------------Functions-------------------------//

    @Override
    public void act(Object object) {

    }

    @Override
    public void update(float deltaTime) {

    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        sprite.setTexture(null);
        position = null;
        cellType = null;
    }
}
