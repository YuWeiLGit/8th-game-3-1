package gameobj;

import controllers.ImageController;

import java.awt.*;

public class MoveingBrick extends GameObject {
    private Image img;
    private boolean isLoop;
    private boolean isPause;
    private int vertical;
    private int parallel;
    private int moveDis;
    protected MoveDir moveDir;
    private boolean changeDir;
    private int movingPerFrame;

    public MoveingBrick(int x, int y, int width, int height, MoveDir moveDir, int moveDis) {
        super(x, y, width, height, State.NULL);
        img = ImageController.getInstance().tryGet("/moveBlock.png");
        isLoop = false;
        isPause = true;
        changeDir = false;
        this.moveDir = moveDir;
        this.moveDis = moveDis;
        vertical = 0;
        parallel = 0;
        this.movingPerFrame=1;

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
        OFF,
        TOP,
        DOWN,
        LEFT,
        Right;
    }
}
