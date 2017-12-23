package ipca.hrem.com.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.GameObject;
import ipca.hrem.com.ObjectResources.UIResources.Button;

public class LiveState extends GameState {

    private Button stopTimeBtn;
    private Button playTimeBtn;
    private Button managerStateBtn;
    private Button buildStateBtn;
    private Button menuStateBtn;
    private Label.LabelStyle labelStyle;


    public LiveState(float menuSize) {
        super(menuSize);
        buildStateBtn = new Button("Tile002.png", new Vector2(0, 0), new Vector2(1, 1), "") {

            @Override
            public void onClick() {
                MainGame.setCurrentState(new BuildState());
            }
        };
        addUIObject(buildStateBtn);
    }

    public LiveState() {

    }

    @Override
    public void update(float gameTime) {

    }

    @Override
    protected void renderMenu(SpriteBatch batch) {
        buildStateBtn.render(batch);
    }

    @Override
    protected void renderGame(SpriteBatch batch) {
        currentMap.render(batch);
        for (GameObject obj : allGameObjects) {
            obj.render(batch);                      //OPTIMIZE THIS.
        }
    }
}
