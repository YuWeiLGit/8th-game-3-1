package scene;

import controllers.ImageController;
import utils.CommandSolver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EndScene extends Scene {
    private Image img;
    private Image img2;
    private boolean isPress;

    @Override
    public void sceneBegin() {
        boolean isPress = false;
        img = ImageController.getInstance().tryGet("/shift.png");
        img2 = ImageController.getInstance().tryGet("/Shift2.png");

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public CommandSolver.MouseListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                if(commandCode==4) {
                    isPress = true;
                }
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
            if(commandCode==4){
                isPress = false;
            }
            }

            @Override
            public void keyTyped(char c, long trigTime) {

            }
        };
    }


    @Override
    public void paint(Graphics g) {
        if (!isPress) {
            g.drawImage(img, 100, 100, 200, 100, null);
        }else{
            g.drawImage(img2, 100, 100, 200, 100, null);
        }
    }

    @Override
    public void update() {

    }
}



