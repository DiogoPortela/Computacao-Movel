package ipca.hrem.com.ObjectResources;

import java.util.ArrayList;

/**
 * Created by Ferna on 28/12/2017.
 */

public class RestaurantBuilding {
    public ArrayList<Room> rooms;
    public  float size;

    public float getSize() {
        return size;
    }

    public float FreeSpaceCalculate(float size, ArrayList<Room> rooms)
    {
        float roomSizes=0;
        for(Room r:rooms)
        {
            roomSizes+=r.size;
        }
        return size-roomSizes;
    }
}
