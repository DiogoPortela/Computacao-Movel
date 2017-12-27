package ipca.hrem.com.States;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.InputManagers.BuildInput;
import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.GridCell;
import ipca.hrem.com.ObjectResources.UIResources.Button;


public class BuildState extends GameState {
    //-------------------------Variables-------------------------//
    private Button returnToLiveBtn;
    private Button buildBtn;
    private Button renovateBtn;
    private Button decorateBtn;

    private Button tile1;
    //-------------------------GetSetters-------------------------//

    //-------------------------Constructor-------------------------//
    public BuildState() {
        setCurrentMenuSize(5.0f);
        returnToLiveBtn = new Button(new Vector2( 0.5f, gameScaleHeight - 1.1f), new Vector2(1, 1), new Vector2(128, 96), new Vector2(32, 32)) {
            @Override
            public void onClick() {
                MainGame.setCurrentState(new LiveState());
            }
        };
        addUIObject(returnToLiveBtn);

        buildBtn = new Button(new Vector2( 2.0f, gameScaleHeight - 1.1f), new Vector2(1, 1), new Vector2(160, 0), new Vector2(32, 32)) {
            @Override
            public void onClick() {
                ((BuildInput) MainGame.getInputManager()).setBuildingWalls(true);
            }
        };
        addUIObject(buildBtn);


        tile1 = new Button(GridCell.tileTexture, new Vector2(0,0), new Vector2(1,1), new Vector2(0,0), new Vector2(32, 32)) {
            @Override
            public void onClick() {
                setSelectedObject(this);
                ((BuildInput)MainGame.getInputManager()).setCurrentSelectedCellType(GridCell.CellType.grass);
            }
        };
        addUIObject(tile1);
    }

    //-------------------------Functions-------------------------//
    @Override
    public void update(float gameTime) {

    }

    @Override
    protected void renderMenu(SpriteBatch batch) {
        returnToLiveBtn.render(batch);
        buildBtn.render(batch);
        tile1.render(batch);
    }

    @Override
    protected void renderGame(SpriteBatch batch) {

    }
}
