package ipca.hrem.com.ObjectResources;

import java.util.HashMap;

/**
 * Created by Ferna on 03/01/2018.
 */

public class InventoryChoices {

    private HashMap<String, Integer> inventoryItemsQuantity;
    private HashMap<String, Float> inventoryItemsValue;

    public HashMap<String, Integer> getInventoryItemsQuantity() {
        return inventoryItemsQuantity;
    }
    public HashMap<String, Float> getInventoryItemsValue() {
        return inventoryItemsValue;
    }

    public InventoryChoices(int beef, int chicken, int fish, int greens, int potato, int rice, int beer, int juice, int water) {
        inventoryItemsQuantity = new HashMap<String, Integer>();
        inventoryItemsValue = new HashMap<String, Float>();

        inventoryItemsValue.put("Chicken", (float) 1.0);
        inventoryItemsValue.put("Beef", (float) 2.0);
        inventoryItemsValue.put("Fish", (float) 1.5);
        inventoryItemsValue.put("Potato", (float) 0.75);
        inventoryItemsValue.put("Rice", (float) 1.25);
        inventoryItemsValue.put("Greens", (float) 2.0);
        inventoryItemsValue.put("Beer", (float) 1.0);
        inventoryItemsValue.put("Water", (float) 0.5);
        inventoryItemsValue.put("Juice", (float) 2.0);

        inventoryItemsQuantity.put("Chicken", chicken);
        inventoryItemsQuantity.put("Beef", beef);
        inventoryItemsQuantity.put("Fish", fish);
        inventoryItemsQuantity.put("Potato", potato);
        inventoryItemsQuantity.put("Rice", rice);
        inventoryItemsQuantity.put("Greens", greens);
        inventoryItemsQuantity.put("Beer", beer);
        inventoryItemsQuantity.put("Water", water);
        inventoryItemsQuantity.put("Juice", juice);


    }
}
