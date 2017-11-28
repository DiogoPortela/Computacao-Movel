package ipca.hrem.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ipca.hrem.com.BasicResources.State;


/////		----------------//----------------
/////		HELLISH RESTAURANT EXTREME MANAGER
/////		----------------//----------------


public class MainGame extends ApplicationAdapter {
    SpriteBatch batch;
    public static State currentState;

    @Override
    public void create() {
        batch = new SpriteBatch();
        currentState = new GameState();
    }

    @Override
    public void render() {
        currentState.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        currentState.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        currentState.dispose();
    }
}
