package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.input.GestureDetector.GestureListener;



public abstract class InputManager implements GestureListener {
    protected final float MAX_ZOOM = 3.0f;
    protected final float MIN_ZOOM = 0.5f;
    protected final float MOVEMENT_SPEED = 0.01f;

}
