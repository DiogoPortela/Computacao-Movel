package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.BasicResources.Grid;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.GridCell;
import ipca.hrem.com.ObjectResources.TouchableObject;
import ipca.hrem.com.ObjectResources.UIResources.Button;
import ipca.hrem.com.ObjectResources.UIResources.UIObject;
import ipca.hrem.com.States.BuildState;
import ipca.hrem.com.States.GameState;

public class BuildInput extends InputManager {
    //-------------------------Variables-------------------------//
    BuildState currentState;
    GridCell.CellType currentSelectedCellType;
    boolean isBuildingWalls;
    Vector2 firstPosition;

    //-------------------------GetSetters-------------------------//

    public void setCurrentSelectedCellType(GridCell.CellType currentSelectedCellType) {
        this.currentSelectedCellType = currentSelectedCellType;
    }

    public void setBuildingWalls(boolean buildingWalls) {
        isBuildingWalls = buildingWalls;
    }

    public void toggleBuildWalls() {
        isBuildingWalls = !isBuildingWalls;
    }

    //-------------------------Constructor-------------------------//
    public BuildInput(BuildState state) {
        currentState = state;
        isBuildingWalls = false;
        firstPosition = null;
    }
    //-------------------------Functions-------------------------//

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if (isBuildingWalls && x > GameState.getCurrentMenuSizeScreen()) {
            firstPosition = new Vector2(GameState.currentViewport.unproject(new Vector2(x, y)));
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (x > GameState.getCurrentMenuSizeScreen()) {            //SE ESTIVER NO VIWEPORT.
            Vector2 touchedPositionOnWorld = new Vector2(GameState.currentViewport.unproject(new Vector2(x, y)));
            if (currentState.getSelectedObject() != null && !isBuildingWalls) {
                MainGame.currentPlayer.currentMap.setGridCell(Point.fromVector2(touchedPositionOnWorld), currentSelectedCellType);
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
        if (!isBuildingWalls) {
            if (x > GameState.getCurrentMenuSizeScreen()) {
                MainGame.currentPlayer.gameCamera.translate(deltaX * -MOVEMENT_SPEED * MainGame.currentPlayer.gameCamera.zoom, deltaY * MOVEMENT_SPEED * MainGame.currentPlayer.gameCamera.zoom);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if (isBuildingWalls) {
            Vector2 touchedPositionOnWorld = new Vector2(GameState.currentViewport.unproject(new Vector2(x, y)));
            Grid newGrid = new Grid(Grid.GridType.interior, firstPosition, touchedPositionOnWorld);
            return true;
        } else {

        }
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (!isBuildingWalls) {
            MainGame.currentPlayer.gameCamera.zoom += (initialDistance - distance) * MOVEMENT_SPEED * 0.1 * Gdx.graphics.getDeltaTime();
            if (MainGame.currentPlayer.gameCamera.zoom < MIN_ZOOM)
                MainGame.currentPlayer.gameCamera.zoom = MIN_ZOOM;
            else if (MainGame.currentPlayer.gameCamera.zoom > MAX_ZOOM)
                MainGame.currentPlayer.gameCamera.zoom = MAX_ZOOM;
        }
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
