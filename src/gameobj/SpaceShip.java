package gameobj;

import controllers.ImageController;
import controllers.Rotate;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpaceShip extends GameObject{
    private Image img;
    private boolean isGetMaterials;
    private int moveStep;
    private final  int initialMoveStep ;
    private ArrayList<GameObject> materials; //道具

    public SpaceShip(int x, int y,int moveStep) {
        super(x+32/2, y+32/2, 32, 32,null);
       this.img = ImageController.getInstance().tryGet("/spaceship.png");
       isCircle();
        materials = new ArrayList<>();
        isGetMaterials = false;
        setMoveStep(moveStep);
        initialMoveStep = moveStep;
//        initialSocks =

//        image=ImageController.getInstance().addBuff("/airplane1.png");
//        img=  Rotate.BufferedImageToImage(Rotate.rotateImage(image,33));
    }
    public void setMoveStep(int moveStep) {
        this.moveStep = moveStep;
    }
    public int getMoveStep(){
        return  this.moveStep;
    }
    public  void offSetMoveStep(int x){
        setMoveStep(getMoveStep()+x);
    }

    public int initialMoveStep() {
        return initialMoveStep;
    }

    //得到道具的方法
    public void getMaterials(gameobj.RandomMaterial randomMaterial){
        materials.add(randomMaterial);
        this.isGetMaterials = true;
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
