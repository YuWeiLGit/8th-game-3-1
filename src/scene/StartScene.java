package scene;

import controllers.ImageController;
import controllers.SceneController;
import gameobj.GameObject;
import gameobj.MillenniumFalcon;
import gameobj.StartButton;
import utils.CommandSolver;

import java.awt.*;
import java.awt.event.MouseEvent;

public class StartScene extends Scene {
    private boolean show;
    private boolean startPaint;
    private Image image;
    private MillenniumFalcon startScenePic;
    private StartButton startButton;

    @Override
    public void sceneBegin() {
        image = ImageController.getInstance().tryGet("/back.png");
        startScenePic = new MillenniumFalcon(-30, 0, 32, 32, GameObject.State.NULL);
        startButton = new StartButton(startScenePic.painter().centerX() + 520, startScenePic.painter().centerY() + 400, 170, 100, GameObject.State.NULL);
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public CommandSolver.MouseListener mouseListener() {
        return (e, state, trigTime) -> {
            if (state != null) {
                int x = e.getX();
                int y = e.getY();
                if (startPaint) {
                    if (415 <= x && x <= 585 && y >= 300 && y <= 400) {
                        startButton.isTouch();
                        System.out.println("!!!");
                    } else {
                        startButton.notTouch();
                    }
                }
            }
        if(startButton.isTouched()){
            if(state== CommandSolver.MouseState.CLICKED){
                SceneController.getInstance().changeScene(new MapScene());
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
        g.drawImage(image, 0, 0, null);
        startScenePic.paintComponent(g);
        if (startPaint) {
            startButton.paint(g);
        }
    }

    @Override
    public void update() {
        if (startScenePic.painter().centerX() < 350) {
            startScenePic.painter().offset(4, 2);
        } else {
            show = true;
        }
        if (show) {
            startPaint = true;
            show = false;
//            System.out.println("L:"+startButton.painter().left()+
//                                "T:"+startButton.painter().top()+
//                                "R:"+startButton.painter().right()+
//                                "B:"+startButton.painter().bottom()    );
        }


    }
}