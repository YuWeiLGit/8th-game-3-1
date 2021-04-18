package gameobj;

import controllers.ImageController;
import controllers.Rotate;
import utils.Vector;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Goal extends GameObject{
    private Image img;
    private Vector GoalSpeed;


    public Goal(int x, int y) {
        super(x+32/2, y+32/2, 32, 32,CollisionState.STEADY);
       this.img = ImageController.getInstance().tryGet("/goal.png");
       isCircle();
       GoalSpeed= new Vector();
       changeCollisionState(CollisionState.STEADY);
        IsGoal(true);

    }

    @Override
    public void changeCollisionState(CollisionState collisionState){
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
        }
        this.collisionState=collisionState;

    }

    public void move() {
//        if(this.getCollisionState()==CollisionState.STEADY){
//            System.out.println("!!");
//            return;
//        }
        painter().offset(getSpeed().vx(), getSpeed().vy());
        collider().offset(getSpeed().vx(), getSpeed().vy());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), null);
    }

    @Override
    public void update() {

    }
}
