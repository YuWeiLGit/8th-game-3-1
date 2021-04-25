package gameobj;

import controllers.ImageController;
import utils.Path;

import java.awt.*;

public class StrikeBlock extends GameObject{
    private Type type;
    private Image image;

    public StrikeBlock(int x, int y, int width, int height,Type type) {
        super(x, y, width, height);
        this.type =type;
        image = ImageController.getInstance().tryGet(type.path);
    }

    public enum Type {
        StrikeBlock_1(Path.StrikeBlock.STRIKEBLOCK_1),
        StrikeBlock_2(Path.StrikeBlock.STRIKEBLOCK_2);
        private String path;
        Type(String path) {
            this.path = path;
        }
        public String Path() {
            return path;
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, painter().left(),painter().top(),null);

    }

    @Override
    public void update() {

    }
}
