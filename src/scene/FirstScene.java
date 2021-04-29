package scene;

import controllers.AudioResourceController;
import controllers.ImageController;
import controllers.SceneController;
import gameobj.Title;
import utils.CommandSolver;

import java.awt.*;

public class FirstScene extends Scene {
    private Image back0;
    private Image back;
    private Image back1;
    private int count;
    private Image title;
    private Image launch;
    private Image launch2;
    private boolean isLaunch;


    @Override
    public void sceneBegin() {
        back0 = ImageController.getInstance().tryGet("/back111.png");
        back = ImageController.getInstance().tryGet("/start0.png");
        back1 = ImageController.getInstance().tryGet("/start5.png");
        title = ImageController.getInstance().tryGet("/title.png");
        launch = ImageController.getInstance().tryGet("/launch.png");
        launch2 = ImageController.getInstance().tryGet("/launch2.png");
        count = 0;
        isLaunch=false;
    }

    @Override
    public void sceneEnd() {
        AudioResourceController.getInstance().stop("/entre.wav");
    }

    @Override
    public CommandSolver.MouseListener mouseListener() {
        return (e, state, trigTime) -> {
            if (state != null) {
                int x = e.getX();
                int y = e.getY();
                if (count>200) {
                    if (380 <= x && x <= 680 && y >= 900 && y <= 946) {
                        isLaunch=true;
                    } else {
                        isLaunch=false;
                    }
                }
            }
            if(isLaunch){
                if(state== CommandSolver.MouseState.RELEASED){
                    SceneController.getInstance().changeScene(new IntroScene());
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
        g.drawImage(back0, 0, 0, null);
        if (count == 60) {
            g.drawImage(back, 50, 0, 1130, 1160, 0, 0, 1080, 1160, null);
        } else if (count == 61) {
            g.drawImage(back, -50, 0, 1030, 1160, 0, 0, 1080, 1160, null);
        } else if (count == 120) {
            g.drawImage(back, 100, 100, 1180, 1260, 0, 0, 1080, 1160, null);
        } else if (count == 121) {
            g.drawImage(back, -100, -100, 980, 1060, 0, 0, 1080, 1160, null);
        } else if (count == 131) {
            g.drawImage(back, 100, 0, 1180, 1160, 0, 0, 1080, 1160, null);
        } else if (count == 132) {
            g.drawImage(back, -100, 0, 980, 1160, 0, 0, 1080, 1160, null);
        } else if (count == 140) {
            g.drawImage(back, 100, 100, 1180, 1260, 0, 0, 1080, 1160, null);
        } else if (count == 141) {
            g.drawImage(back, -100, -100, 980, 1060, 0, 0, 1080, 1160, null);
        } else if (count == 150) {
            g.drawImage(back, 100, 100, 1180, 1260, 0, 0, 1080, 1160, null);
        } else if (count == 151) {
            g.drawImage(back, -100, -100, 980, 1060, 0, 0, 1080, 1160, null);
        } else {
            g.drawImage(back, 0, 0, 1080, 1160, 0, 0, 1080, 1160, null);
        }
        if (count > 160) {
            g.drawImage(back1, 0, 0, null);
            g.drawImage(title, 115, 350, null);
        }
        if (count > 200) {
            g.drawImage(launch, 380, 900, null);
        if(isLaunch){
            g.drawImage(launch2, 380, 900, null);}
        }
    }


    @Override
    public void update() {
        if (count == 1) {
            AudioResourceController.getInstance().shot("/tutu1.wav");
        }
        if (count == 150) {
            AudioResourceController.getInstance().loop("/entre.wav", 20);

        }
        count++;
    }
}
