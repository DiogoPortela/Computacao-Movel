package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.Client;
import ipca.hrem.com.ObjectResources.Employee;
import ipca.hrem.com.ObjectResources.Table;
import ipca.hrem.com.ObjectResources.TouchableObject;
import ipca.hrem.com.ObjectResources.UIResources.Button;
import ipca.hrem.com.ObjectResources.UIResources.UIObject;
import ipca.hrem.com.States.GameState;
import ipca.hrem.com.ObjectResources.GameObject;
import ipca.hrem.com.States.LiveState;


public class GameInput extends InputManager {
    //-------------------------Variables-------------------------//
    private LiveState currentState;

    //-------------------------Constructor-------------------------//
    public GameInput(LiveState currentState){
        this.currentState = currentState;
    }

    //-------------------------Functions-------------------------//
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        if (x > GameState.getCurrentMenuSizeScreen()) {            //SE ESTIVER NO VIWEPORT.
            Vector2 touchedPositionOnWorld = new Vector2(GameState.currentViewport.unproject(new Vector2(x, y)));
            TouchableObject touchableObjectSelectedThisFrame = currentState.findTouchedObject(touchedPositionOnWorld);
            if (currentState.getSelectedObject() == null)    //SE NAO HOUVER NADA SELECIONADO.
            {
                currentState.setSelectedObject(touchableObjectSelectedThisFrame);
                if (touchableObjectSelectedThisFrame == null) {    //SE NAO HOUVE SELECÇAO AGORA.
                    //select ground floor.
                }
            } else {                                           //SE HOUVER ALGO SELECIONADO
                //SE CARREGARES NO CHAO MOVE.
                if (touchableObjectSelectedThisFrame == null) {
                    ((GameObject) currentState.getSelectedObject()).act(touchedPositionOnWorld);
                }
                //SE CARREGARES NOUTRO BONECO SELECIONA-O.
                else if (touchableObjectSelectedThisFrame instanceof Client || touchableObjectSelectedThisFrame instanceof Employee) {
                    currentState.setSelectedObject(touchableObjectSelectedThisFrame);
                } else {
                    if(currentState.getSelectedObject() instanceof Client){
                        if(((Table)touchableObjectSelectedThisFrame).isUsed() == false){
                            ((Table)touchableObjectSelectedThisFrame).setUsed(true);
                            ((Client)currentState.getSelectedObject()).act(touchableObjectSelectedThisFrame);
                        }
                    }
                }
            }
        } else {                                                //SE ESTIVER NO MENU.
            Vector2 touchedPositionOnWorld = new Vector2(GameState.currentMenuViewport.unproject(new Vector2(x, y)));
            TouchableObject touchableObjectSelectedThisFrame = currentState.findTouchedObject(touchedPositionOnWorld);
            if (touchableObjectSelectedThisFrame instanceof Button)
                ((Button) touchableObjectSelectedThisFrame).onClick();

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
        if (x > GameState.getCurrentMenuSizeScreen()) {
            MainGame.currentPlayer.gameCamera.translate(deltaX * -MOVEMENT_SPEED * MainGame.currentPlayer.gameCamera.zoom, deltaY * MOVEMENT_SPEED * MainGame.currentPlayer.gameCamera.zoom);
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
