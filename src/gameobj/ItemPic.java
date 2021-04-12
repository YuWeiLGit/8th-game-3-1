package gameobj;

import controllers.ImageController;

import java.awt.*;

public class ItemPic extends GameObject{
private  Image img;
    public ItemPic(int x, int y) {
        super(x, y, 128, 128);
        this.img = ImageController.getInstance().tryGet("/item1.png");

    }

    public void changePos(int x,int y){

    }

    @Override
    public void active(GameObject obj) {

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().centerX(), painter().centerY(), null);
    }

    @Override
    public void update() {

    }
}
