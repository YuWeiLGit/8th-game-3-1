package scene;

import controllers.ImageController;
import controllers.SceneController;
import menumodule.menu.BackgroundType;
import menumodule.menu.EditText;
import menumodule.menu.Style;
import utils.CommandSolver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EndScene extends Scene {
    private Image image;
    private String name;
    private EditText editText;
    private Image img;
    private Image img2;
    private boolean isPress;
    public EndScene(String name){
        this.name=name;
    }
    @Override
    public void sceneBegin() {
        String s=name;
    image= ImageController.getInstance().tryGet("/end.png");
        Style name = new Style.StyleRect(100, 100, false, new BackgroundType.BackgroundColor(Color.black))
                .setTextColor(Color.white)
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.HANGING_BASELINE, 50))
                .setText(s);
        isPress = false;
        img = ImageController.getInstance().tryGet("/shift.png");
        img2 = ImageController.getInstance().tryGet("/Shift2.png");
        editText = new EditText(450+s.length()*20, 575,"",name);
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
                if (commandCode == 1) {
                    SceneController.getInstance().changeScene(new RankScene());
                }
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

    g.drawImage(image,-20,-20,null);
    editText.paint(g);
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



