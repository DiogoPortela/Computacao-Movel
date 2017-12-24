package ipca.hrem.com.States;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.UIResources.Button;


public class MenuState extends State {
    //-------------------------Variables-------------------------//
    private Button playBtn;
    private Viewport currentViewport;
    private OrthographicCamera currentCamera;
    //-------------------------GetSetters-------------------------//

    public Viewport getCurrentViewport() {
        return currentViewport;
    }

    //-------------------------Constructor-------------------------//
    public MenuState() {
        currentCamera = new OrthographicCamera();
        currentViewport = new GameViewport(Point.Zero, new Vector2(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight()),GameState.gameScaleWidth, GameState.gameScaleHeight, currentCamera);
        currentViewport.apply();
        currentCamera.position.set(currentCamera.viewportWidth / 2.0f, currentCamera.viewportHeight / 2f, 0);
        playBtn = new Button("Tile002.png", new Vector2( getCurrentViewport().getWorldWidth() / 2.0f - 0.5f,getCurrentViewport().getWorldHeight() / 2.0f - 0.5f), new Vector2(1, 1), ""){
            @Override
            public void onClick() {
                MainGame.setCurrentState(new LiveState(2.5f));
            }
        };
        addUIObject(playBtn);
    }

    //-------------------------Functions-------------------------//
    @Override
    public void update(float gameTime) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        currentViewport.apply();
        spriteBatch.setProjectionMatrix(currentCamera.combined);
        spriteBatch.begin();
        playBtn.render(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        playBtn.dispose();
        currentCamera = null;
        currentViewport = null;
    }

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width, height);
    }
}
