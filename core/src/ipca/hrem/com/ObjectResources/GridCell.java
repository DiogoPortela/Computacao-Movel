package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.ResourceManagers.TextureManager;

public class GridCell extends GameObject{

    public enum CellType {                      //ENUMERATOR FOR ALL CELL TYPES: THIS IS SUPPOSED TO GENERATE THE SPRITE AND STUFF.
        empty, test, selectedTest, grass, concrete, woodenFloor     //....
    }

    //-------------------------Variables-------------------------//
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
                sprite = new Sprite(TextureManager.loadTexture("Tile001.png"));
                break;
            case selectedTest:
                sprite = new Sprite(TextureManager.loadTexture("Tile002.png"));
            case grass:
                //SET SPRITE AND STATS
                break;
        }
    }
    public boolean getIsInterior() {
        return isInterior;
    }
    public void setIsInterior(boolean interior) {
        isInterior = interior;
    }

    //-------------------------Constructor-------------------------//
    public GridCell(CellType cellType, Vector2 position, float scale) {
        setCellType(cellType);
        this.position = position;
        this.scale = scale;
        this.sprite.setPosition(position.x * scale, position.y * scale);
        this.sprite.setSize(scale, scale);
    }

    @Override
    public void act(Object object) {

    }
    @Override
    public void update(float deltaTime) {

    }

    //-------------------------Functions-------------------------//
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
    public void dispose() {
        sprite.setTexture(null);
        position = null;
        cellType = null;
    }
}
