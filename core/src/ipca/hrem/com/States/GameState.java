package ipca.hrem.com.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.InputManagers.InputManager;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.BasicResources.Point;

//GameLogic goes here
public abstract class GameState extends State {

    public final static float gameScaleWidth = 15.0f;
    public final static float gameScaleHeight = gameScaleWidth * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
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

    public void setCurrentMenuSize(int menuSize) {
        currentMenuSize = menuSize;
        currentViewport.setScreenPosition(currentMenuSize, 0);
        currentViewport.setScreenWidth(Gdx.graphics.getBackBufferWidth() - currentMenuSize);

        currentMenuViewport.setScreenWidth(currentMenuSize);
    }

    //-------------------------Constructor-------------------------//
    public GameState(int menuSize, InputManager input) {
        currentMap = new Map(20, 20);

        gameCamera = new OrthographicCamera();

        currentMenuSize = menuSize;
        currentViewport = new GameViewport(new Point(currentMenuSize, 0), new Vector2(Gdx.graphics.getBackBufferWidth() - currentMenuSize, Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, gameCamera);
        currentViewport.apply();
        gameCamera.position.set(gameCamera.viewportWidth / 2.0f, gameCamera.viewportHeight / 2f, 0);

        menuCamera = new OrthographicCamera();
        currentMenuViewport = new GameViewport(Point.Zero, new Vector2(currentMenuSize, Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, menuCamera);
        currentMenuViewport.apply();
        menuCamera.position.set(menuCamera.viewportWidth / 2f, menuCamera.viewportHeight / 2f, 0);

        inputManager = input;
        Gdx.input.setInputProcessor(new GestureDetector(inputManager));
    }

    //-------------------------Functions-------------------------//
    @Override
    public void render(SpriteBatch batch) {
        currentViewport.apply();

        batch.setProjectionMatrix(gameCamera.combined);
        batch.begin();
        currentMap.render(batch);
        renderGame();
        batch.end();

        currentMenuViewport.apply();
        batch.setProjectionMatrix(menuCamera.combined);
        batch.begin();
        renderMenu();
        batch.end();
    }

    protected abstract void renderMenu();
    protected abstract void renderGame();


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
        currentViewport.update(width - currentMenuSize, height, false);
        currentMenuViewport.update(currentMenuSize, height, false);
    }
}
