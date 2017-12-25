package ipca.hrem.com;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

import ipca.hrem.com.BasicResources.Date;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.ObjectResources.GameObject;

public class Player {
    //-------------------------Variables-------------------------//
    public OrthographicCamera gameCamera;
    public Map currentMap;
    public ArrayList<GameObject> allGameObjects;
    public Date date;
    //-------------------------GetSetters-------------------------//

    //-------------------------Constructor-------------------------//
    public Player(){
        gameCamera = new OrthographicCamera();
        currentMap = new Map(30, 30);
        allGameObjects = new ArrayList<GameObject>();
        date = new Date();
    }
    //-------------------------Functions-------------------------//
}
