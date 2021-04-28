package gameobj;

import controllers.ImageController;
import controllers.Rotate;
import utils.Global;
import utils.Vector;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpaceShip extends GameObject {
    private Image img;
    private Image img2;
    private boolean isGetMaterials;
    private double moveStep;
    private final double initialMoveStep;
    private ArrayList<GameObject> materials; //道具
    private Vector speed;
    private boolean isMove;

    public SpaceShip(int x, int y) {
        super(x + 32 / 2, y + 32 / 2, 32, 32, (CollisionState) null);
        this.img = ImageController.getInstance().tryGet("/spaceShip1-2.png");
//        this.img2 = ImageController.getInstance().tryGet("/spaceShip1-3.png");  //換圖效果不明顯,就不開了
        isCircle();
        materials = new ArrayList<>();
        isGetMaterials = false;
        setMoveStep(moveStep);
        initialMoveStep = moveStep;
        speed = new Vector();
        isMove = false;

//        image=ImageController.getInstance().addBuff("/airplane1.png");
//        img=  Rotate.BufferedImageToImage(Rotate.rotateImage(image,33));
    }

    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public void setMoveStep(double moveStep) {
        this.moveStep = moveStep;
    }

    public double getMoveStep() {
        return this.moveStep;
    }

    public void offSetMoveStep(int x) {
        setMoveStep(getMoveStep() + x);
    }

    public double initialMoveStep() {
        return initialMoveStep;
    }

    public boolean getIsMove(){
        return  isMove;
    }
    public void setIsMove(boolean isMove){
        this.isMove = isMove;
    }

    //得到道具的方法
    public void getMaterials(gameobj.RandomMaterial randomMaterial) {
        materials.add(randomMaterial);
        this.isGetMaterials = true;
    }

    @Override
    public void paintComponent(Graphics g) {
//        if (!isMove) {
//            g.drawImage(img, painter().left(), painter().top(), null);
//        } else {
//            g.drawImage(img2, painter().left(), painter().top(), null);
//        }
    }
    @Override
    public void changeCollisionState(CollisionState collisionState) {
        if (collisionState == GameObject.CollisionState.ANGLE) {
            speed.reverse();
        } else if (collisionState == GameObject.CollisionState.TOP) {
            speed.absY();
        } else if (collisionState == CollisionState.BOTTOM) {
            speed.negY();
        } else if (collisionState == CollisionState.RIGHT) {
            speed.negX();
        } else if (collisionState == GameObject.CollisionState.LEFT) {
            speed.absX();
        }else if (collisionState == CollisionState.CIRCLE) {
            speed.reverse();
        }

        super.changeCollisionState(collisionState);
    }

    public void move() {
        isMove = true;
        painter().offset(speed.vx(), speed.vy());
        collider().offset(speed.vx(), speed.vy());
    }

    public void paintComponent(Graphics g, double degree) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform t = g2d.getTransform();
        g2d.rotate(Math.toRadians(degree), painter().centerX(), painter().centerY());
        g2d.drawImage(img, painter().left(), painter().top(), null);
        g2d.setTransform(t);

    }

    @Override
    public void update() {

    }
}
