package scene;

import controllers.AudioResourceController;
import controllers.ImageController;
import controllers.SceneController;
import menumodule.menu.BackgroundType;
import menumodule.menu.EditText;
import menumodule.menu.Style;
import utils.CommandSolver;

import javax.jws.soap.SOAPMessageHandlers;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EndScene extends Scene {
    private Image image;
    private String name;
    private EditText editText;
    private EditText editTextScore;
    private Image img;
    private Image img2;
    private boolean isPress;
    private int time;

    public EndScene(String name,int s) {
        this.name = name;
        this.time=s;
    }

    @Override
    public void sceneBegin() {
<<<<<<< HEAD
        String s = name;
        String t=String.valueOf(time);
        image = ImageController.getInstance().tryGet("/end.png");
        Style name = new Style.StyleRect(100, 100, false, new BackgroundType.BackgroundColor(Color.black))
                .setTextColor(Color.white)
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.HANGING_BASELINE, 50))
                .setText(s);
        Style score = new Style.StyleRect(100, 100, false, new BackgroundType.BackgroundColor(Color.black))
                .setTextColor(Color.white)
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.HANGING_BASELINE, 50))
                .setText(t+"s");
        isPress = false;
        img = ImageController.getInstance().tryGet("/shift.png");
        img2 = ImageController.getInstance().tryGet("/Shift2.png");
=======
        AudioResourceController.getInstance().loop("/endSecne1.wav",20);
>>>>>>> origin/火焰修改+地圖修改

        editText = new EditText(470 + s.length() * 20, 571, "", name);
        editTextScore=new EditText(470 + s.length() * 20, 640, "", score);
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
                if (commandCode == 4) {
                    isPress = true;
                }
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
                if (commandCode == 1) {
                    SceneController.getInstance().changeScene(new RankScene());
                }
                if (commandCode == 4) {
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

        g.drawImage(image, -20, -20, null);
        editText.paint(g);
        editTextScore.paint(g);
    }

    @Override
    public void update() {

    }
}



