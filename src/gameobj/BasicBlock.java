package gameobj;

import controllers.ImageController;
import utils.Path;

import java.awt.*;

public class BasicBlock extends GameObject{
    private Type type;
    private Image image;
   protected boolean isPardon;
    public enum Type{
        COLLISION_BLOCK_BOT(Path.BasicBlock.COLLISIONBLOCK_BOT),
        COLLISION_Block_LEFT(Path.BasicBlock.COLLISIONBLOCK_LEFT),
        COLLISION_Block_LEFT_BOT(Path.BasicBlock.COLLISIONBLOCK_LEFT_BOT),
        COLLISION_Block_LEFT_TOP(Path.BasicBlock.COLLISIONBLOCK_LEFT_TOP),
        COLLISION_Block_RIGHT(Path.BasicBlock.COLLISIONBLOCK_RIGHT),
        COLLISION_Block_RIGHT_BOT(Path.BasicBlock.COLLISIONBLOCK_RIGHT_BOT),
        COLLISION_Block_RIGHT_TOP(Path.BasicBlock.COLLISIONBLOCK_RIGHT_TOP),
        COLLISION_Block_TOP(Path.BasicBlock.COLLISIONBLOCK_TOP),
        COLLISION_Block_BOT_LEFT(Path.BasicBlock.COLLISIONBLOCK_BOT_LEFT),
        COLLISION_Block_BOT_RIGHT(Path.BasicBlock.COLLISIONBLOCK_BOT_RIGHT),
        COLLISION_Block_TOP_LEFT(Path.BasicBlock.COLLISIONBLOCK_TOP_LEFT),
        COLLISION_Block_TOP_RIGHT(Path.BasicBlock.COLLISIONBLOCK_TOP_RIGHT);
        private String path;
        Type(String path){
            this.path =path;
        }
        public String path(){
            return  path;
        }
    }
    public BasicBlock(int x, int y, int width, int height, Type type) {
        super(x, y, width, height);
        this.type =type;
        image = ImageController.getInstance().tryGet(type.path());
        isPardon=false;
    }
    public  void changeIsPardon(boolean isPardon){
        this.isPardon=isPardon;
    }
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, painter().left(), painter().top(), null);

    }

    @Override
    public void update() {

    }
}
