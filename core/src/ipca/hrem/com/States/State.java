package ipca.hrem.com.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.ObjectResources.TouchableObject;
import ipca.hrem.com.ObjectResources.UIResources.UIObject;

//Base Class for any State, use as an interface.
public abstract class State {
    //-------------------------Variables-------------------------//
    private TouchableObject selectedObject;
    private ArrayList<UIObject> UIObjects;

    //-------------------------GetSetters-------------------------//
    public TouchableObject getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(TouchableObject selectedObject) {
        this.selectedObject = selectedObject;
    }

    //-------------------------Constructor-------------------------//
    public State() {
        selectedObject = null;
        UIObjects = new ArrayList<UIObject>();
    }

    //-------------------------Functions-------------------------//
    public boolean addUIObject(UIObject obj) {
        if (!UIObjects.contains(obj)) {
            UIObjects.add(obj);
            return true;
        }
        return false;
    }

    public boolean removeUIObject(UIObject obj) {
        if (UIObjects.contains(obj)) {
            UIObjects.remove(obj);
            return true;
        }
        return false;
    }

    public TouchableObject findTouchedObject(Vector2 worldPosition) {
        for (UIObject obj : UIObjects) {
            if (obj.isVectorInside(worldPosition))
                return obj;
        }
        return null;
    }

    public void dispose() {
        for (UIObject obj : UIObjects) {
            obj.dispose();
        }
    }

    //-------------------------Abstracts-------------------------//

    public abstract void update(float gameTime);

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void resize(int width, int height);
}
