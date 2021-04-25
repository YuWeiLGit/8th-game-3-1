package gameobj;

import controllers.ImageController;

import java.awt.*;

public class MoveingBrick extends GameObject{
    private Image img;
    private boolean isLoop;
    private boolean isPause;
    private int vertical;
    private int parallel;


    public MoveingBrick(int x, int y, int width, int height, State state) {
        super(x, y, width, height, state);
        img= ImageController.getInstance().tryGet("/movebricks.png");
        isLoop=false;
        isPause=true;
        vertical=0;
        parallel=0;

    }

    public void loop(){
        isLoop=true;
        isPause=false;
    }

    public void stop(){
        isPause=true;
    }
    public void moveToLeft(int x){

    }
    public void moveToRight(int x){

    }
    public void moveToTop(int x){

    }

    @Override
    public void paintComponent(Graphics g) {
    g.drawImage(img, painter().left()+parallel, painter().top()+vertical, null);
    }

    @Override
    public void update() {

    }
}
