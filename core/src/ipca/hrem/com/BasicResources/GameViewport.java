package ipca.hrem.com.BasicResources;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameViewport extends Viewport{

    private float worldWidthPermanent;
    private float worldHeightPermanent;

    public GameViewport(Point screenPosition, Vector2 screenSize, float worldWidth, float worldHeight, Camera camera) {
        float widthRatio = screenSize.x / (float)Gdx.graphics.getBackBufferWidth();
        float heightRatio = screenSize.y / (float)Gdx.graphics.getBackBufferHeight();
        worldWidthPermanent = worldWidth;
        worldHeightPermanent = worldHeight;

        setWorldSize(worldWidth * widthRatio, worldHeight * heightRatio);
        setScreenBounds(screenPosition.X, screenPosition.Y, (int)screenSize.x, (int)screenSize.y);
        setCamera(camera);
    }

    @Override
    public void update(int screenWidth, int screenHeight, boolean centerCamera) {
        super.update(screenWidth, screenHeight, centerCamera);
        float widthRatio = screenWidth / (float)Gdx.graphics.getBackBufferWidth();
        float heightRatio = screenHeight / (float)Gdx.graphics.getBackBufferHeight();
        setWorldSize(worldWidthPermanent * widthRatio, worldHeightPermanent * heightRatio);
    }

    public  void update(Point screenPosition, Point screenSize){
        super.update(screenSize.X, screenSize.Y, false);
        float widthRatio = screenSize.X / (float)Gdx.graphics.getBackBufferWidth();
        float heightRatio = screenSize.Y / (float)Gdx.graphics.getBackBufferHeight();
        setWorldSize(worldWidthPermanent * widthRatio, worldHeightPermanent * heightRatio);
        setScreenBounds(screenPosition.X, screenPosition.Y, screenSize.X, screenSize.Y);
    }

}
