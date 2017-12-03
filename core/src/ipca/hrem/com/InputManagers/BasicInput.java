package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.BasicResources.State;
import ipca.hrem.com.GameState;


public class BasicInput extends InputManager {

    private final int MAX_ZOOM = 3;
    private final int MIN_ZOOM = 1;
    private final float MOVEMENT_SPEED = 0.01f;

    public BasicInput(State currentState) {
        super(currentState);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
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
        GameState.camera.translate(deltaX * -MOVEMENT_SPEED * GameState.camera.zoom, deltaY * MOVEMENT_SPEED * GameState.camera.zoom);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        GameState.camera.zoom += (initialDistance - distance) * MOVEMENT_SPEED * Gdx.graphics.getDeltaTime();
        if (GameState.camera.zoom < MIN_ZOOM)
            GameState.camera.zoom = MIN_ZOOM;
        else if (GameState.camera.zoom > MAX_ZOOM)
            GameState.camera.zoom = MAX_ZOOM;

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
