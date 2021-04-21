package gameobj;

import controllers.ImageController;
import utils.Delay;
import utils.Global;

import java.awt.*;

public class EnergyBall extends GameObject{
    private EnergyAnimator energyAnimator;
    private Delay delay;
    //避開障礙物中間障礙物用
    private int fx;
    private int fy;
    //避開右邊障礙物用
    private int rx;
    private int ry;
    public EnergyBall(int x, int y) {
        super(x, y, Global.UNIT_X, Global.UNIT_Y,State.BURN);
        energyAnimator = new EnergyAnimator();
        delay = new Delay(30);
    }


    @Override
    public void paintComponent(Graphics g) {
        if (this.getState() == State.BURN) {
            energyAnimator.paintComponent(g, painter().left(), painter().top(), painter().right(), painter().bottom());
        }
    }

    @Override
    public void update() {

    }
    private static class EnergyAnimator {
        private Image img;
        private int count;
        private Delay delay;
        private static final int[] ENERGYANIMATOR = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        //建構子
        private  EnergyAnimator() {
            img = ImageController.getInstance().tryGet("/energyBall1.png");
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
                if (count >=10) {
                    count = 0;
                }
            }
            int tx = Global.UNIT_X * 1;
            int ty = Global.UNIT_Y * 1;
            g.drawImage(img, left, top, right, bottom,   Global.UNIT_X *  ENERGYANIMATOR[count],
                    0, tx + Global.UNIT_X *  ENERGYANIMATOR[count] ,ty,null);
        }

    }
}
