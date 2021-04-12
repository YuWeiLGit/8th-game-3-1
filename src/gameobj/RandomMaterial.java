package gameobj;

import controllers.ImageController;
import gameobj.GameObject;
import gameobj.SpaceShip;
import utils.Delay;
import utils.Global;

import java.awt.*;

public class RandomMaterial extends GameObject {
    private Image img;
    private Delay delay;

    public RandomMaterial(int x, int y) {
        super(x, y, Global.UNIT_X * 2, Global.UNIT_Y * 2,State.BURN);
        img = ImageController.getInstance().tryGet("/randomMaterial.png");
        this.delay = new Delay(300);
        delay.isPause();
    }

    @Override
    public void active(GameObject gameObject) {
//        if (gameObject instanceof SpaceShip) {
//            SpaceShip spaceShip = (SpaceShip) gameObject;
//            spaceShip.getMaterials();
//        }

    }
    //確定他是否存在
    @Override
    public void paintComponent(Graphics g) {
        if (this.getState() == GameObject.State.BURN) {
            g.drawImage(img, painter().left(), painter().top(), null);
        }
    }

    @Override
    public void update() {
        if (getState() == GameObject.State.DISAPPEAR) {
            delay.play();
            if (delay.count()) {
                setState(GameObject.State.BURN);
            }
        }
    }

    }



