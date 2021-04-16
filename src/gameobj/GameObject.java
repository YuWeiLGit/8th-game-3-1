package gameobj;

import camera.MapInformation;
import utils.GameKernel;
import utils.Global;
import utils.Vector;

import java.awt.*;

//管理遊戲物件的抽象父類
public abstract class GameObject implements GameKernel.UpdateInterface, GameKernel.PaintInterface {
    private final Rect collider;
    private final Rect painter;
    private boolean isCircle;
    private State state;
    private CollisionState collisionState;


    public GameObject(int x, int y, int width, int height, State state) {
        this(x, y, width, height, x, y, width, height);
        isCircle = false;
        this.state = state;
        this.collisionState = CollisionState.NORMAL;

    }

    public GameObject(int x, int y, int width, int height) {
        this(x, y, width, height, x, y, width, height);
        isCircle = false;
        this.state = null;
        this.collisionState = CollisionState.NORMAL;
    }

    public GameObject(Rect rect) {
        state = State.NULL;
        collider = new Rect(rect);
        painter = new Rect(rect);
    }

    public GameObject(int x, int y, int width, int height,
                      int x2, int y2, int width2, int height2) {
        collider = Rect.genWithCenter(x, y, width, height);
        painter = Rect.genWithCenter(x2, y2, width2, height2);
        state = State.NULL;
    }

    public GameObject(Rect rect, Rect rect2) {
        collider = new Rect(rect);
        painter = new Rect(rect2);
        state = State.NULL;
    }

    public void changeCollisionState(CollisionState collisionState) {
        this.collisionState = collisionState;
    }

    public CollisionState getCollisionState() {
        return collisionState;
    }

    public boolean outOfScreen() {
        if (painter.bottom() <= 0) {
            return true;
        }
        if (painter.right() <= 0) {
            return true;
        }
        if (painter.left() >= Global.SCREEN_X) {
            return true;
        }
        return painter.top() >= Global.SCREEN_Y;
    }

    public boolean touchTop() {
        return collider.top() <= 0;
    }

    public boolean touchLeft() {
        return collider.left() <= 0;
    }

    public boolean touchRight() {
        return collider.right() >= MapInformation.mapInfo().right();
    }

    public boolean touchBottom() {
        return collider.bottom() >= MapInformation.mapInfo().bottom();
    }

    public boolean isCollision(GameObject obj) {
        if (isCircle) {
            int vx = Math.abs(painter.centerX() - obj.painter.centerX());
            int vy = Math.abs(painter.centerY() - obj.painter.centerY());
            double hx = Math.abs(obj.painter.width() * 0.5);
            double hy = Math.abs(obj.painter.height() * 0.5);
            double ux = vx - hx;
            double uy = vy - hy;
            double dis;
            if (ux >= 0 && uy >= 0) {
                dis = Global.getHypotenuse(ux, uy);
            } else if (ux < 0) {
                dis = vy - (obj.painter.height() * 0.5);
            } else if (uy < 0) {
                dis = vx - (obj.painter.width() * 0.5);
            } else {
                return true;
            }
            if (dis < this.painter.width()) {
                if (ux >= 0 && uy >= 0) {
                    changeCollisionState(GameObject.CollisionState.ANGLE);
                } else if (ux < 0) {
                    if(painter.centerY() - obj.painter.centerY()>0){
                        changeCollisionState(CollisionState.TOP);
                    }else
                    changeCollisionState(CollisionState.BOTTOM);
                }else if (uy < 0) {
                    if(painter.centerX() - obj.painter.centerX()>0){
                    changeCollisionState(CollisionState.LEFT);}
                    else {
                        changeCollisionState(CollisionState.RIGHT);
                    }
                }
                return true;
            } else {
                changeCollisionState(CollisionState.NORMAL);
                return false;
            }
        } else {
            return collider.overlap(obj.collider);
        }
    }


    public void isCircle() {
        isCircle = true;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public final void back(int x, int y) {
        collider.setCenter(x, y);
        painter.setCenter(x, y);
    }

    public final void translate(int x, int y) {
        collider.translate(x, y);
        painter.translate(x, y);
    }

    public final void translateX(int x) {
        collider.translateX(x);
        painter.translateX(x);
    }

    public final void translateY(int y) {
        collider.translateY(y);
        painter.translateY(y);
    }

    public final Rect collider() {
        return collider;
    }

    public final Rect painter() {
        return painter;
    }

    @Override
    public final void paint(Graphics g) {
        paintComponent(g);
        if (Global.IS_DEBUG) {
            if (isCircle) {
                g.setColor(Color.RED);
                g.drawOval(this.painter.left(), this.painter.top(), this.painter.width(), this.painter.height());
                g.setColor(Color.BLUE);
                g.drawOval(this.collider.left(), this.collider.top(), this.collider.width(), this.collider.height());
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
                g.drawRect(this.painter.left(), this.painter.top(), this.painter.width(), this.painter.height());
                g.setColor(Color.BLUE);
                g.drawRect(this.collider.left(), this.collider.top(), this.collider.width(), this.collider.height());
                g.setColor(Color.BLACK);
            }
        }
    }


    public void active(GameObject obj) {
    }

    public abstract void paintComponent(Graphics g);


    public enum CollisionState {
        NORMAL,
        ANGLE,
        TOP,
        BOTTOM,
        RIGHT,
        LEFT;


    }

    public enum State {
        BURN,
        DISAPPEAR,
        NULL;
    }
}
