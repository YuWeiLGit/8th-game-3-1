package gameobj;

import controllers.ImageController;

import java.awt.*;

public class EnergyBar extends GameObject{

    private Image img;
    public EnergyBar(int x, int y,int width,int height) {
        super(x, y, width, height, State.NULL);
        img= ImageController.getInstance().tryGet("/emptyBar.png");
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), null);
    }

    @Override
    public void update() {

    }
}
