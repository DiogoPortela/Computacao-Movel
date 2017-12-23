package ipca.hrem.com.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.InputManagers.GameInput;
import ipca.hrem.com.InputManagers.InputManager;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.GameObject;
import ipca.hrem.com.ObjectResources.UIResources.StaticTexture;

//GameLogic goes here
public abstract class GameState extends State {
    //-------------------------Constants-------------------------//
    public final static float gameScaleWidth = 15.0f;
    public final static float gameScaleHeight = gameScaleWidth * ((float) Gdx.graphics.getBackBufferHeight() / (float) Gdx.graphics.getBackBufferWidth());

    //-------------------------Variables-------------------------//
    public static Map currentMap;
    public static OrthographicCamera gameCamera, menuCamera;
    public static GameViewport currentViewport, currentMenuViewport;
    private static int currentMenuSizeScreen;
    private static float currentMenuSizeWorld;

    protected static Float timeSpeed;

    protected static ArrayList<GameObject> allGameObjects;

    private static StaticTexture menuBar;
    private static StaticTexture menuBackground;
    //private static Label timeLabel;

    //-------------------------GetSetters-------------------------//
    public static int getCurrentMenuSize() {
        return currentMenuSizeScreen;
    }

    public static void setCurrentMenuSize(float menuSize) {
        currentMenuSizeWorld = menuSize;
        currentMenuSizeScreen = (int)(menuSize * Gdx.graphics.getBackBufferWidth() / gameScaleWidth);
        //currentViewport.setScreenPosition(currentMenuSizeScreen, 0);
        //currentViewport.setScreenWidth(Gdx.graphics.getBackBufferWidth() - currentMenuSizeScreen);

        //currentMenuViewport.setScreenWidth(currentMenuSizeScreen);

        currentViewport.update(new Point(currentMenuSizeScreen, 0), new Point(Gdx.graphics.getBackBufferWidth() - currentMenuSizeScreen, Gdx.graphics.getBackBufferHeight()));
        currentMenuViewport.update(Point.Zero, new Point(currentMenuSizeScreen,  Gdx.graphics.getBackBufferHeight()));
        gameCamera.update();
        menuCamera.update();
    }

    //-------------------------Constructor-------------------------//
    public GameState(float menuSize) {
        currentMap = new Map(20, 20);

        gameCamera = new OrthographicCamera();
        currentViewport = new GameViewport(Point.Zero, new Vector2(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, gameCamera);
        currentViewport.apply();

        menuCamera = new OrthographicCamera();
        currentMenuViewport = new GameViewport(Point.Zero, new Vector2(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, menuCamera);
        currentMenuViewport.apply();
        setCurrentMenuSize(menuSize);

        gameCamera.position.set(gameCamera.viewportWidth / 2.0f, gameCamera.viewportHeight / 2.0f, 0);
        menuCamera.position.set(menuCamera.viewportWidth / 2.0f, menuCamera.viewportHeight / 2.0f, 0);

        allGameObjects = new ArrayList<GameObject>();
        timeSpeed = 1.0f;

        menuBar = new StaticTexture("MenuBar.png", new Vector2(currentMenuSizeWorld - 0.5f, 0), new Vector2(0.5f, gameScaleHeight));
        menuBackground = new StaticTexture("MenuColorPixel.png", new Vector2(0, 0), new Vector2(currentMenuSizeWorld - 0.5f, gameScaleHeight));
        //timeLabel = new Label(timeSpeed.toString(), MainGame.testLabel);
        //timeLabel.setPosition(2,2);
    }

    public GameState() {
    }

    //-------------------------Functions-------------------------//
    @Override
    public void render(SpriteBatch batch) {
        currentViewport.apply();
        batch.setProjectionMatrix(gameCamera.combined);
        batch.begin();
        currentMap.render(batch);
        renderGame(batch);
        batch.end();

        currentMenuViewport.apply();
        batch.setProjectionMatrix(menuCamera.combined);
        batch.begin();
        menuBar.render(batch);
        menuBackground.render(batch);
        //timeLabel.draw(batch, 1);
        renderMenu(batch);
        batch.end();
    }

    protected abstract void renderMenu(SpriteBatch batch);

    protected abstract void renderGame(SpriteBatch batch);

    @Override
    public void dispose() {
        gameCamera = null;
        menuCamera = null;
        currentViewport = null;
        currentMenuViewport = null;
        currentMap.dispose();
        for (GameObject obj: allGameObjects) {
            obj.dispose();
        }
        allGameObjects.clear();
        menuBar.dispose();
        menuBackground.dispose();
    }

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width - currentMenuSizeScreen, height, false);
        currentMenuViewport.update(currentMenuSizeScreen, height, false);
    }
}
