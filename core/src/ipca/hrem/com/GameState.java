package ipca.hrem.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ipca.hrem.com.BasicResources.InputManager;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.BasicResources.State;

//GameLogic goes here
public class GameState extends State  {
    //-------------------------Variables-------------------------//
    public static Map currentMap;
    public static OrthographicCamera camera;
    private static Viewport currentViewport;
    private static InputManager inputManager;

    //-------------------------Constructor-------------------------//
    public GameState() {
        currentMap = new Map(20, 20);
        camera = new OrthographicCamera( 15, (15 * ((float)Gdx.graphics.getHeight() /(float)Gdx.graphics.getWidth())));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        currentViewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        //currentViewport.setScreenPosition( 50, 50);
        //currentViewport.apply();
        //currentViewport.update( 500, 500);
        Gdx.input.setInputProcessor(new GestureDetector(inputManager));
    }

    //-------------------------Functions-------------------------//
    @Override
    public void update(float gameTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        currentMap.render(batch);
    }

    @Override
    public void dispose() {
        camera = null;
        currentViewport = null;
        currentMap.dispose();
    }
}
