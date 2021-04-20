package menumodule.menumodule;


import controllers.SceneController;
import menumodule.scene.MenuScene;
import utils.*;
import utils.CommandSolver.KeyListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GI implements GameKernel.UpdateInterface,GameKernel.PaintInterface, CommandSolver.MouseListener, CommandSolver.KeyListener {

//    public GI() throws IOException {
//        SceneController.getInstance().changeScene(new MenuScene());
//    }

    @Override
    public void paint(Graphics g) { //繪畫
        SceneController.getInstance().paint(g);
    }

    @Override
    public void update() { //邏輯
        SceneController.getInstance().update();
    }

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
//        if (SceneController.getInstance().mouseListener() != null) {
//            SceneController.getInstance().mouseListener().mouseTrig(e, state, trigTime);
//        }
    }

    @Override
    public void keyPressed(int commandCode, long trigTime) {
//        KeyListener kl = SceneController.getInstance().keyListener();
//        if (kl != null) {
//            kl.keyPressed(commandCode, trigTime);
//        }
    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
//        KeyListener kl = SceneController.getInstance().keyListener();
//        if (kl != null) {
//            kl.keyReleased(commandCode, trigTime);
//        }
    }

    @Override
    public void keyTyped(char c, long trigTime) {
//        if (SceneController.getInstance().keyListener() != null) {
//            SceneController.instance().keyListener().keyTyped(c, trigTime);
//        }
//    }

}}
