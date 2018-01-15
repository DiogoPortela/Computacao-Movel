package ipca.hrem.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ipca.hrem.com.InputManagers.BuildInput;
import ipca.hrem.com.InputManagers.GameInput;
import ipca.hrem.com.InputManagers.InputManager;
import ipca.hrem.com.InputManagers.MenuInput;
import ipca.hrem.com.InputManagers.ScoreInput;
import ipca.hrem.com.ResourceManagers.FontManager;
import ipca.hrem.com.States.BuildState;
import ipca.hrem.com.States.GameState;
import ipca.hrem.com.States.LiveState;
import ipca.hrem.com.States.ScoreState;
import ipca.hrem.com.States.State;
import ipca.hrem.com.ResourceManagers.TextureManager;
import ipca.hrem.com.States.MenuState;


/////		----------------//----------------
/////		HELLISH RESTAURANT EXTREME MANAGER
/////		----------------//----------------


public class MainGame extends ApplicationAdapter {
    //-------------------------Variables-------------------------//
    private static SpriteBatch batch;
    private static State currentState;
    private static InputManager inputManager;
    public static Player currentPlayer;
    public static Label.LabelStyle testLabel;

    //-------------------------GetSetters-------------------------//
    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State newState) {
        currentState = newState;
        if(newState instanceof LiveState) {
            setInputManager(new GameInput((LiveState) currentState));
        }
        else if(newState instanceof BuildState) {
            setInputManager(new BuildInput((BuildState) currentState));
        }
        else if(newState instanceof MenuState){
            setInputManager(new MenuInput((MenuState) currentState));
        } else if(newState instanceof ScoreState){
            setInputManager(new ScoreInput((ScoreState) currentState));
        }
    }

    public static InputManager getInputManager() {
        return inputManager;
    }

    private static void setInputManager(InputManager newInputManager) {
        inputManager = newInputManager;
        Gdx.input.setInputProcessor(new GestureDetector(inputManager));

    }

    //-------------------------Functions-------------------------//

    @Override
    public void create() {
        TextureManager.Start();
        FontManager.Start();
        currentPlayer = new Player();
        testLabel = FontManager.loadFont("ARIALUNI.TTF", 10, Color.WHITE);
        batch = new SpriteBatch();

        setCurrentState(new MenuState());
    }

    @Override
    public void render() {
        currentState.update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentState.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        currentState.dispose();
        TextureManager.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        currentState.resize(width, height);
    }
}
