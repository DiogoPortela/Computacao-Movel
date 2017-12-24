package ipca.hrem.com.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.GameObject;
import ipca.hrem.com.ObjectResources.UIResources.Button;
import ipca.hrem.com.ObjectResources.UIResources.Clock;

public class LiveState extends GameState {
    //-------------------------Variables-------------------------//
    private Button timeBtn;
    private Button timeFastBtn;
    private Button managerStateBtn;
    private Button buildStateBtn;
    private Button menuStateBtn;
    private Label.LabelStyle labelStyle;
    private Clock clock;
    //-------------------------GetSetters-------------------------//

    //-------------------------Constructor-------------------------//
    public LiveState(float menuSize) {
        super(menuSize);
        clock = new Clock(new Vector2(0.1f, GameState.gameScaleHeight - 1.2f), new Vector2(2.0f, 1.0f));


        timeBtn = new Button("ButtonStop.png", new Vector2(0.05f, clock.getPosition().y - 1.2f), new Vector2(1.0f, 1.0f)) {
            @Override
            public void onClick() {
                if (timeSpeed > 0) {
                    timeSpeed = 0;
                    this.changeTexture("ButtonPlay.png");
                } else {
                    timeSpeed = 1;
                    this.changeTexture("ButtonStop.png");
                }

            }
        };
        addUIObject(timeBtn);

        timeFastBtn = new Button("ButtonFastForward.png", new Vector2(1.1f, clock.getPosition().y - 1.2f), new Vector2(1.0f, 1.0f)) {
            @Override
            public void onClick() {
                if (timeSpeed == 1) {
                    timeSpeed = 2;
                }else if (timeSpeed == 2){
                    timeSpeed = 4;
                }else if (timeSpeed == 4){
                    timeSpeed = 1;
                }
            }
        };
        addUIObject(timeFastBtn);

        buildStateBtn = new Button("ButtonBuild.png", new Vector2(0.1f, timeBtn.getPosition().y - 1.1f), new Vector2(2.0f, 1.0f)) {
            @Override
            public void onClick() {
                MainGame.setCurrentState(new BuildState());
            }
        };
        addUIObject(buildStateBtn);

    }

    public LiveState() {

    }

    //-------------------------Functions-------------------------//
    @Override
    public void update(float gameTime) {
        date.gameUpdate(gameTime * timeSpeed);
        clock.update(date);
    }

    @Override
    protected void renderMenu(SpriteBatch batch) {
        buildStateBtn.render(batch);
        timeBtn.render(batch);
        timeFastBtn.render(batch);
        clock.render(batch);
    }

    @Override
    protected void renderGame(SpriteBatch batch) {
        currentMap.render(batch);
        for (GameObject obj : allGameObjects) {
            obj.render(batch);                      //OPTIMIZE THIS.
        }
    }
}
