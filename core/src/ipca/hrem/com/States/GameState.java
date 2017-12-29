package ipca.hrem.com.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ipca.hrem.com.BasicResources.Date;
import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.BasicResources.Grid;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.GameObject;
import ipca.hrem.com.ResourceManagers.TextureManager;
import sun.applet.Main;

//GameLogic goes here
public abstract class GameState extends State {
    //-------------------------Constants-------------------------//
    public final static float gameScaleWidth = 15.0f;
    public final static float gameScaleHeight = gameScaleWidth * ((float) Gdx.graphics.getBackBufferHeight() / (float) Gdx.graphics.getBackBufferWidth());

    //-------------------------Variables-------------------------//
    public static OrthographicCamera menuCamera;
    public static GameViewport currentViewport, currentMenuViewport;
    private static int currentMenuSizeScreen;
    private static float currentMenuSizeWorld;

    protected static float timeSpeed;

    private static Sprite menuBar;
    private static Sprite menuBackground;

    private int[][] debugRestaurant = new int[][]{
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 4, 4, 4, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 4, 4, 4, 1},
            {1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 4, 4, 4, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    //-------------------------GetSetters-------------------------//
    public static int getCurrentMenuSizeScreen() {
        return currentMenuSizeScreen;
    }

    public static float getCurrentMenuSizeWorld() {
        return currentMenuSizeWorld;
    }

    public static void setCurrentMenuSize(float menuSize) {
        currentMenuSizeWorld = menuSize;
        currentMenuSizeScreen = (int) (menuSize * Gdx.graphics.getBackBufferWidth() / gameScaleWidth);
        currentViewport.update(new Point(currentMenuSizeScreen, 0), new Point(Gdx.graphics.getBackBufferWidth() - currentMenuSizeScreen, Gdx.graphics.getBackBufferHeight()));
        currentMenuViewport.update(Point.Zero, new Point(currentMenuSizeScreen, Gdx.graphics.getBackBufferHeight()));

        menuBackground.setSize(currentMenuSizeWorld - 0.5f, gameScaleHeight);
        menuBar.setPosition(currentMenuSizeWorld - 0.5f, 0);

        menuCamera.position.set(menuCamera.viewportWidth / 2.0f, menuCamera.viewportHeight / 2.0f, 0);

        MainGame.currentPlayer.gameCamera.update();
        menuCamera.update();
    }

    //-------------------------Constructor-------------------------//
    public GameState(float menuSize) {
        menuBar = new Sprite(TextureManager.loadTexture("MenuBar.png"));
        menuBackground = new Sprite(TextureManager.loadTexture("MenuColorPixel.png"));

        currentViewport = new GameViewport(Point.Zero, new Vector2(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, MainGame.currentPlayer.gameCamera);
        currentViewport.apply();

        menuCamera = new OrthographicCamera();
        currentMenuViewport = new GameViewport(Point.Zero, new Vector2(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, menuCamera);
        currentMenuViewport.apply();
        setCurrentMenuSize(menuSize);

        MainGame.currentPlayer.gameCamera.position.set(MainGame.currentPlayer.gameCamera.viewportWidth / 2.0f, MainGame.currentPlayer.gameCamera.viewportHeight / 2.0f, 0);

        menuBar.setSize(0.5f, gameScaleHeight);
        menuBackground.setPosition(0, 0);

        timeSpeed = 1.0f;

        MainGame.currentPlayer.currentMap.getGridList().add(new Grid(debugRestaurant, 17, 15, new Point(10, 5)));
    }

    public GameState() {
    }

    //-------------------------Functions-------------------------//
    @Override
    public void render(SpriteBatch batch) {
        currentViewport.apply();
        batch.setProjectionMatrix(MainGame.currentPlayer.gameCamera.combined);
        batch.begin();
        MainGame.currentPlayer.currentMap.render(batch);
        renderGame(batch);
        batch.end();

        currentMenuViewport.apply();
        batch.setProjectionMatrix(menuCamera.combined);
        batch.begin();
        menuBar.draw(batch);
        menuBackground.draw(batch);
        renderMenu(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        menuCamera = null;
        currentViewport = null;
        currentMenuViewport = null;
        menuBar.getTexture().dispose();
        menuBackground.getTexture().dispose();
    }

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width - currentMenuSizeScreen, height, false);
        currentMenuViewport.update(currentMenuSizeScreen, height, false);
    }

    //-------------------------Abstracts-------------------------//
    protected abstract void renderMenu(SpriteBatch batch);

    protected abstract void renderGame(SpriteBatch batch);
}
