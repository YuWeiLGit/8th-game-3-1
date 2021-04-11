package gameobj;

import controllers.ImageController;
import controllers.Rotate;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class SpaceShip extends GameObject{
    private Image img;


    public SpaceShip(int x, int y) {
        super(x, y, 32, 32);
       this.img = ImageController.getInstance().tryGet("/spaceship.png");
       isCircle();
//        image=ImageController.getInstance().addBuff("/airplane1.png");
//        img=  Rotate.BufferedImageToImage(Rotate.rotateImage(image,33));
    }

    @Override
    public void paintComponent(Graphics g) {

//        g.drawImage(img, painter().left(), painter().top(), null);
    }

    public void paintComponent(Graphics g,double degree) {
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform t = g2d.getTransform();
        g2d.rotate(Math.toRadians(degree), painter().centerX(), painter().centerY());
        g2d.drawImage(img, painter().left(), painter().top(), null);
        g2d.setTransform(t);

//        g.drawImage(img, painter().left(), painter().top(), null);
    }
    @Override
    public void update() {

    }
}
