package gameobj;

import controllers.ImageController;
import utils.Delay;
import utils.Global;

import java.awt.*;

public class Portal extends GameObject{
    private Image img ;
    private PortalAnimator portalAnimator;
    public Portal(int x, int y,int width,int height) {
        super(x, y,width ,height);
        portalAnimator = new PortalAnimator();

    }
    @Override
    public void paintComponent(Graphics g) {
        portalAnimator.paintComponent(g,painter().left(),painter().top(),painter().right(),painter().bottom());
    }
    @Override
    public void update() {
    }
    private static class PortalAnimator {
        private Image img;
        private int count;
        private Delay delay;
        private static final int[] PORTALAIMATORARR = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};


        //建構子
        private PortalAnimator() {
            img = ImageController.getInstance().tryGet("/portal5.png");
            delay = new Delay(5);
            delay.loop();
            count = 0;

        }

        public int getCount() {
            return count;
        }

        public void paintComponent(Graphics g, int left, int top, int right, int bottom) {
            if (delay.count()) {
                count++;
                if (count >21) {
                    count = 0;
                }
            }
            int tx = 96 ;
            int ty = 64;
            g.drawImage(img, left, top, right, bottom,   tx*PORTALAIMATORARR[count],
                    0, tx * (PORTALAIMATORARR[count]+1) ,ty,null);
        }
    }
}

