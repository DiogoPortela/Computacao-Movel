package ipca.hrem.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.Viewport;

import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.InputManagers.BasicInput;
import ipca.hrem.com.InputManagers.InputManager;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.BasicResources.State;

//GameLogic goes here
public class GameState extends State  {
    //-------------------------Variables-------------------------//
    public static Map currentMap;
    public static OrthographicCamera camera;
    private static Viewport currentViewport, currentMenuViewport;
    private static InputManager inputManager;

    //-------------------------Constructor-------------------------//
    public GameState() {
        currentMap = new Map(20, 20);
        camera = new OrthographicCamera();
        currentViewport = new GameViewport(new Point(150, 0), new Point(Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight()),15, (15 * ((float) Gdx.graphics.getHeight() /(float)Gdx.graphics.getWidth())), camera);
        currentViewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

        inputManager = new BasicInput(this);
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

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }
}
