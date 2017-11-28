package ipca.hrem.com;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.BasicResources.State;

//GameLogic goes here
public class GameState extends State {
    //-------------------------Variables-------------------------//
    public static Map currentMap;
    public static OrthographicCamera camera;
    public static Viewport currentViewport;

    //-------------------------Constructor-------------------------//
    public GameState() {
        currentMap = new Map(20, 20);
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
    }

    //-------------------------Functions-------------------------//
    @Override
    public void update(float gameTime) {
        
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        currentMap.render(batch);
    }

    @Override
    public void dispose() {

    }
}
