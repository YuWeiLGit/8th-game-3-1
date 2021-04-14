package gameobj;

import controllers.ImageController;

import java.awt.*;

public class InBar extends GameObject{
    private Image img;
    private boolean show;
    public InBar(int x, int y) {
        super(x, y, 21, 34, State.NULL);
        img= ImageController.getInstance().tryGet("/inBar.png");
        show=false;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Override
    public void paintComponent(Graphics g) {
        if(show){
    g.drawImage(img, painter().centerX(), painter().centerY(), null);}
        else {
            return;
        }
    }

    @Override
    public void update() {

    }
}
