package ipca.hrem.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    //-------------------------GetSetters-------------------------//
    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State newState) {
        currentState = newState;
    }

    //-------------------------Functions-------------------------//

    @Override
    public void create() {
        TextureManager.Start();
        batch = new SpriteBatch();
        currentState = new MenuState();
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
