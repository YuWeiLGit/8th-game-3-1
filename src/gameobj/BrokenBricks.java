package gameobj;

import controllers.ImageController;
<<<<<<< HEAD

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
=======
import utils.Path;

import java.awt.*;

public class BrokenBricks extends GameObject{
    private Image img;
    private Image img1;
    private Image img2;
    private Type type;
    private int count;
    protected HitState hitState;
    private boolean isBroken;
    public BrokenBricks(int x, int y,int width,int height,Type type) {
        super(x, y, 32, 32, State.BURN);
        this.hitState = BrokenBricks.HitState.NORMAL;
        this.type =type;
        img = ImageController.getInstance().tryGet(type.path());
        img1 = ImageController.getInstance().tryGet("/brickA2.png");
        img2 = ImageController.getInstance().tryGet("/30.png");
>>>>>>> origin/火焰修改+地圖修改
        count = 0;
        isBroken = false;
    }

    public void collision() {
        this.count++;
<<<<<<< HEAD
        if (count >= 2) {
            count = 2;
=======
        System.out.println("數字多少"+count);
        if (count >= 2) {
>>>>>>> origin/火焰修改+地圖修改
            isBroken = true;
        }
    }

    public boolean IsBroken() {
        return this.isBroken;
    }

    @Override
    public void paintComponent(Graphics g) {
<<<<<<< HEAD
        if (this.hitState == HitState.NORMAL) {
            g.drawImage(img, painter().left(), painter().top(), null);
        } else if (this.hitState == HitState.FIRST) {
            g.drawImage(img1, painter().left(), painter().top(), null);
        } else g.drawImage(img2, painter().left(), painter().top(), null);
=======
        if (count==0) {
            g.drawImage(img, painter().left(), painter().top(), null);
        } else if (count==1) {
            g.drawImage(img1, painter().left(), painter().top(), null);
        }else {
            g.drawImage(img2,painter().left(),painter().top(),null);
            return;
        }
>>>>>>> origin/火焰修改+地圖修改
    }

    @Override
    public void update() {
        if (count == 0) {
            this.hitState = HitState.NORMAL;
        } else if (count == 1) {
            this.hitState = HitState.FIRST;
<<<<<<< HEAD
        } else if (count == 2) {
            this.hitState = HitState.SECOND;
=======
        } else{
            this.hitState = HitState.SECOND;
            return;
>>>>>>> origin/火焰修改+地圖修改
        }

    }

<<<<<<< HEAD
=======
    public enum  Type{
        BROKENBRICKS1(Path.BrokenBricks.BROKENBRICKS_1);
        private String path;
        Type(String path){
            this.path = path;
        }
        public String path(){
            return path;
        }

    }



>>>>>>> origin/火焰修改+地圖修改
    public enum HitState {
        NORMAL,
        FIRST,
        SECOND;
    }
}
