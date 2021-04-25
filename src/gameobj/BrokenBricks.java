package gameobj;

import controllers.ImageController;

import java.awt.*;

public class BrokenBricks extends GameObject {
    private Image img;
    private Image img1;
    private Image img2;
    private int count;
    protected HitState hitState;
    private boolean isBroken;

    public BrokenBricks(int x, int y, int width, int height, State state) {
        super(x, y, width, height, state);
        this.hitState = BrokenBricks.HitState.NORMAL;
        img = ImageController.getInstance().tryGet("/brickA1.png");
        img1 = ImageController.getInstance().tryGet("/brickA2.png");
        img2 = ImageController.getInstance().tryGet("/brickA3.png");
        count = 0;
        isBroken = false;
    }

    public void collision() {
        this.count++;
        if (count >= 2) {
            count = 2;
            isBroken = true;
        }
    }

    public boolean IsBroken() {
        return this.isBroken;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (this.hitState == HitState.NORMAL) {
            g.drawImage(img, painter().left(), painter().top(), null);
        } else if (this.hitState == HitState.FIRST) {
            g.drawImage(img1, painter().left(), painter().top(), null);
        } else g.drawImage(img2, painter().left(), painter().top(), null);
    }

    @Override
    public void update() {
        if (count == 0) {
            this.hitState = HitState.NORMAL;
        } else if (count == 1) {
            this.hitState = HitState.FIRST;
        } else if (count == 2) {
            this.hitState = HitState.SECOND;
        }

    }

    public enum HitState {
        NORMAL,
        FIRST,
        SECOND;
    }
}
