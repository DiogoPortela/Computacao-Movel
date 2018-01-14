package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.math.Vector2;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by Ferna on 02/01/2018.
 */

public class Restaurant {
    Group restaurant;
    private ArrayList<RoomAsActor>  rooms;
    private Kitchen kitchen;
    private BathRoom bathroom;
    private MainHall mainHall;
     Vector2 somePosition;

    public Restaurant()
    {
        rooms= new ArrayList<RoomAsActor>();
    kitchen= new Kitchen(somePosition,10 );
    rooms.add(kitchen);
    bathroom= new BathRoom(somePosition, 10);
    rooms.add(bathroom);
    mainHall= new MainHall(somePosition, 10);
    rooms.add(mainHall);
    }
}
