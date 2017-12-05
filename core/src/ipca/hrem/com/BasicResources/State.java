package ipca.hrem.com.BasicResources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import ipca.hrem.com.ObjectResources.GameObject;

//Base Class for any State, use as an interface.
public abstract class State {
    //-------------------------Variables-------------------------//
    public GameObject selectedObject;
    protected ArrayList<GameObject> touchableObjects;

    //-------------------------GetSetters-------------------------//
    public GameObject getSelectedObject() {
        return selectedObject;
    }
    public void setSelectedObject(GameObject selectedObject) {
        this.selectedObject = selectedObject;
    }

    //-------------------------Constructor-------------------------//
    public State(){
        selectedObject = null;
        touchableObjects = new ArrayList<GameObject>();
    }

    //-------------------------Functions-------------------------//
    public GameObject findTouchedObject(Vector2 worldPosition){
        for (GameObject obj: touchableObjects) {
            if(obj.getSprite().getBoundingRectangle().contains(worldPosition))
                return obj;
        }
        return null;
    }
    public abstract void update(float gameTime);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
    public abstract void resize(int width, int height);
}
