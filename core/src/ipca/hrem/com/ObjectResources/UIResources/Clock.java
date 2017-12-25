package ipca.hrem.com.ObjectResources.UIResources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.BasicResources.Date;
import ipca.hrem.com.ResourceManagers.TextureManager;


public class Clock extends UIObject {
    private final Vector2 digitSize = new Vector2(12, 17);
    private final Vector2 dotSize = new Vector2(8, 17);

    //-------------------------Variables-------------------------//
    private Vector2 position;
    private Texture clockTexture;
    private Sprite backgroundTexture;
    private Sprite firstDigit;
    private Sprite secondDigit;
    private Sprite thirdDigit;
    private Sprite forthDigit;
    private Sprite middleDots;

    //-------------------------GetSetters-------------------------//
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }


    //-------------------------Constructor-------------------------//
    public Clock(Vector2 position, Vector2 size) {
        this.position = position;
        backgroundTexture = new Sprite(TextureManager.loadTexture("ClockAssets/ClockBackground.png"));
        backgroundTexture.setPosition(position.x, position.y);
        backgroundTexture.setSize(size.x, size.y);
        clockTexture = TextureManager.loadTexture("ClockAssets/ClockNumbers.png");

        float digitHeight = size.y * 0.45f;
        float digitWidth = digitSize.x * digitHeight / digitSize.y;

        float digitYOffset = (size.y - digitHeight) / 2.0f;
        float digitXOffset = (size.x / 4) / 2.0f;

        firstDigit = new Sprite(clockTexture, 0, 0, (int)digitSize.x, (int)digitSize.y);
        firstDigit.setPosition((position.x + digitXOffset),  position.y + digitYOffset);
        firstDigit.setSize(digitWidth, digitHeight);
        secondDigit = new Sprite(clockTexture,0, 0, (int)digitSize.x, (int)digitSize.y );
        secondDigit.setPosition((position.x + digitXOffset + digitWidth),  position.y + digitYOffset);
        secondDigit.setSize(digitWidth, digitHeight);
        thirdDigit = new Sprite(clockTexture,0, 0, (int)digitSize.x, (int)digitSize.y );
        thirdDigit.setPosition((position.x + (size.x - digitWidth * 2.0f - digitXOffset)),  position.y + digitYOffset);
        thirdDigit.setSize(digitWidth, digitHeight);
        forthDigit = new Sprite(clockTexture,0, 0, (int)digitSize.x, (int)digitSize.y );
        forthDigit.setPosition((position.x + (size.x - digitWidth - digitXOffset)),position.y + digitYOffset);
        forthDigit.setSize(digitWidth, digitHeight);

        float dotWidth = dotSize.x * digitHeight / dotSize.y;
        middleDots  = new Sprite(clockTexture,  (int)(digitSize.x * 10), 0, (int)dotSize.x, (int)dotSize.y);
        middleDots.setPosition((position.x + size.x / 2  - dotWidth / 2), position.y + digitYOffset);
        middleDots.setSize(dotWidth, digitHeight);
    }

    //-------------------------Functions-------------------------//
    private void ChangeDigit(Sprite digit, int digitValue) {
        digit.setRegion((12 * digitValue), 0, (int)digitSize.x, (int)digitSize.y);
    }


    public void update(Date time) {
        if(time.getMinute() >= 10) {
            int decimal = (int)(time.getMinute() * 0.1f);
            ChangeDigit(forthDigit, time.getMinute() - decimal * 10);
            ChangeDigit(thirdDigit, decimal);
        }
        else {
            ChangeDigit(forthDigit, time.getMinute());
            ChangeDigit(thirdDigit,0);
        }

        if(time.getHour() >= 10) {
            int decimal = (int)(time.getHour() * 0.1f);
            ChangeDigit(secondDigit, time.getHour() - decimal * 10);
            ChangeDigit(firstDigit, decimal);
        }
        else {
            ChangeDigit(secondDigit, time.getHour());
            ChangeDigit(firstDigit,0);
        }
    }

    @Override
    public boolean isVectorInside(Vector2 position) {
        return backgroundTexture.getBoundingRectangle().contains(position);
    }

    @Override
    public void render(SpriteBatch batch) {
        backgroundTexture.draw(batch);
        firstDigit.draw(batch);
        secondDigit.draw(batch);
        thirdDigit.draw(batch);
        forthDigit.draw(batch);
        middleDots.draw(batch);
    }

    @Override
    public void dispose() {
        backgroundTexture.getTexture().dispose();
    }
}
