package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ipca.hrem.com.BasicResources.State;
import ipca.hrem.com.GameState;


public class BasicInput extends InputManager {

    private final float MAX_ZOOM = 3.0f;
    private final float MIN_ZOOM = 0.5f;
    private final float MOVEMENT_SPEED = 0.01f;
    Vector3 touchPosition;

    public BasicInput(State currentState) {
        super(currentState);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if(x > GameState.getCurrentMenuSize()) {
            touchPosition = new Vector3(GameState.currentViewport.unproject(new Vector3(x, y, 0)));
        }
        else
        {
            touchPosition = new Vector3(GameState.currentMenuViewport.unproject(new Vector3(x, y, 0)));
        }
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(x > GameState.getCurrentMenuSize()){
            GameState.gameCamera.translate(deltaX * -MOVEMENT_SPEED * GameState.gameCamera.zoom, deltaY * MOVEMENT_SPEED * GameState.gameCamera.zoom);
            return true;
        }
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        GameState.gameCamera.zoom += (initialDistance - distance) * MOVEMENT_SPEED * 0.1 * Gdx.graphics.getDeltaTime();
        if (GameState.gameCamera.zoom < MIN_ZOOM)
            GameState.gameCamera.zoom = MIN_ZOOM;
        else if (GameState.gameCamera.zoom > MAX_ZOOM)
            GameState.gameCamera.zoom = MAX_ZOOM;

        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
