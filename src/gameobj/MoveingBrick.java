package gameobj;

import controllers.ImageController;
<<<<<<< HEAD

import java.awt.*;

public class MoveingBrick extends GameObject {
    private Image img;
=======
import utils.Global;

import java.awt.*;
import java.util.Random;

public class MoveingBrick extends GameObject {
    private Image img;
    private Image img1;
    private Image img2;
    private Image img3;
    private Image img4;
    private MoveDir moveDir;
>>>>>>> origin/火焰修改+地圖修改
    private boolean isLoop;
    private boolean isPause;
    private int vertical;
    private int parallel;
    private int moveDis;
<<<<<<< HEAD
    protected MoveDir moveDir;
    private boolean changeDir;
    private int movingPerFrame;

    public MoveingBrick(int x, int y, int width, int height, MoveDir moveDir, int moveDis) {
        super(x, y, width, height, State.NULL);
        img = ImageController.getInstance().tryGet("/moveBlock.png");
=======
    private boolean changeDir;
    private int movingPerFrame;
    private int randomCount;//用來存random出來的值

    public MoveingBrick(int x, int y, int width, int height,MoveDir moveDir, int moveDis) {
        super(x, y, width, height, State.NULL);
        img = ImageController.getInstance().tryGet("/moveBlock2.png"); //上下
        img1 = ImageController.getInstance().tryGet("/moveBlock3.png"); //上下
        img2 = ImageController.getInstance().tryGet("/moveBlock4.png"); //左右
        img3 = ImageController.getInstance().tryGet("/moveBlock5.png"); //左右
        img4 = ImageController.getInstance().tryGet("/moveBlock6.png"); //左右

>>>>>>> origin/火焰修改+地圖修改
        isLoop = false;
        isPause = true;
        changeDir = false;
        this.moveDir = moveDir;
        this.moveDis = moveDis;
        vertical = 0;
        parallel = 0;
<<<<<<< HEAD
        this.movingPerFrame=1;

=======
        this.movingPerFrame = 1;
        this.randomCount = Global.random(1,5);
>>>>>>> origin/火焰修改+地圖修改
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
<<<<<<< HEAD
        g.drawImage(img, painter().left() , painter().top(), null);

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
        else if (this.moveDir == MoveDir.Right) {
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

    public enum MoveDir {
=======
        switch (randomCount) {
            case 1:
                g.drawImage(img, painter().left(), painter().top(), null);
                break;
            case 2:
                g.drawImage(img1, painter().left(), painter().top(), null);
                break;
            case 3:
                g.drawImage(img2, painter().left(), painter().top(), null);
                break;
            case 4:
                g.drawImage(img3, painter().left(), painter().top(), null);
                break;
            case 5:
                g.drawImage(img4, painter().left(), painter().top(), null);
                break;
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
             public enum MoveDir {
>>>>>>> origin/火焰修改+地圖修改
        OFF,
        TOP,
        DOWN,
        LEFT,
<<<<<<< HEAD
        Right;
    }
}
=======
        RIGHT;
      }
    }
>>>>>>> origin/火焰修改+地圖修改
