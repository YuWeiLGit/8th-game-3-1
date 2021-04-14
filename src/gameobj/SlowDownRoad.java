package gameobj;

import controllers.ImageController;
import gameobj.GameObject;
import gameobj.SpaceShip;
import utils.Delay;
import utils.Global;

import java.awt.*;

public class SlowDownRoad extends GameObject {
    private static final int SLOWDOWN_VALUE = 3;
    private Image img;
    private Delay delay;


    public SlowDownRoad(int x, int y) {
        super(x+Global.UNIT_X/2, y+Global.UNIT_Y/2, Global.UNIT_X , Global.UNIT_Y, State.BURN);
        img = ImageController.getInstance().tryGet("/slowdown.png");
        delay = new Delay(5);
    }

    @Override
    public void active(GameObject gameObject) {
        if (gameObject instanceof SpaceShip) {
            SpaceShip spaceShip = (SpaceShip) gameObject;
            spaceShip.offSetMoveStep(-SLOWDOWN_VALUE);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), null);
    }

    @Override
    public void update() {
        if (getState() == GameObject.State.DISAPPEAR) {
            delay.play();
        }
        if (delay.count()) {
            setState(GameObject.State.BURN);
        }

    }
}
