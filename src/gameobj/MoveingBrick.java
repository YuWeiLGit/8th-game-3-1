package gameobj;

import controllers.ImageController;
import utils.Global;

import java.awt.*;
import java.util.Random;

public class MoveingBrick extends GameObject {
    private Image img;
    private Image img1;
    private Image img2;
    private Image img3;
    private MoveDir moveDir;
    private boolean isLoop;
    private boolean isPause;
    private int vertical;
    private int parallel;
    private int moveDis;
    private boolean changeDir;
    private int movingPerFrame;
    private int randomCount;//用來存random出來的值

    public MoveingBrick(int x, int y, int width, int height,MoveDir moveDir, int moveDis) {
        super(x, y, width, height, State.NULL);
        img = ImageController.getInstance().tryGet("/"); //上下
        img1 = ImageController.getInstance().tryGet("/movingBrick2-1.png"); //上下
        img2 = ImageController.getInstance().tryGet("/movingBrick2-3.png"); //左右
        img3 = ImageController.getInstance().tryGet("/movingBrick2-4.png"); //左右
        isLoop = false;
        isPause = true;
        changeDir = false;
        this.moveDir = moveDir;
        this.moveDis = moveDis;
        vertical = 0;
        parallel = 0;
        this.movingPerFrame = 1;
        this.randomCount = Global.random(0,1);
    }

    public void loop() {
        isLoop = true;
        isPause = false;
    }

    public void changeMoveDir(MoveDir moveDir) {
        this.moveDir = moveDir;
    }
    public void changeMovingDisPerFrame(int t1){
        this.movingPerFrame=movingPerFrame+t1;
    }
    private void changeBoolean() {
        if (this.changeDir) {
            this.changeDir = false;
        } else {
            changeDir = true;
        }
    }


    public void stop() {
        isPause = true;
    }


    @Override
    public void paintComponent(Graphics g) {
        if (randomCount == 0) {
            g.drawImage(img, painter().left(), painter().top(), null);
        } else {
            g.drawImage(img1, painter().left(), painter().top(), null);
        }
    }

    @Override
    public void update() {
            if (this.moveDir == MoveDir.TOP) {
                if (changeDir) {
                    vertical=vertical+movingPerFrame;
                    painter().offset(0,movingPerFrame);
                } else {
                    vertical=vertical-movingPerFrame;
                    painter().offset(0,-movingPerFrame);
                }
                if (Math.abs(vertical) == moveDis || vertical == 0) {    ////1
                    changeBoolean();
                }
            }
            else if (this.moveDir == MoveDir.DOWN) {
                if (changeDir) {
                    vertical=vertical-movingPerFrame;
                    painter().offset(0,-movingPerFrame);
                } else {
                    vertical=vertical+movingPerFrame;
                    painter().offset(0,movingPerFrame);
                }
                if (Math.abs(vertical) == moveDis || vertical == 0) {   ///2
                    changeBoolean();
                }
            }
            else if (this.moveDir == MoveDir.LEFT) {
                if (changeDir) {
                    parallel=parallel-movingPerFrame;
                    painter().offset(movingPerFrame,0);
                } else {
                    parallel=parallel+movingPerFrame;
                    painter().offset(-movingPerFrame,0);
                }
                if (Math.abs(parallel )== moveDis || parallel == 0) {
                    changeBoolean();
                }
            }
            else if (this.moveDir == MoveDir.RIGHT) {
                System.out.println("p:"+parallel);
                if (changeDir) {
                    parallel=parallel+movingPerFrame;
                    painter().offset(-movingPerFrame,0);
                } else {
                    parallel=parallel-movingPerFrame;
                    painter().offset(movingPerFrame,0);
                }
                if (Math.abs(parallel )== moveDis || parallel == 0) {
                    changeBoolean();
                }
            }

        }

    }

    public enum MoveDir {
        OFF,
        TOP,
        DOWN,
        LEFT,
        RIGHT;
        }

}