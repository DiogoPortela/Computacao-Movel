package ipca.hrem.com.BasicResources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Base Class for any State, use as an interface.
public abstract class State {

    public abstract void update(float gameTime);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}
