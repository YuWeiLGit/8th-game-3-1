package gameobj;

import controllers.ImageController;
import gameobj.GameObject;
import utils.Global;

import java.awt.*;

public class Wall extends GameObject {
    private Image img;
    public Wall(int x, int y) {
        super(x+Global.UNIT_X, y+Global.UNIT_Y, Global.UNIT_X, Global.UNIT_Y,State.BURN);
        img = ImageController.getInstance().tryGet("/p1.png");
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
