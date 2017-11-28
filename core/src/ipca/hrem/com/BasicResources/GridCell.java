package ipca.hrem.com.BasicResources;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GridCell {

    public  enum CellType{                      //ENUMERATOR FOR ALL CELL TYPES
        empty, grass, concrete, woodenFloor     //....
    }

    private Sprite sprite;
    private CellType cellType;
    private boolean isInterior;
    //Some statistics here

    public  GridCell(CellType cellType){
        setCellType(cellType);
    }

    public CellType getCellType() {
        return cellType;
    }
    public void setCellType(CellType cellType) {
        this.cellType = cellType;

        switch (cellType) {
            case empty:
                break;
            case grass:
                //SET SPRITE AND STATS
                //sprite = new Sprite(new Texture("Tile001.png"))
                break;
        }
    }
    public boolean isInterior() {
        return isInterior;
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
