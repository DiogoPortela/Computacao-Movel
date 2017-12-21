package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.Client;
import ipca.hrem.com.ObjectResources.Employee;
import ipca.hrem.com.ObjectResources.TouchableObject;
import ipca.hrem.com.States.GameState;
import ipca.hrem.com.ObjectResources.GameObject;
import ipca.hrem.com.ObjectResources.Item;


public class BasicInput extends InputManager {
    //-------------------------Variables-------------------------//
    private GameState currentGameState;
    private final float MAX_ZOOM = 3.0f;
    private final float MIN_ZOOM = 0.5f;
    private final float MOVEMENT_SPEED = 0.01f;
    Vector2 touchPosition;

    //-------------------------Constructor-------------------------/
    public BasicInput() {
        currentGameState = (GameState) MainGame.getCurrentState();
    }

    //-------------------------Functions-------------------------//
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        if(x > GameState.getCurrentMenuSize()) {            //SE ESTIVER NO VIWEPORT.
            Vector2 touchedPositionOnWorld = new Vector2(GameState.currentViewport.unproject(new Vector2(x, y)));
            TouchableObject touchableObjectSelectedThisFrame = currentGameState.findTouchedObject(touchedPositionOnWorld);
            if(currentGameState.getSelectedObject() == null)    //SE NAO HOUVER NADA SELECIONADO.
            {
                currentGameState.setSelectedObject(touchableObjectSelectedThisFrame);
                if(touchableObjectSelectedThisFrame == null){    //SE NAO HOUVE SELECÇAO AGORA.
                    //select ground floor.
                }
            }
            else{                                           //SE HOUVER ALGO SELECIONADO
                //SE CARREGARES NO CHAO MOVE.
                if(touchableObjectSelectedThisFrame == null && touchableObjectSelectedThisFrame.getClass() == GameObject.class){
                    ((GameObject)currentGameState.getSelectedObject()).act(touchedPositionOnWorld);
                 }
                //SE CARREGARES NOUTRO BONECO SELECIONA-O.
                else if(touchableObjectSelectedThisFrame.getClass() == Client.class || touchableObjectSelectedThisFrame.getClass() == Employee.class ){
                    currentGameState.setSelectedObject(touchableObjectSelectedThisFrame);
                }
                else{
                    //TOCAS NUM ITEM COM UM BONECO SELECTED.
                }
            }
        }
        else {
            touchPosition = new Vector2(GameState.currentMenuViewport.unproject(new Vector2(x, y)));
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
