package gameobj;

import controllers.ImageController;

import java.awt.*;

public class MillenniumFalcon extends GameObject{
    private Image img;

    public MillenniumFalcon(int x, int y, int width, int height, State state) {
        super(x, y, width, height, State.NULL);
        img= ImageController.getInstance().tryGet("/back2.png");
    }

    @Override
    public void paintComponent(Graphics g) {
    g.drawImage(img, painter().left(), painter().top(), null);

    }

    @Override
    public void update() {

    }
}
