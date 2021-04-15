package gameobj;

import camera.MapInformation;
import utils.GameKernel;
import utils.Global;

import java.awt.*;

//管理遊戲物件的抽象父類
public abstract class GameObject implements GameKernel.UpdateInterface, GameKernel.PaintInterface {
    private final Rect collider;
    private final Rect painter;
    private boolean isCircle;
    private State state;
    private MoveState moveState;

    public GameObject(int x, int y, int width, int height, State state) {
        this(x, y, width, height, x, y, width, height);
        isCircle = false;
        this.state = state;
        this.moveState = MoveState.NORMAL;
    }

    public GameObject(int x, int y, int width, int height) {
        this(x, y, width, height, x, y, width, height);
        isCircle = false;
        this.state = null;
        this.moveState = MoveState.NORMAL;
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

    public void changeMoveState(MoveState moveState) {
        this.moveState = moveState;
    }

    public MoveState getMoveState() {
        return this.moveState;
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
                dis = uy - (obj.painter.height() * 0.5);
            } else if (uy < 0) {
                dis = uy - (obj.painter.width() * 0.5);
            } else {
                dis = -1;
            }
            if (Global.getHypotenuse(ux, uy) < this.painter.width()) {
                return true;
            } else {
                return false;
            }
        } else {
            return collider.overlap(obj.collider);
        }
    }

    //    }
    public boolean topIsCollision(GameObject obj) {
        if (isCircle) {
//            System.out.println("obj.top");
//            System.out.println(painter.top()<obj.painter.top()&&
//                    painter.bottom()>=obj.painter.top());
            return painter.top() >= obj.painter.bottom() &&
                    painter.bottom() > obj.painter.top();
        }
        return collider.left() < obj.collider.right() &&
                obj.collider.bottom() <= collider.top() &&//
                obj.collider.top() > collider.bottom() &&
                obj.collider.left() < collider.right();
    }

    public boolean leftIsCollision(GameObject obj) {
        if (isCircle) {
            //   System.out.println("obj.Left");
            //  System.out.println(painter.left()>=obj.painter.right()&&
            //          painter.right()>obj.painter.right());
            return painter.left() >= obj.painter.right() &&
                    painter.left() > obj.painter.left();

        }
        return collider.left() <= obj.collider.right() &&
                obj.collider.bottom() > collider.top() &&
                obj.collider.top() < collider.bottom() &&
                obj.collider.left() < collider.left();
    }

    public boolean rightIsCollision(GameObject obj) {
        if (isCircle) {
//            System.out.println("obj.right");
//            System.out.println(painter.right()<=obj.painter.left()&& painter.right()>obj.painter.right());
            return painter.right() <= obj.painter.left() &&
                    painter.right() < obj.painter.right();

        }
        return collider.right() >= obj.collider.left() &&
                obj.collider.bottom() > collider.top() &&
                obj.collider.top() < collider.bottom() &&
                obj.collider.right() > collider.right();
    }

    public boolean bottomIsCollision(GameObject obj) {
        if (isCircle) {
//            System.out.println("obj.bot");
//            System.out.println(painter.top()<obj.painter.top()&&
//                    painter.bottom()<=obj.painter.top());
            return painter.top() < obj.painter.top() &&
                    painter.bottom() <= obj.painter.top();
        }
        return collider.left() < obj.collider.right() &&
                obj.collider.bottom() > collider.top() &&//
                obj.collider.top() >= collider.bottom() &&
                obj.collider.left() < collider.right();
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

    public enum MoveState {
        NORMAL,
        BOTTLE,
        TOP,
        RIGHT,
        LEFT;

    }

    public enum State {
        BURN,
        DISAPPEAR,
        NULL;
    }
}
