package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.MainGame;
import ipca.hrem.com.States.BuildState;

public class BuildInput extends InputManager {
    //-------------------------Variables-------------------------//
    BuildState currentState;

    //-------------------------GetSetters-------------------------//

    //-------------------------Constructor-------------------------//
    public BuildInput(BuildState state){
        currentState = state;
    }
    //-------------------------Functions-------------------------//

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
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        MainGame.currentPlayer.gameCamera.zoom += (initialDistance - distance) * MOVEMENT_SPEED * 0.1 * Gdx.graphics.getDeltaTime();
        if (MainGame.currentPlayer.gameCamera.zoom < MIN_ZOOM)
            MainGame.currentPlayer.gameCamera.zoom = MIN_ZOOM;
        else if (MainGame.currentPlayer.gameCamera.zoom > MAX_ZOOM)
            MainGame.currentPlayer.gameCamera.zoom = MAX_ZOOM;

        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
