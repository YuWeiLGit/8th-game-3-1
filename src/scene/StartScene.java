package scene;

import controllers.AudioResourceController;
import controllers.ImageController;
import controllers.SceneController;
import gameobj.GameObject;
import gameobj.MillenniumFalcon;
import gameobj.StartButton;
import gameobj.Title;
import javafx.scene.media.AudioClip;
import menumodule.menu.BackgroundType;
import menumodule.menu.EditText;
import menumodule.menu.Style;
import utils.CommandSolver;
import utils.Delay;

import java.awt.*;
import java.awt.event.MouseEvent;

public class StartScene extends Scene {
    private boolean show;
    private boolean startPaint;
    private Image image;
    private MillenniumFalcon startScenePic;
    private StartButton startButton;
    private Title title;
    private Delay delay;
    private EditText editText;

    @Override
    public void sceneBegin() {
        delay=new Delay(40);
        delay.play();
        image = ImageController.getInstance().tryGet("/back.png");
        startScenePic = new MillenniumFalcon(-30, 0, 32, 32, GameObject.State.NULL);
        startButton = new StartButton(startScenePic.painter().centerX() + 520, startScenePic.painter().centerY() + 400, 170, 100, GameObject.State.NULL);
        title=new Title(startScenePic.painter().centerX() + 520, startScenePic.painter().centerY()+ 450 , 780, 777, GameObject.State.NULL) ;
        AudioResourceController.getInstance().loop("/entre.wav",20);
        Style aa = new Style.StyleOval(100, 100, true, new BackgroundType.BackgroundColor(new Color(184, 134, 11)))
                .setTextColor(Color.BLACK)
                .setHaveBorder(true)
                .setBorderColor(new Color(230, 184, 0))
                .setBorderThickness(5)
                .setTextFont(new Font("", Font.TYPE1_FONT, 28))
                .setText(" ");
        String k=" name";
        editText = new EditText(300, 500, k,aa);

        editText.isFocus();
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
                if (startPaint) {
                    if (415 <= x && x <= 585 && y >= 350 && y <= 400) {
                        startButton.isTouch();
                    } else {
                        startButton.notTouch();
                    }
                }
            }
        if(startButton.isTouched()){
            if(state== CommandSolver.MouseState.RELEASED){
                SceneController.getInstance().changeScene(new IntroScene());
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
        g.drawImage(image, 0, 0, null);
        startScenePic.paintComponent(g);
        if (startPaint) {
            startButton.paint(g);
            title.paint(g);
        }
        editText.paint(g);
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
            title.isTouch();
            show = false;
            if(delay.count()){
                startButton.show();
            };
        }


    }
}
