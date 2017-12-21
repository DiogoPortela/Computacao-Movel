package ipca.hrem.com.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ipca.hrem.com.ObjectResources.GameObject;
import ipca.hrem.com.ObjectResources.TouchableObject;
import ipca.hrem.com.ObjectResources.UIResources.UIObject;

//Base Class for any State, use as an interface.
public abstract class State {
    //-------------------------Variables-------------------------//
    public TouchableObject selectedObject;
    protected ArrayList<TouchableObject> touchableUI;

    //-------------------------GetSetters-------------------------//
    public Object getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(TouchableObject selectedObject) {
        this.selectedObject = selectedObject;
    }

    protected ArrayList<TouchableObject> getTouchableUI() {
        return touchableUI;
    }

    protected void setTouchableUI(ArrayList<TouchableObject> touchableUI) {
        this.touchableUI = touchableUI;
    }

    //-------------------------Constructor-------------------------//
    public State() {
        selectedObject = null;
        touchableUI = new ArrayList<TouchableObject>();
    }

    public State(State lastState) {
        selectedObject = null;
        touchableUI = lastState.getTouchableUI();
    }

    //-------------------------Functions-------------------------//
    public boolean addUIObject(UIObject obj) {
        if (!touchableUI.contains(obj)) {
            touchableUI.add(obj);
            return true;
        }
        return false;
    }

    public boolean removeUIObject(UIObject obj) {
        if (touchableUI.contains(obj)) {
            touchableUI.remove(obj);
            return true;
        }
        return false;
    }

    public UIObject findTouchedObject(Vector2 worldPosition) {
        for (TouchableObject obj : touchableUI) {
            if (obj.isVectorInside(worldPosition))
                return (UIObject)obj;
        }
        return null;
    }

    public abstract void update(float gameTime);

    public abstract void render(SpriteBatch spriteBatch);

    public void dispose() {
        for (TouchableObject obj : touchableUI) {
            obj.dispose();
        }
    }

    public abstract void resize(int width, int height);
}
