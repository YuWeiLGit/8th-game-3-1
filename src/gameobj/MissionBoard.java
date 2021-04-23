package gameobj;

import controllers.ImageController;

import java.awt.*;

public class MissionBoard extends GameObject {
    private Image img;
    private Image img2;
    private Image img3;
    private Image img4;
    private int  changeCount;// 0 第一章圖
    public MissionBoard(int x, int y, int width, int height,int changeCount) {
        super(x, y, width, height);
        img = ImageController.getInstance().tryGet("/missionBlock1-1.png");
        img2 = ImageController.getInstance().tryGet("/missionBlock1-2.png");
        img3 = ImageController.getInstance().tryGet("/missionBlock1-3.png");
        img4 = ImageController.getInstance().tryGet("/finalGoal.png");
        this.changeCount = changeCount;
    }

    public int getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(int changeCount) {
        this.changeCount = changeCount;
    }

    @Override
    public void paintComponent(Graphics g) {
        switch (changeCount){
            case 0:
                g.drawImage(img,painter().left(),painter().top(),null);
                break;
            case 2:
                g.drawImage(img2,painter().left(),painter().top(),null);
                break;
            case 4:
                g.drawImage(img3,painter().left(),painter().top(),null);
                break;
            case 5:
                g.drawImage(img4,painter().left(),painter().top(),null);
                break;
        }

    }

    @Override
    public void update() {

    }
}
