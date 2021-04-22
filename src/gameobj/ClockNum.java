package gameobj;

import controllers.ImageController;

import java.awt.*;

public class ClockNum extends GameObject {
    private Image img;
    private int x1;
            private int y1;
            private Hand hand;
            public int count;
            private int second;
            private int second10;
            private int minute;
            private int hour;


    public ClockNum(int x, int y, int width, int height, int x1, int y1, Hand hand) {
                super(x, y, width, height, State.NULL);
                img = ImageController.getInstance().tryGet("/num.png");
                this.x1 = x1;
                this.y1 = y1;
                this.hand = hand;
                count = 0;
                second=0;
                second10=0;
                minute=0;
                hour=0;
            }

            @Override
        public void paintComponent(Graphics g) {
        if (this.hand == Hand.SecondHand) {
        g.drawImage(img, x1, y1, x1 + 38, y1 + 55, second%10*38+5, 0, second%10*38+43, 55, null);
        }
       else if (this.hand == Hand.SecondHand10) {
            g.drawImage(img, x1, y1, x1 + 38, y1 + 55, second10%6*38+5, 0, second10%6*38+43, 55, null);
        }
        else if (this.hand == Hand.MinuteHand) {
            g.drawImage(img, x1, y1, x1 + 38, y1 + 55, minute%10*38+5, 0, minute%10*38+43, 55, null);
        }
        else if (this.hand == Hand.HourHand) {
            g.drawImage(img, x1, y1, x1 + 38, y1 + 55, hour%6*38+5, 0, hour%6*38+43, 55, null);
        }
    }

    @Override
    public void update() {
        count++;
        second=count/60;
        second10=count/600;
        minute=second10/6;
        hour=second10/60;
    }

    public enum Hand {
        HourHand,
        MinuteHand,
        SecondHand,
        SecondHand10;
    }
}
