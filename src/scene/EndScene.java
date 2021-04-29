package scene;

import controllers.AudioResourceController;
import controllers.ImageController;
import controllers.SceneController;
import menumodule.menu.BackgroundType;
import menumodule.menu.EditText;
import menumodule.menu.Style;
import utils.CommandSolver;

import java.awt.*;

public class EndScene extends Scene {
    private Image image;
    private String name;
    private EditText editText;
    private EditText editTextScore;
    private Image img;
    private Image img2;
    private boolean isPress;
    private int time;
    private boolean mode;
    public EndScene(String name,int s,boolean mode) {
        this.name = name;
        this.time=s;
        this.mode=mode;
    }

    @Override
    public void sceneBegin() {
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
        AudioResourceController.getInstance().loop("/endScene1.wav",20);
        editText = new EditText(470 + s.length() * 20, 571, "", name);
        editTextScore=new EditText(470 + s.length() * 20, 640, "", score);
    }

    @Override
    public void sceneEnd() {
        AudioResourceController.getInstance().stop("/endScene1.wav");
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
                    if(mode){
                    SceneController.getInstance().changeScene(new HardModeRankScene());}
                    else SceneController.getInstance().changeScene(new EasyModeRank());
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



