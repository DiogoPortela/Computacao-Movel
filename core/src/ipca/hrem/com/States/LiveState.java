package ipca.hrem.com.States;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.Client;
import ipca.hrem.com.ObjectResources.GameObject;
import ipca.hrem.com.ObjectResources.UIResources.Button;
import ipca.hrem.com.ObjectResources.UIResources.Clock;
import ipca.hrem.com.ResourceManagers.ClientGenerator;
import sun.applet.Main;

public class LiveState extends GameState {
    //-------------------------Variables-------------------------//
    private Button timeBtn;
    private Button timeFastBtn;
    private Button managerStateBtn;
    private Button buildStateBtn;
    private Button menuStateBtn;
    private Clock clock;

    private ArrayList<Client[]> clients;
    ClientGenerator clientGenerator;

    private ArrayList<GameObject> toRemove;


    //-------------------------GetSetters-------------------------//


    public ArrayList<Client[]> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client[]> clients) {
        this.clients = clients;
    }

    //-------------------------Constructor-------------------------//
    public LiveState(float menuSize) {
        super(menuSize);
        onCreate();

    }

    public LiveState() {
        setCurrentMenuSize(2.5f);
        onCreate();
    }

    //-------------------------Functions-------------------------//

    private void onCreate(){
        clock = new Clock(new Vector2(0.1f, GameState.gameScaleHeight - 1.2f), new Vector2(2.0f, 1.0f));

        timeBtn = new Button(new Vector2(0.05f, clock.getPosition().y - 1.2f), new Vector2(1.0f, 1.0f), new Vector2( 32 , 0), new Vector2(32, 32)) {
            @Override
            public void onClick() {
                if (timeSpeed > 0) {
                    timeSpeed = 0;
                    this.changeTexture(new Vector2(0,0), new Vector2(32, 32));
                } else {
                    timeSpeed = 1;
                    this.changeTexture(new Vector2(32,0), new Vector2(32, 32));
                }
            }
        };
        addUIObject(timeBtn);

        timeFastBtn = new Button( new Vector2(1.1f, clock.getPosition().y - 1.2f), new Vector2(1.0f, 1.0f), new Vector2(64, 0), new Vector2(32, 32)) {
            @Override
            public void onClick() {
                if (timeSpeed == 1) {
                    timeSpeed = 2;
                }else if (timeSpeed == 2){
                    timeSpeed = 4;
                }else if (timeSpeed == 4){
                    timeSpeed = 1;
                }
            }
        };
        addUIObject(timeFastBtn);

        buildStateBtn = new Button(new Vector2(0.1f, timeBtn.getPosition().y - 1.1f), new Vector2(2.0f, 1.0f), new Vector2(0, 32 ), new Vector2(64, 32)) {
            @Override
            public void onClick() {
                MainGame.setCurrentState(new BuildState());
            }
        };
        addUIObject(buildStateBtn);

        managerStateBtn = new Button(new Vector2(0.1f, buildStateBtn.getPosition().y - 1.1f), new Vector2(2.0f, 1.0f), new Vector2(64, 32 ), new Vector2(64, 32)) {
            @Override
            public void onClick() {
            }
        };
        addUIObject(managerStateBtn);

        menuStateBtn = new Button(new Vector2(0.1f, 0.1f), new Vector2(2.0f, 1.0f), new Vector2(0, 64 ), new Vector2(64, 32)) {
            @Override
            public void onClick() {
                MainGame.currentPlayer.income.currentMoneyCalculation(MainGame.currentPlayer.income.currentMoney, clients);
                MainGame.currentPlayer.income.ScoreCalculation(MainGame.currentPlayer, Gdx.graphics.getDeltaTime());
                MainGame.setCurrentState(new ScoreState());
            }
        };
        addUIObject(menuStateBtn);

        //client = new Client(new Vector2(5,5), 1);
        clientGenerator= new ClientGenerator();
        clients= new ArrayList<Client[]>();
        clients=clientGenerator.getClients();
        for (Client[] cs:clients) {
            for (Client c:cs) {
                MainGame.currentPlayer.allGameObjects.add(c);
            }
        }
        toRemove = new ArrayList<GameObject>();
    }

    @Override
    public void update(float gameTime) {
        MainGame.currentPlayer.date.gameUpdate(gameTime * timeSpeed);
        clock.update(MainGame.currentPlayer.date);
        for (GameObject obj : MainGame.currentPlayer.allGameObjects) {
            obj.update(gameTime);                      //OPTIMIZE THIS.
        }
        for (GameObject obj: toRemove) {
            MainGame.currentPlayer.allGameObjects.remove(obj);
        }
        toRemove = new ArrayList<GameObject>();
        clientGenerator.update(gameTime);
    }

    @Override
    protected void renderMenu(SpriteBatch batch) {
        clock.render(batch);
        timeBtn.render(batch);
        timeFastBtn.render(batch);
        buildStateBtn.render(batch);
        managerStateBtn.render(batch);
        menuStateBtn.render(batch);
    }

    @Override
    protected void renderGame(SpriteBatch batch) {
        for (GameObject obj : MainGame.currentPlayer.allGameObjects) {
            obj.render(batch);                      //OPTIMIZE THIS.
        }
    }
    public void removeItem(GameObject obj){
        toRemove.add(obj);
        clients.remove(obj);
    }
}
