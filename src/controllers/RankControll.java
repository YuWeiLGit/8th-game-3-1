package controllers;

public class RankControll {
    private int score;
    private String name;
    public  RankControll(int score,String name){
        this.name=name;
        this.score=score;
    }

    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
}
