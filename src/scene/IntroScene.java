package scene;

import controllers.AudioResourceController;
import controllers.ImageController;
import controllers.SceneController;
import menumodule.menu.EditText;
import utils.CommandSolver;

import java.awt.*;

public class IntroScene extends Scene {
    private Image SoloImage;
    private Image SoloImage2;
    private Image DulImage;
    private Image DulImage2;
    private Image back;
    private Image campaign;
    private Image gladiator;
    private int countSolo;
    private int countDul;
    private int mx;
    private int my;
    private boolean showSolo;
    private boolean showDul;

    @Override
    public void sceneBegin() {
        SoloImage = ImageController.getInstance().tryGet("/solo5.png");
        SoloImage2 = ImageController.getInstance().tryGet("/solo2.png");
        back = ImageController.getInstance().tryGet("/vortex.png");
        DulImage = ImageController.getInstance().tryGet("/dul.png");
        DulImage2 = ImageController.getInstance().tryGet("/dul2.png");
        campaign=ImageController.getInstance().tryGet("/campaign.png");
        gladiator=ImageController.getInstance().tryGet("/gladiator.png");
        AudioResourceController.getInstance().loop("/intro.wav", 20);
        countSolo = 0;
        countDul = 0;
        showSolo = false;
        showDul = false;
    }

    @Override
    public void sceneEnd() {
        AudioResourceController.getInstance().stop("/intro.wav");
    }

    @Override
    public CommandSolver.MouseListener mouseListener() {
        return (e, state, trigTime) -> {
            if (state != null) {
                mx = e.getX();
                my = e.getY();
            }
            if(showSolo){
                if(state== CommandSolver.MouseState.RELEASED){
                    SceneController.getInstance().changeScene(new GameScene());
                }
            }
            if(showDul){
                if(state== CommandSolver.MouseState.RELEASED){
                    SceneController.getInstance().changeScene(new GameScene());
                }
            }
        };
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(back, 0, 0, null);
        g.drawImage(SoloImage, 50, 50, null);
        g.drawImage(SoloImage2, 50, 50, 476, 50 + 681
                , 0 + countSolo, 50 + countSolo, 376 + countSolo, 631 + countSolo, null);
        if(countDul>=100){
        g.drawImage(DulImage, 550, 50, null);}
        else if(countDul>=1&&countDul<100){
        g.drawImage(DulImage2, 550, 50, null);}
        if(countDul==0){
            g.drawImage(DulImage, 550, 50, null);}
        if(showSolo){
            g.drawImage(campaign,10,800,null);
        }
        if(showDul){
            g.drawImage(gladiator,10,800,null);
        }

    }

    @Override
    public void update() {
        if ((47 <= mx && mx <= 463 && my >= 45 && my <= 720)) {
            if (countSolo < 50)
                countSolo++;
            showSolo = true;
        } else if ((550 <= mx && mx <= 1004 && my >= 45 && my <= 720)) {
            showDul = true;
            if (countDul > 200){
                countDul=0;
            }
                countDul++;

        } else {
            showDul = false;
            showSolo = false;
            countDul = 0;
            countSolo = 0;
        }
    }
}

