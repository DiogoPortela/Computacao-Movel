package ipca.hrem.com.ObjectResources;

import java.util.ArrayList;
import java.util.Collection;

import ipca.hrem.com.BasicResources.Grid;
import ipca.hrem.com.ObjectResources.InventoryChoices;
import ipca.hrem.com.Player;
import ipca.hrem.com.ResourceManagers.ClientGenerator;

/**
 * Created by Ferna on 28/12/2017.
 */

public class Finance {
    //Variveis
    public float initialMoney;
    public float currentMoney;
    public float inventoryValue;
    public float expectedIncome;

    private InventoryChoices inventoryChoices;

    //Getters and Setters
    public float getInitialMoney() {
        return initialMoney;
    }

    public float getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(float currentMoney) {
        this.currentMoney = currentMoney;
    }

    public float getInventoryValue() {
        return inventoryValue;
    }

    public void setInventoryValue(float inventoryValue) {
        this.inventoryValue = inventoryValue;
    }

    public Finance(float initialMoney, float inventoryValue, float expectedIncome) {
        this.initialMoney = initialMoney;
        this.currentMoney = initialMoney;
        this.inventoryValue = 0;
        this.expectedIncome = 0;
    }

    public void currentMoneyCalculation(float currentMoney, ArrayList<Client[]> clients) {
        for (Client[] c : clients
                ) {
            for (Client client : c
                    ) {
                if (client.getPatience() > 0 && client.isLeaving())
                    currentMoney += client.getMoney() + client.getPatience() / 10;
                else if (client.getPatience() <= 0 && client.isLeaving())
                    currentMoney -= client.getMoney();
            }
        }
    }

    public void ScoreCalculation(Player player, float time) {
        float currentScore = player.getScore();
        if (!player.isGameOver()) {
            currentScore += (int) (player.income.currentMoney * time);
            player.setScore(currentScore);

        }

    }

    public float getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(float expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    //Funcoes
    public boolean FinanceForTerrainCheck(Room room, Player player) {
        if (player.income.currentMoney != 0 && FinanceGridSizeCheck(room.grid, room.getPriceForTile(), player)) {
            return true;
        } else
            return false;
    }

    public boolean FinanceGridSizeCheck(Grid grid, float priceForTile, Player player) {
        if (grid.getGridWidth() * grid.getGridHeight() * priceForTile >
                FinanceForTerrain(player.income.initialMoney))
            return false;
        else
            return true;
    }

    public int FinanceForTerrain(float playerIncome) {
        return (int) playerIncome / 3;
    }

    public float Expenses(InventoryChoices inventoryChoices, float currentMoney, float inventoryValue) {
        float expenses = 0;
        Collection<Integer> quantaties = inventoryChoices.getInventoryItemsQuantity().values();
        Collection<Float> values = inventoryChoices.getInventoryItemsValue().values();
        for (int i : quantaties) {
            for (float f : values) {
                expenses += i * f;
            }
        }
        return expenses;
    }

    public static float ExpectedDemand(InventoryChoices inventoryChoices, ClientGenerator clientGenerator) {
        int numbClients = clientGenerator.getNumbClients();
        int numbItems = 0;
        Collection<Integer> quantaties = inventoryChoices.getInventoryItemsQuantity().values();
        for (int i : quantaties) {
            numbItems += i;
        }
        return numbItems / numbClients;
    }


}
