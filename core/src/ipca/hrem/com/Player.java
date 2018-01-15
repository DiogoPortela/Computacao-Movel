package ipca.hrem.com;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

import ipca.hrem.com.BasicResources.Date;
import ipca.hrem.com.BasicResources.GraphGrid;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.ObjectResources.Finance;
import ipca.hrem.com.ObjectResources.GameObject;

public class Player {
    //-------------------------Variables-------------------------//
    public OrthographicCamera gameCamera;
    public Map currentMap;
    public ArrayList<GameObject> allGameObjects;
    public Date date;
    public Finance income;
    public static GraphGrid graphPath;
    float score;
    boolean gameOver;


    //-------------------------GetSetters-------------------------//
    public float getScore() {return score; }

    public boolean isGameOver() {return gameOver;}

    public void setScore(float score) {this.score = score;}
    //-------------------------Constructor-------------------------//
    public Player(){
        gameCamera = new OrthographicCamera();
        currentMap = new Map(30, 30);
        allGameObjects = new ArrayList<GameObject>();
        date = new Date();
        graphPath = new GraphGrid();
        income= new Finance(100, 0, 0);
        gameOver=false;
        score=0;
    }
    //-------------------------Functions-------------------------//
}
