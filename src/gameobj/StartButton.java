package gameobj;

import controllers.ImageController;

import java.awt.*;

public class StartButton extends GameObject {
    private Image img;
    private Image img2;
    private boolean isTouch;
    private boolean show;

    public StartButton(int x, int y, int width, int height, State state) {
        super(x, y, width, height, State.NULL);
        System.out.println("new");
        isTouch = false;
        show = false;
        img = ImageController.getInstance().tryGet("/start.png");
        img2 = ImageController.getInstance().tryGet("/start2.png");
    }

    public void isTouch() {
        this.isTouch = true;

    }

    public boolean isTouched() {
        return this.isTouch;
    }

    public void notTouch() {
        this.isTouch = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (show) {
            if (isTouch) {
                g.drawImage(img2, painter().left(), painter().top(), null);
            } else {
                g.drawImage(img, painter().left(), painter().top(), null);
                ;
            }
        }
    }
public void show(){
        this.show=true;
}
    @Override
    public void update() {

    }
}
