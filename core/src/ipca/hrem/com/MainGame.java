package ipca.hrem.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;

import java.awt.Menu;

import ipca.hrem.com.InputManagers.GameInput;
import ipca.hrem.com.InputManagers.InputManager;
import ipca.hrem.com.InputManagers.MenuInput;
import ipca.hrem.com.States.GameState;
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

    //-------------------------GetSetters-------------------------//
    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State newState) {
        currentState = newState;
        if(newState instanceof GameState) {
            setInputManager(new GameInput());
            ((GameInput)inputManager).setCurrentGameState((GameState) currentState);
        }
        else if(newState instanceof MenuState){
            setInputManager(new MenuInput());
            ((MenuInput)inputManager).setCurrentState((MenuState) currentState);
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
        batch = new SpriteBatch();

        setCurrentState(new MenuState());
    }

    @Override
    public void render() {
        currentState.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0, 0, 1, 1);
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
