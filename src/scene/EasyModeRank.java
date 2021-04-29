package scene;

import controllers.AudioResourceController;
import controllers.ImageController;
import controllers.RankControll;
import controllers.SceneController;
import menumodule.menu.BackgroundType;
import menumodule.menu.EditText;
import menumodule.menu.Style;
import utils.CommandSolver;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class EasyModeRank extends Scene{
    private ArrayList<RankControll> rankControlls;
    private Image image;
    private EditText editText;
    private ArrayList<EditText> editTexts;

    @Override
    public void sceneBegin() {
        String path = (HardModeRankScene.class).getProtectionDomain().getCodeSource().getLocation().getFile();
        AudioResourceController.getInstance().loop("/rank.wav",20);
        path = path + "rank1.txt";
        image = ImageController.getInstance().tryGet("/rank2.png");
        rankControlls = new ArrayList<>();
        editTexts = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String s;
            while ((s = br.readLine()) != null) {
                String[] tmp = s.split("/");
                rankControlls.add(new RankControll(Integer.parseInt(tmp[1]), tmp[0]));
            }
        } catch (Exception ex) {
            try {
                System.out.println("!");
                BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            return;
        }
        int y = 420;
        //第一名
        if (rankControlls.size() > 0) {
            editTexts.add(new EditText(225, y, "", getStyle(rankControlls.get(0).getName())));
            editTexts.add(new EditText(725, y, "", getStyle(String.valueOf(rankControlls.get(0).getScore()) + " s")));
        }
        //第二名
        if (rankControlls.size() > 1) {
            editTexts.add(new EditText(225, y + 80, "", getStyle(rankControlls.get(1).getName())));
            editTexts.add(new EditText(725, y + 80, "", getStyle(String.valueOf(rankControlls.get(1).getScore()) + " s")));
        }
        //第三名
        if (rankControlls.size() > 2) {
            editTexts.add(new EditText(225, y + 160, "", getStyle(rankControlls.get(2).getName())));
            editTexts.add(new EditText(725, y + 160, "", getStyle(String.valueOf(rankControlls.get(2).getScore()) + " s")));
        }
        //第四名
        if (rankControlls.size() > 3) {
            editTexts.add(new EditText(225, y + 240, "", getStyle(rankControlls.get(3).getName())));
            editTexts.add(new EditText(725, y + 240, "", getStyle(String.valueOf(rankControlls.get(3).getScore()) + " s")));
        }
        //第四名
        if (rankControlls.size() > 4) {
            editTexts.add(new EditText(225, y + 320, "", getStyle(rankControlls.get(4).getName())));
            editTexts.add(new EditText(725, y + 320, "", getStyle(String.valueOf(rankControlls.get(4).getScore()) + " s")));
        }


    }

    @Override
    public void sceneEnd() {
        AudioResourceController.getInstance().stop("/rank.wav");
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

            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
                if (commandCode == 1) {
                    SceneController.getInstance().changeScene(new IntroScene());
                }
            }

            @Override
            public void keyTyped(char c, long trigTime) {

            }
        };
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, -20, -100, null);
        for (int i = 0; i < editTexts.size(); i++) {
            editTexts.get(i).paint(g);
        }
    }

    @Override
    public void update() {

    }

    private Style getStyle(String s) {
        return new Style.StyleRect(100, 100, false, new BackgroundType.BackgroundColor(Color.black))
                .setTextColor(Color.white)
                .setHaveBorder(false)
                .setTextFont(new Font("", Font.HANGING_BASELINE, 60))
                .setText(s);
    }
}
