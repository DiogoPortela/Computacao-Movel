package ipca.hrem.com.BasicResources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.ResourceManagers.TextureManager;

public class GridCell {

    public enum CellType {                      //ENUMERATOR FOR ALL CELL TYPES: THIS IS SUPPOSED TO GENERATE THE SPRITE AND STUFF.
        empty, test, grass, concrete, woodenFloor     //....
    }

    //-------------------------Variables-------------------------//
    private Texture texture;
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
                TextureManager.loadTexture("Tile001.png");
                texture = TextureManager.getTexture("Tile001.png");
                break;
            case grass:
                //SET SPRITE AND STATS

                break;
        }
    }

    public boolean isInterior() {
        return isInterior;
    }

    //-------------------------Constructor-------------------------//
    public GridCell(CellType cellType, Vector2 position, float scale) {
        setCellType(cellType);
        texture = new Texture("Tile001.png");
        this.scale = scale;
        this.position = position.scl(scale);
    }


    //-------------------------Functions-------------------------//
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, scale, scale);
        //batch.draw(texture, position.x, position.y);
    }

    public void Dispose() {

    }
}
