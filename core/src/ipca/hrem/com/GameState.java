package ipca.hrem.com;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Button;

import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.BasicResources.GridCell;
import ipca.hrem.com.InputManagers.BasicInput;
import ipca.hrem.com.InputManagers.InputManager;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.BasicResources.State;
import ipca.hrem.com.ObjectResources.Client;
import ipca.hrem.com.ObjectResources.Employee;
import ipca.hrem.com.ObjectResources.GameObject;

//GameLogic goes here
public class GameState extends State {

    public final static float gameScaleWidth = 15.0f;
    public final static float gameScaleHeight = gameScaleWidth * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
    //-------------------------Variables-------------------------//
    public static Map currentMap;
    public static OrthographicCamera gameCamera, menuCamera;
    public static Viewport currentViewport, currentMenuViewport;
    private static InputManager inputManager;

    private static int currentMenuSize;


    Client c;
    Employee e;
    GridCell test;

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
        currentViewport = new GameViewport(new Point(currentMenuSize, 0), new Vector2(Gdx.graphics.getBackBufferWidth() - currentMenuSize, Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, gameCamera);
        currentViewport.apply();
        gameCamera.position.set(gameCamera.viewportWidth / 2.0f, gameCamera.viewportHeight / 2f, 0);

        menuCamera = new OrthographicCamera();
        currentMenuViewport = new GameViewport(Point.Zero, new Vector2(currentMenuSize, Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, menuCamera);
        currentMenuViewport.apply();
        menuCamera.position.set(menuCamera.viewportWidth / 2f, menuCamera.viewportHeight / 2f, 0);

        inputManager = new BasicInput(this);
        Gdx.input.setInputProcessor(new GestureDetector(inputManager));

        c = new Client(new Vector2(10, 10), 1);
        this.addGameObject(c);
        e = new Employee(new Vector2(5, 5), 1);
        this.addGameObject(e);
        test = new GridCell(GridCell.CellType.test, new Vector2(currentMenuViewport.getWorldWidth() / 2.0f - 0.5f , gameScaleHeight - 1.2f), 1);

    }

    //-------------------------Functions-------------------------//
    @Override
    public void update(float gameTime) {
        c.update(gameTime);
        e.update(gameTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        currentViewport.apply();

        batch.setProjectionMatrix(gameCamera.combined);
        batch.begin();
        currentMap.render(batch);
        for (GameObject client : touchableClients) {
            client.render(batch);
        }
        for (GameObject employee : touchableEmployees) {
            employee.render(batch);
        }
        for (GameObject item : touchableItems) {
            item.render(batch);
        }
        batch.end();

        currentMenuViewport.apply();
        batch.setProjectionMatrix(menuCamera.combined);
        batch.begin();
        //MenuDraw
        test.render(batch);
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
        currentViewport.update(width - currentMenuSize, height, false);
        currentMenuViewport.update(currentMenuSize, height, false);
    }
}
