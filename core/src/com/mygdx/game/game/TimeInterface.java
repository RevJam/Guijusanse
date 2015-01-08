package com.mygdx.game.game;

/**
 * Created by sebastien on 02/01/15.
 */
public interface TimeInterface {

    /**
     * calcul le temps en milliseconde
     * @return
     */
    public long startTime();

    /**
     * permet de récupérer le temps en milliseconde du systeme depuis le lancement du programme
     */
    public void setCurrentTimeSystem();

    /**
     * remet le temps à 0
     */
    public void resetTime();

    /**
     * reprend le timer au moment ou le jeu a été mis en pause
     * @param t
     */
    public void restartTime(long t);

}
