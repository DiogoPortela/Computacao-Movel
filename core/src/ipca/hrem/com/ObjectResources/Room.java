package ipca.hrem.com.ObjectResources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

import ipca.hrem.com.BasicResources.Grid;

/**
 * Created by Ferna on 28/12/2017.
 */

public class Room {
    //Variaveis globais
    public static final float CELL_SCALE = 1.0f;
    public Grid grid;
    protected HashMap<String, Texture> objects;
    protected float size;
    protected float roomSizeValue;
    protected float maxSize;
    protected Vector2 posicao;
    protected float priceForTile;
    public float rateOfWork;
    public int minItemsNumber;
    public int maxItemsNumber;

    //Getters and Setters


    public float getSize() {return size;}

    public void setSize(float size) {this.size = size;}

    public float getRoomSizeValue() {return roomSizeValue;}

    public void setRoomSizeValue(float roomSizeValue) {this.roomSizeValue = roomSizeValue;}

    public float getMaxSize() {return maxSize;}

    public Vector2 getPosicao() {return posicao;}

    public void setPosicao(Vector2 posicao) {this.posicao = posicao;}

    public float getPriceForTile() {return priceForTile;}

    public float getRateOfWork() {return rateOfWork;}

    public int getMinItemsNumber() {return minItemsNumber;}

    public int getMaxItemsNumber() {return maxItemsNumber;}

    public Room(float size, float roomSizeValue, Vector2 posicao, float rateOfWork, int minItemsNumber, int maxItemsNumber, RestaurantBuilding restaurant) {
        this.size = size;
        this.maxSize = MaxSizeCaculate(size,roomSizeValue,restaurant);
        this.posicao = posicao;
        this.priceForTile = 0;// Tera de ser definido em cada tipo de room
        this.rateOfWork = RateOfWorkCalculate(maxSize, size);
        this.minItemsNumber = 0;
        this.maxItemsNumber = 0;
        objects = new HashMap<String, Texture>();
    }
    public boolean CheckIfFits(float size, float maxSize)
    {
        if(size>maxSize)
            return false;
        else
            return true;
    }
    public float MaxSizeCaculate(float size,float roomSizeValue, RestaurantBuilding restaurant)
    {
        return restaurant.getSize()*roomSizeValue;
    }
    public float RateOfWorkCalculate(float maxSize, float size)
    {
        return size/maxSize;
    }
}
