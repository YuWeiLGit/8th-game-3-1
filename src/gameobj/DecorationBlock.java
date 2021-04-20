package gameobj;

import controllers.ImageController;
import utils.Path;

import java.awt.*;

public class DecorationBlock extends GameObject{
    private Type type;
    private Image image;

    public enum Type{
        DecorationBlock_1(Path.DecorationBlock.DECORATIONBLOCK_1),
        DecorationBlock_2(Path.DecorationBlock.DECORATIONBLOCK_2),
        DecorationBlock_3(Path.DecorationBlock.DECORATIONBLOCK_3),
        DecorationBlock_4(Path.DecorationBlock.DECORATIONBLOCK_4),
        DecorationBlock_5(Path.DecorationBlock.DECORATIONBLOCK_5),
        DecorationBlock_6(Path.DecorationBlock.DECORATIONBLOCK_6),
        DecorationBlock_7(Path.DecorationBlock.DECORATIONBLOCK_7),
        DecorationBlock_8(Path.DecorationBlock.DECORATIONBLOCK_8),
        DecorationBlock_9(Path.DecorationBlock.DECORATIONBLOCK_9),
        DecorationBlock_10(Path.DecorationBlock.DECORATIONBLOCK_10),
        DecorationBlock_11(Path.DecorationBlock.DECORATIONBLOCK_11),
        DecorationBlock_12(Path.DecorationBlock.DECORATIONBLOCK_12),
        DecorationBlock_13(Path.DecorationBlock.DECORATIONBLOCK_13),
        DecorationBlock_15(Path.DecorationBlock.DECORATIONBLOCK_15),
        DecorationBlock_16(Path.DecorationBlock.DECORATIONBLOCK_16),
        DecorationBlock_17(Path.DecorationBlock.DECORATIONBLOCK_17),
        DecorationBlock_18(Path.DecorationBlock.DECORATIONBLOCK_18),
        DecorationBlock_20(Path.DecorationBlock.DECORATIONBLOCK_20),
        DecorationBlock_21(Path.DecorationBlock.DECORATIONBLOCK_21),
        DecorationBlock_22(Path.DecorationBlock.DECORATIONBLOCK_22),
        DecorationBlock_23(Path.DecorationBlock.DECORATIONBLOCK_23),
        DecorationBlock_24(Path.DecorationBlock.DECORATIONBLOCK_24),
        DecorationBlock_25(Path.DecorationBlock.DECORATIONBLOCK_25),
        DecorationBlock_26(Path.DecorationBlock.DECORATIONBLOCK_26),
        DecorationBlock_27(Path.DecorationBlock.DECORATIONBLOCK_27),
        DecorationBlock_28(Path.DecorationBlock.DECORATIONBLOCK_28),
        DecorationBlock_29(Path.DecorationBlock.DECORATIONBLOCK_29),
        DecorationBlock_30(Path.DecorationBlock.DECORATIONBLOCK_30),
        DecorationBlock_31(Path.DecorationBlock.DECORATIONBLOCK_31),
        DecorationBlock_32(Path.DecorationBlock.DECORATIONBLOCK_32),
        DecorationBlock_33(Path.DecorationBlock.DECORATIONBLOCK_33),
        DecorationBlock_34(Path.DecorationBlock.DECORATIONBLOCK_34),
        DecorationBlock_35(Path.DecorationBlock.DECORATIONBLOCK_35),
        DecorationBlock_36(Path.DecorationBlock.DECORATIONBLOCK_36),
        DecorationBlock_39(Path.DecorationBlock.DECORATIONBLOCK_39),
        DecorationBlock_40(Path.DecorationBlock.DECORATIONBLOCK_40),
        DecorationBlock_Skull(Path.DecorationBlock.DECORATIONBLOCK_SKULL);
        private String path;
        Type( String path) {
            this.path = path;
        }
        public String path(){
                return path;
            }
        }
    public DecorationBlock(int x, int y, int width, int height,Type type) {
        super(x, y, width, height);
        this.type = type;
        image = ImageController.getInstance().tryGet(type.path());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, painter().left(), painter().top(),null);

    }

    @Override
    public void update() {

    }
}
