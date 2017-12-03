package ipca.hrem.com.BasicResources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

//Base Class for any State, use as an interface.
public abstract class State {

    Actor selectedActor = null;

    public abstract void update(float gameTime);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
    public abstract void resize(int width, int height);
}
