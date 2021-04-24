package gameobj;

import controllers.ImageController;
import utils.Global;

import java.awt.*;

public class MoveBlock extends GameObject{
    private Image img;

    public MoveBlock(int x, int y ) {
        super(x, y, Global.UNIT_X, Global.UNIT_Y,CollisionState.NORMAL);
        img = ImageController.getInstance().tryGet("/moveBlock.png");
        changeCollisionStateByMoveBlock(CollisionState.NORMAL);
    }

    public void changeCollisionStateByMoveBlock(CollisionState collisionState){
        if (collisionState == GameObject.CollisionState.TOP) {
            translateY(1);
        } else if (collisionState == CollisionState.BOTTOM) {
            translateY(-1);
        } else if (collisionState == CollisionState.RIGHT) {
            translateX(-1);
        } else if (collisionState == GameObject.CollisionState.LEFT) {
            translateX(+1);
        }
        this.collisionState=collisionState;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img,painter().left(),painter().top(),null);

    }

    @Override
    public void update() {

    }
}
