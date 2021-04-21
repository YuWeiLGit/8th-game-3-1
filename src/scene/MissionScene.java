package scene;

import controllers.AudioResourceController;
import controllers.ImageController;
import controllers.SceneController;
import menumodule.menu.BackgroundType;
import menumodule.menu.EditText;
import menumodule.menu.Style;
import utils.CommandSolver;

import java.awt.*;

public class MissionScene extends Scene{
    private Image back0;
    private EditText editText;
    private Image launch;
    private Image launch2;
    private boolean isLaunch;

    @Override
    public void sceneBegin() {
        back0 = ImageController.getInstance().tryGet("/mission.png");
//        AudioResourceController.getInstance().loop("/mission.wav",20);
        Style name = new Style.StyleRect(100, 100, false, new BackgroundType.BackgroundColor(Color.black))
                .setTextColor(Color.white)
                .setHaveBorder(false)
                 .setTextFont(new Font("", Font.ROMAN_BASELINE, 46))
                .setText("");

        editText = new EditText(350, 145," enter your name",name);
        editText.setCursorColor(Color.white);
        editText.isFocus();
        launch = ImageController.getInstance().tryGet("/launch.png");
        launch2 = ImageController.getInstance().tryGet("/launch2.png");
        isLaunch=false;
    }

    @Override
    public void sceneEnd() {
        AudioResourceController.getInstance().stop("/mission.wav");
    }

    @Override
    public CommandSolver.MouseListener mouseListener() {
        return (e, state, trigTime) -> {
            if (state != null) {
                int x = e.getX();
                int y = e.getY();
                if (editText.getLength()!=0) {
                    if (380 <= x && x <= 680 && y >= 900 && y <= 946) {
                        isLaunch=true;
                    } else {
                        isLaunch=false;
                    }
                }
            }
            if(isLaunch){
                if(state== CommandSolver.MouseState.RELEASED){
                    SceneController.getInstance().changeScene(new GameScene(editText.getEditText()));
                }
            }
        };
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {

            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {

            }

            @Override
            public void keyTyped(char c, long trigTime) {
                if(editText.getIsFocus()){
                    editText.keyTyped(c);
                }
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(back0, -20, -50, null);
        editText.paint(g);
        if(editText.getLength()!=0){
            g.drawImage(launch,380,900,null);
        }
        if(isLaunch){
            g.drawImage(launch2,380,900,null);
        }
    }

    @Override
    public void update() {

    }
}
