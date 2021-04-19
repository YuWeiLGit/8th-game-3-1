package scene;

import camera.MapInformation;
import controllers.ImageController;
import gameobj.BasicBlock;
import gameobj.SpaceShip;
import utils.CommandSolver;

import java.awt.*;

import static utils.Global.*;

//測試地圖用
//並創建SceneTool
public class GameScene extends Scene {
    private SceneTool st;


    @Override
    public void sceneBegin() {
        MapInformation.setMapInfo(0, 0, GAME_SCENE_WIDTH, GAME_SCENE_HEIGHT);
        st = new SceneTool.Builder()
                .setMaploader("/genMap2.bmp", "/genMap2.txt")
                .setSpaceShip((new SpaceShip(300,2000,10)))
                .setCam(GAME_SCENE_WIDTH,GAME_SCENE_HEIGHT)
                .gen();
        st.genMap();

    }

    @Override
    public void sceneEnd() {
        ImageController.getInstance().clear();

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
        st.paint(g);
        //用來碰撞判定的
        //st.getBasicBlock().get(i)
    }

    @Override
    public void update() {
        st.update();
    }
}
