package ipca.hrem.com.States;


import ipca.hrem.com.InputManagers.BasicInput;
import ipca.hrem.com.InputManagers.InputManager;

public class LiveState extends GameState{


    public LiveState(int menuSize, InputManager input) {
        super(menuSize, input);
    }

    public LiveState(BuildState build){
        super(build.getCurrentMenuSize(), new BasicInput());
    }

    @Override
    public void update(float gameTime) {

    }

    @Override
    protected void renderMenu() {

    }

    @Override
    protected void renderGame() {

    }
}
