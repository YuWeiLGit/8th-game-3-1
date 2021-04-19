package gameobj;

import controllers.ImageController;

import java.awt.*;

public class ClockBack extends GameObject{
    private Image img;

    public ClockBack(int x, int y, int width, int height) {
        super(x, y, width, height, State.NULL);
        img= ImageController.getInstance().tryGet("/clockback.png");
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), null);
    }

    @Override
    public void update() {

    }

}
