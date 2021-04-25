package scene;

import controllers.AudioResourceController;
import controllers.ImageController;
import controllers.SceneController;
import gameobj.DemoVedio;
import menumodule.menu.BackgroundType;
import menumodule.menu.EditText;
import menumodule.menu.Style;
import utils.CommandSolver;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SimpleMissionScene extends Scene {
    private Image back0;
    private EditText editText;
    private Image launch;
    private Image launch2;
    private boolean isLaunch;
    private boolean isPress;
    private Image img;
    private Image img2;
    private Image missionImage;
    private Image missionImage1;
    private Image missionImage2;
    private Image missionPoint;
    private Image missionPoint2;
    private Image demoImage;
    private Image mousePoint1;
    private Image mousePoint2;
    private Image mousePoint3;
    private int count;
    private DemoVedio demoVedio;
    private boolean isMousePress;

    @Override
    public void sceneBegin() {
        isPress = false;
        demoVedio = new DemoVedio(400, 363);
        back0 = ImageController.getInstance().tryGet("/mission.png");
        AudioResourceController.getInstance().loop("/mission.wav",20);
        img = ImageController.getInstance().tryGet("/shift.png");
        img2 = ImageController.getInstance().tryGet("/Shift2.png");
        missionImage = ImageController.getInstance().tryGet("/Mission0.png");
        missionImage1 = ImageController.getInstance().tryGet("/Mission1.png");
        missionImage2 = ImageController.getInstance().tryGet("/Mission2.png");
        missionPoint = ImageController.getInstance().tryGet("/missionPoint2.png");
        missionPoint2 = ImageController.getInstance().tryGet("/missionPoint3.png");
        demoImage = ImageController.getInstance().tryGet("/demo1.png");
        mousePoint1 = ImageController.getInstance().tryGet("/mousePoint3.png");
        mousePoint2 = ImageController.getInstance().tryGet("/mousePoint4.png");
        mousePoint3 = ImageController.getInstance().tryGet("/mousePoint5.png");
//        AudioResourceController.getInstance().loop("/mission.wav",20);

        Style name = new Style.StyleRect(100, 100, false, new BackgroundType.BackgroundColor(Color.black))
                .setTextColor(Color.white)
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.ROMAN_BASELINE, 46))
                .setText("");

        editText = new EditText(350, 145, " enter your name", name);
        editText.setCursorColor(Color.white);
        editText.isFocus();
        launch = ImageController.getInstance().tryGet("/launch3.png");
        launch2 = ImageController.getInstance().tryGet("/launch4.png");
        isLaunch = false;
        isMousePress = false;
    }

    @Override
    public void sceneEnd() {
        AudioResourceController.getInstance().stop("/mission.wav");
    }

    @Override
    public CommandSolver.MouseListener mouseListener() {
        return (e, state, trigTime) -> {
            if (state != null) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    isMousePress = true;
                    demoVedio.play();
                }
            }
            if(state== CommandSolver.MouseState.RELEASED){
                isMousePress = false;
            }
            if (state != null) {
                int x = e.getX();
                int y = e.getY();
                if (editText.getLength() != 0) {
                    if (780 <= x && x <= 980 && y >= 900 && y <= 946) {
                        isLaunch = true;
                    } else {
                        isLaunch = false;
                    }
                }
            }
            if (isLaunch) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    SceneController.getInstance().changeScene(new SimpleModeScene(editText.getEditText()));
                }
            }
        };
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
                if (commandCode == 4) {
                    isPress = false;
                    count++;
                    if (count > 2) {
                        count = 0;
                    }
                }
                if (editText.getLength() != 0) {
                    if (commandCode == 1) {
                        SceneController.getInstance().changeScene(new GameScene(editText.getEditText()));
                    }
                }
            }

            @Override
            public void keyTyped(char c, long trigTime) {
                if (editText.getIsFocus()) {
                    editText.keyTyped(c);
                }
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(back0, -20, -50, null);
        if (!isMousePress) {
            g.drawImage(mousePoint2, 800, 400, 80, 100, null);
        } else {
            g.drawImage(mousePoint3, 800, 400, 80, 100, null);
        }
        g.drawImage(missionPoint2, 50, 250, 700, 230, null);
        demoVedio.paintComponent(g);
//        g.drawImage(demoImage,70,300,625,198,null);
        g.drawImage(missionPoint, 50, 800, 700, 185, null);
        switch (count) {
            case 0:
                g.drawImage(missionImage, 70, 850, 651, 92, null);
                break;
            case 1:
                g.drawImage(missionImage1, 70, 850, 651, 92, null);
                break;
            case 2:
                g.drawImage(missionImage2, 70, 850, 651, 92, null);
                break;
        }
        if (!isPress) {
            g.drawImage(img, 600, 888, 100, 50, null);
        } else {
            g.drawImage(img2, 600, 888, 100, 50, null);
        }
        editText.paint(g);
        if (editText.getLength() != 0) {
            g.drawImage(launch, 780, 900, null);
        }
        if (isLaunch) {
            g.drawImage(launch2, 780, 900, null);
        }
    }

    @Override
    public void update() {

    }
}

