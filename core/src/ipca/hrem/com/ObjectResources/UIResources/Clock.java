package ipca.hrem.com.ObjectResources.UIResources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.time.LocalTime;

import ipca.hrem.com.BasicResources.Date;
import ipca.hrem.com.BasicResources.Point;


public class Clock extends UIObject {
    private final Vector2 digitSize = new Vector2(12, 17);
    private final Vector2 dotSize = new Vector2(8, 17);

    //-------------------------Variables-------------------------//
    private Vector2 position;
    private String clockTexture;
    private StaticTexture backgroundTexture;
    private AnimatedTexture firstDigit;
    private AnimatedTexture secondDigit;
    private AnimatedTexture thirdDigit;
    private AnimatedTexture forthDigit;
    private AnimatedTexture middleDots;



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
        backgroundTexture = new StaticTexture("ClockAssets/ClockBackground.png", position, size);
        clockTexture = "ClockAssets/ClockNumbers.png";

        float digitHeight = size.y * 0.45f;
        float digitWidth = digitSize.x * digitHeight / digitSize.y;

        float digitYOffset = (size.y - digitHeight) / 2.0f;
        float digitXOffset = (size.x / 4) / 2.0f;

        firstDigit = new AnimatedTexture(clockTexture, new Vector2((position.x + digitXOffset), position.y + digitYOffset), new Vector2(digitWidth, digitHeight), Vector2.Zero, digitSize);
        secondDigit = new AnimatedTexture(clockTexture, new Vector2((position.x + digitXOffset + digitWidth), position.y + digitYOffset), new Vector2(digitWidth, digitHeight), Vector2.Zero, digitSize);
        thirdDigit = new AnimatedTexture(clockTexture, new Vector2((position.x + (size.x - digitWidth * 2.0f - digitXOffset)), position.y + digitYOffset), new Vector2(digitWidth, digitHeight), Vector2.Zero, digitSize);
        forthDigit = new AnimatedTexture(clockTexture , new Vector2((position.x + (size.x - digitWidth - digitXOffset)), position.y + digitYOffset), new Vector2(digitWidth, digitHeight), Vector2.Zero, digitSize);

        float dotWidth = dotSize.x * digitHeight / dotSize.y;
        middleDots = new AnimatedTexture(clockTexture , new Vector2((position.x + size.x / 2  - dotWidth / 2), position.y + digitYOffset), new Vector2(dotWidth, digitHeight), new Vector2( digitSize.x * 10,0), dotSize);

    }

    //-------------------------Functions-------------------------//
    private void ChangeDigit(AnimatedTexture digit, int digitValue) {
        digit.changeRegion(new Vector2(12 * digitValue, 0), digitSize);
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
        return backgroundTexture.isVectorInside(position);
    }

    @Override
    public void render(SpriteBatch batch) {
        backgroundTexture.render(batch);
        firstDigit.render(batch);
        secondDigit.render(batch);
        thirdDigit.render(batch);
        forthDigit.render(batch);
        middleDots.render(batch);
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
    }
}
