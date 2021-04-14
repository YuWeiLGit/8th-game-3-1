package gameobj;

import controllers.ImageController;

import gameobj.GameObject;
import gameobj.SpaceShip;
import utils.Delay;
import utils.Global;

import java.awt.*;

public class AccelerationRoad extends GameObject {
    private static final int ACCELERATION_VALUE = 3;
    private Image img;
    private Delay delay;

    public AccelerationRoad(int x, int y) {
        super(x + Global.UNIT_X / 2, y + Global.UNIT_Y / 2, Global.UNIT_X, Global.UNIT_Y, State.BURN);
        img = ImageController.getInstance().tryGet("/acceleration.png");
        delay = new Delay(30);
        delay.isPause();
    }

    @Override
    public void active(GameObject gameObject) {
        if (gameObject instanceof SpaceShip) {
            //如果加速道的狀態不是消失的話,有加速的效果
            if (getState() != State.DISAPPEAR) {
                SpaceShip spaceShip = (SpaceShip) gameObject;
                spaceShip.offSetMoveStep(ACCELERATION_VALUE);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), null);
    }

    @Override
    public void update() {
        if (getState() == GameObject.State.DISAPPEAR) {
            if (delay.isPlaying()) {
                delay.play();
            } else {
                delay.pause();
            }
            if (delay.count()) {
                setState(GameObject.State.BURN);
            }


        }
    }
}