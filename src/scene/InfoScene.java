package scene;

import controllers.ImageController;
import utils.CommandSolver;
import utils.Delay;

import java.awt.*;

public class InfoScene extends Scene{
    private Image image;
    private Delay delay;

    public InfoScene(){
        image = ImageController.getInstance().tryGet("/back.png");
        delay = new Delay(30);

    }



    @Override
    public void sceneBegin() {
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
        return null;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image,0,0,null);

    }

    @Override
    public void update() {

    }
}
