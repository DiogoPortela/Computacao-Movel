package ipca.hrem.com.BasicResources;

public class Date {
    //-------------------------Variables-------------------------//
    private int day;
    private int hour;
    private int minute;
    private int second;
    private float milisecond;

    //-------------------------GetSetters-------------------------//

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public float getMilisecond() {
        return milisecond;
    }

    public void setMilisecond(float milisecond) {
        this.milisecond = milisecond;
    }

    //-------------------------Constructor-------------------------//
    public Date() {
        day = 0;
        hour = 0;
        second = 0;
        milisecond = 0.0f;
    }

    //-------------------------Functions-------------------------//
    public void regularUpdate(float deltaTime) {
        milisecond += deltaTime;
        if (milisecond >= 1000) {
            milisecond -= 1000;
            second++;
            if (second >= 60) {
                second -= 60;
                minute++;
                if (minute >= 60) {
                    minute -= 60;
                    hour++;
                    if (hour >= 24) {
                        hour -= 24;
                        day++;
                    }
                }
            }
        }
    }

    public void gameUpdate(float deltaTime) {
        milisecond += deltaTime;
        if (milisecond >= 1) {
            milisecond -= 1;
            minute++;
            if (minute >= 60) {
                minute -= 60;
                hour++;
                if (hour >= 24) {
                    hour -= 24;
                    day++;
                }
            }
        }
    }
}

