package ipca.hrem.com.ObjectResources;

import ipca.hrem.com.BasicResources.Grid;
import ipca.hrem.com.Player;

/**
 * Created by Ferna on 28/12/2017.
 */

public class Finance {
    //Variveis
    public float initialMoney;
    public float currentMoney;
    public float inventoryValue;
    public float expectedIncome;

    //Getters and Setters
    public float getInitialMoney() {
        return initialMoney;
    }

    public float getCurrentMoney() {return currentMoney;}

    public void setCurrentMoney(float currentMoney) {this.currentMoney = currentMoney;}

    public float getInventoryValue() {return inventoryValue;}

    public void setInventoryValue(float inventoryValue) {this.inventoryValue = inventoryValue;}

    public Finance(float initialMoney, float inventoryValue, float expectedIncome) {
        this.initialMoney = initialMoney;
        this.currentMoney = initialMoney;
        this.inventoryValue = 0;
        this.expectedIncome = 0;
    }

    public float getExpectedIncome() {return expectedIncome;}

    public void setExpectedIncome(float expectedIncome) {this.expectedIncome = expectedIncome;}

    //Funcoes
    public boolean FinanceForTerrainCheck(Room room, Player player) {
        if (player.income.currentMoney != 0 && FinanceGridSizeCheck(room.grid, room.getPriceForTile(), player)) {
            return true;
        } else
            return false;
    }

    public boolean FinanceGridSizeCheck(Grid grid, float priceForTile, Player player) {
        if (grid.getGridWidth() * grid.getGridHeight() * priceForTile>
        FinanceForTerrain(player.income.initialMoney))
        return false;
        else
        return true;
    }

    public int FinanceForTerrain(float playerIncome) {
        return (int) playerIncome / 3;
    }


}
