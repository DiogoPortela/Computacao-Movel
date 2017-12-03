package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.BasicResources.State;


public abstract class InputManager implements GestureListener {
    State currentState;

    public InputManager(State currentState) {
        this.currentState = currentState;
    }
}
