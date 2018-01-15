package ipca.hrem.com.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.UIResources.Button;

public class ScoreState extends State {

    private Button back;
    private Viewport currentViewport;
    private OrthographicCamera currentCamera;

    private BitmapFont bitmapFont;

    public Viewport getCurrentViewport() {
        return currentViewport;
    }

    public ScoreState(){
        currentCamera = new OrthographicCamera();
        currentViewport = new GameViewport(Point.Zero, new Vector2(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight()),GameState.gameScaleWidth, GameState.gameScaleHeight, currentCamera);
        currentViewport.apply();
        currentCamera.position.set(currentCamera.viewportWidth / 2.0f, currentCamera.viewportHeight / 2f, 0);
        back = new Button( new Vector2( getCurrentViewport().getWorldWidth() / 2.0f - 0.5f,getCurrentViewport().getWorldHeight() / 2.0f + 1.5f), new Vector2(2, 1), new Vector2(64, 96), new Vector2(64, 32)){
            @Override
            public void onClick() {
                MainGame.setCurrentState(new LiveState(2.5f));
            }
        };
        addUIObject(back);

        bitmapFont = new BitmapFont(Gdx.files.internal("anotherarialsmaller.fnt"));
        bitmapFont.getData().setScale(0.05f);
    }

    @Override
    public void update(float gameTime) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        currentViewport.apply();
        spriteBatch.setProjectionMatrix(currentCamera.combined);
        spriteBatch.begin();
        back.render(spriteBatch);
        bitmapFont.draw(spriteBatch, Float.toString(MainGame.currentPlayer.score), getCurrentViewport().getWorldWidth() / 2.0f - 0.5f,getCurrentViewport().getWorldHeight() / 2.0f + 1.5f);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }
}
