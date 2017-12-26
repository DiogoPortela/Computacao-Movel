package ipca.hrem.com.States;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.UIResources.Button;


public class BuildState extends GameState {
    //-------------------------Variables-------------------------//
    private Button returnToLiveBtn;
    private Sprite tile1;
    //-------------------------GetSetters-------------------------//

    //-------------------------Constructor-------------------------//
    public BuildState() {
        setCurrentMenuSize(5.0f);
        returnToLiveBtn = new Button(new Vector2(getCurrentMenuSizeWorld() - 2.5f, gameScaleHeight - 1.1f), new Vector2(2, 1), new Vector2(64, 32 * 3), new Vector2(64, 32)) {
            @Override
            public void onClick() {
                MainGame.setCurrentState(new LiveState());
            }
        };
        addUIObject(returnToLiveBtn);
    }

    //-------------------------Functions-------------------------//
    @Override
    public void update(float gameTime) {

    }

    @Override
    protected void renderMenu(SpriteBatch batch) {
        returnToLiveBtn.render(batch);
    }

    @Override
    protected void renderGame(SpriteBatch batch) {

    }
}
