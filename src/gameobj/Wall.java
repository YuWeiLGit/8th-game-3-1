package gameobj;

import controllers.ImageController;
import gameobj.GameObject;
import utils.Global;

import java.awt.*;

public class Wall extends GameObject {
    private Image img;
    public Wall(int x, int y) {
        super(x, y, Global.UNIT_X*2, Global.UNIT_Y*2,State.BURN);
        img = ImageController.getInstance().tryGet("/wall.png");
    }

    @Override
    public void active(GameObject gameObject) {
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), null);

    }
    @Override
    public void update() {

    }
}
