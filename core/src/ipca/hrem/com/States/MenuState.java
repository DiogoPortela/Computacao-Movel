package ipca.hrem.com.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ipca.hrem.com.MainGame;

public class MenuState extends State {

    public MenuState() {
    }

    @Override
    public void update(float gameTime) {
        MainGame.setCurrentState(new LiveState(150));
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
