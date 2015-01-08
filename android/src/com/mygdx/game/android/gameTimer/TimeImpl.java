package com.mygdx.game.android.gameTimer;

import com.mygdx.game.game.TimeInterface;

import static android.os.SystemClock.elapsedRealtime;

/**
 * Created by sebastien on 02/01/15.
 */
public class TimeImpl implements TimeInterface {

    private long time=0;
    private long currentTimeSystem;


    public TimeImpl(){
        time = 0;
        currentTimeSystem = 0;
    }

    /**
     * calcul le temps en milliseconde
     * @return
     */
    @Override
    public long startTime() {
        time=elapsedRealtime()-currentTimeSystem;
        return time;
    }

    /**
     * permet de récupérer le temps en milliseconde du systeme depuis le lancement du programme
     */
    @Override
    public void setCurrentTimeSystem() {
        currentTimeSystem = elapsedRealtime();
    }

    /**
     * remet le temps à 0
     */
    @Override
    public void resetTime() {
        time = 0;
        currentTimeSystem = 0;
    }

    /**
     * reprend le timer au moment ou le jeu a été mis en pause
     * @param t
     */
    @Override
    public void restartTime(long t) {
        currentTimeSystem = elapsedRealtime() - t;
    }

}
