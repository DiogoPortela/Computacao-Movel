package ipca.hrem.com.BasicResources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ipca.hrem.com.ObjectResources.Client;
import ipca.hrem.com.ObjectResources.Employee;
import ipca.hrem.com.ObjectResources.GameObject;
import ipca.hrem.com.ObjectResources.Item;

//Base Class for any State, use as an interface.
public abstract class State {
    //-------------------------Variables-------------------------//
    public GameObject selectedObject;
    protected ArrayList<GameObject> touchableUI;
    protected ArrayList<GameObject> touchableEmployees;
    protected ArrayList<GameObject> touchableItems;
    protected ArrayList<GameObject> touchableClients;

    //-------------------------GetSetters-------------------------//
    public GameObject getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(GameObject selectedObject) {
        this.selectedObject = selectedObject;
    }

    //-------------------------Constructor-------------------------//
    public State() {
        selectedObject = null;
        touchableUI = new ArrayList<GameObject>();
        touchableEmployees = new ArrayList<GameObject>();
        touchableItems = new ArrayList<GameObject>();
        touchableClients = new ArrayList<GameObject>();
    }

    //-------------------------Functions-------------------------//
    public void addGameObject(GameObject obj) {
        if (obj.getClass() == Employee.class) {
            touchableEmployees.add(obj);
        } else if (obj.getClass() == Item.class) {
            touchableItems.add(obj);
        } else if (obj.getClass() == Client.class) {
            touchableClients.add(obj);
        }
        return;
    }

    public boolean removeGameObject(GameObject obj) {

        if (obj.getClass() == Employee.class) {
            if (touchableEmployees.contains(obj)) {
                touchableEmployees.remove(obj);
                return true;
            }
        } else if (obj.getClass() == Item.class) {
            if (touchableItems.contains(obj)) {
                touchableItems.remove(obj);
                return true;

            }
        } else if (obj.getClass() == Client.class) {
            if (touchableClients.contains(obj)) {
                touchableClients.remove(obj);
                return true;

            }
        }
        return false;
    }

    public GameObject findTouchedObject(Vector2 worldPosition) {
        for (GameObject obj : touchableUI) {
            if (obj.getSprite().getBoundingRectangle().contains(worldPosition))
                return obj;
        }
        for (GameObject obj : touchableEmployees) {
            if (obj.getSprite().getBoundingRectangle().contains(worldPosition))
                return obj;
        }
        for (GameObject obj : touchableClients) {
            if (obj.getSprite().getBoundingRectangle().contains(worldPosition))
                return obj;
        }
        for (GameObject obj : touchableItems) {
            if (obj.getSprite().getBoundingRectangle().contains(worldPosition))
                return obj;
        }
        return null;
    }

    public abstract void update(float gameTime);

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void dispose();

    public abstract void resize(int width, int height);
}
