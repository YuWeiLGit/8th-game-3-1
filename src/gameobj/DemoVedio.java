package gameobj;

import controllers.ImageController;
import utils.Delay;
import utils.Global;

import java.awt.*;

public class DemoVedio extends GameObject {
    private DemoVedioAnimator demoVedioAnimator;

    public DemoVedio(int x, int y) {
        super(x, y, 625, 180);
        demoVedioAnimator = new DemoVedioAnimator();
    }
    public void play(){

        demoVedioAnimator.delay.loop();
    }

private static class DemoVedioAnimator {
    private Image img;
    private Delay delay;
    private int count;
    private static final int[] DEMOVEDIOANIMATORARR = {0, 1, 2, 3, 4, 5,6,8,9,10,11,12,13,14,15,16,17,18,19,20,21};


    private DemoVedioAnimator() {
        this.img = ImageController.getInstance().tryGet("/demovideo.png");
        this.delay = new Delay(1);
        delay.pause();
        count = 0;
    }


    public void paintComponent(Graphics g, int left, int top, int right, int bottom) {
        if (delay.count()) {
            count++;
        }
        if (count >20) {
            count = 0;
            delay.pause();
        }
        int tx = 625;
        int ty = 180;
        g.drawImage(img, left, top, right, bottom, tx * DEMOVEDIOANIMATORARR[count] ,
                0, tx * (DEMOVEDIOANIMATORARR[count] + 1), ty, null);
    }

}


    @Override
    public void paintComponent(Graphics g) {
        demoVedioAnimator.paintComponent(g, painter().left(), painter().top(), painter().right(), painter().bottom());

    }

    @Override
    public void update() {

    }
}
