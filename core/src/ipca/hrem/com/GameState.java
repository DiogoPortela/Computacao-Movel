package ipca.hrem.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.InputManagers.BasicInput;
import ipca.hrem.com.InputManagers.InputManager;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.BasicResources.State;

//GameLogic goes here
public class GameState extends State {

    public final static float gameScale = 15.0f;
    //-------------------------Variables-------------------------//
    public static Map currentMap;
    public static OrthographicCamera gameCamera, menuCamera;
    public static Viewport currentViewport, currentMenuViewport;
    private static InputManager inputManager;

    private static int currentMenuSize;

    //-------------------------GetSetters-------------------------//
    public static int getCurrentMenuSize() {
        return currentMenuSize;
    }

    public static void setCurrentMenuSize(int currentMenuSize) {
        GameState.currentMenuSize = currentMenuSize;
        currentViewport.setScreenPosition(currentMenuSize, 0);
        currentViewport.setScreenWidth(Gdx.graphics.getBackBufferWidth() - currentMenuSize);

        currentMenuViewport.setScreenWidth(currentMenuSize);
    }

    //-------------------------Constructor-------------------------//
    public GameState() {
        currentMap = new Map(20, 20);
        currentMenuSize = 150;

        gameCamera = new OrthographicCamera();
        currentViewport = new GameViewport(new Point(currentMenuSize, 0), new Point(Gdx.graphics.getWidth() - currentMenuSize, Gdx.graphics.getHeight()), gameScale, (gameScale * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())), gameCamera);
        gameCamera.position.set(gameCamera.viewportWidth / 2f, gameCamera.viewportHeight / 2f, 0);

        menuCamera = new OrthographicCamera();
        currentMenuViewport = new GameViewport(Point.Zero, new Point(currentMenuSize, Gdx.graphics.getHeight()), gameScale, (gameScale * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth())), menuCamera);

        inputManager = new BasicInput(this);
        Gdx.input.setInputProcessor(new GestureDetector(inputManager));
    }

    //-------------------------Functions-------------------------//
    @Override
    public void update(float gameTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        gameCamera.update();
        currentViewport.apply();
        batch.setProjectionMatrix(gameCamera.combined);
        batch.begin();
        currentMap.render(batch);
        batch.end();

        menuCamera.update();
        currentMenuViewport.apply();
        batch.setProjectionMatrix(menuCamera.combined);
        batch.begin();
        //MenuDraw
        batch.end();
    }

    @Override
    public void dispose() {
        gameCamera = null;
        menuCamera = null;
        currentViewport = null;
        currentMenuViewport = null;
        currentMap.dispose();
    }

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width, height);
        gameCamera.position.set(gameCamera.viewportWidth / 2, gameCamera.viewportHeight / 2, 0);
        currentMenuViewport.update(width, height);
    }
}
