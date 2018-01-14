package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ipca.hrem.com.ResourceManagers.TextureManager;

/**
 * Created by Ferna on 02/01/2018.
 */

public class Kitchen extends RoomAsActor {
    private int numbItems;
    //private int numbMaxItems;
    //private float workFlowBathroom;
    private String name;

    public Kitchen(Vector2 position, float size) {
        super(position, size);

        this.numbItems= 6;

    }

    private void LoadBathRoomTextures(float numbItems)
    {
        for(int i=0; i<=numbItems;i++)
        {
            this.roomObjects.add(new Image(TextureManager.getTexture(name)));
        }
    }
}
