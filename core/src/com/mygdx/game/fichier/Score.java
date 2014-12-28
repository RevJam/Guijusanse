package com.mygdx.game.fichier;

/**
 * Created by sebastien on 28/12/14.
 */
public class Score {

    private String playerName;
    private String songTitle;
    private TypeDifficultee difficultee;
    private int score;

    public Score(){
        playerName = "";
        songTitle = "";
        difficultee = null;
        score = -1;
    }

    public Score(String playerName, String songTitle, TypeDifficultee difficultee, int score){
        this.playerName = playerName;
        this.songTitle = songTitle;
        this.difficultee = difficultee;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public TypeDifficultee getDifficultee() {
        return difficultee;
    }

    public void setDifficultee(TypeDifficultee difficultee) {
        this.difficultee = difficultee;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "playerName='" + playerName + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", difficultee=" + difficultee +
                ", score=" + score +
                '}';
    }

    public boolean isEmpty(){
        return playerName.isEmpty() && songTitle.isEmpty() && difficultee==null && score==-1;
    }
}
