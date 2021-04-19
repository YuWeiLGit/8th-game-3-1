package scene;

import camera.Camera;
import controllers.ImageController;
import gameobj.BasicBlock;
import gameobj.DecorationBlock;
import gameobj.GameObject;
import gameobj.SpaceShip;
import maploader.MapInfo;
import maploader.MapLoader;
import utils.GameKernel;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneTool implements GameKernel.UpdateInterface, GameKernel.PaintInterface {
    private Image background;
    private MapLoader mapLoader;
    private ArrayList<SpaceShip> spaceShips;
    private ArrayList<MapInfo> mapInfo;
    private ArrayList<GameObject> basicBlock;
    private ArrayList<DecorationBlock> decorationBlocks;
    private Camera cam;

    //讓外面的人一定要透過gen才能創建SceneTool
    private SceneTool(Image background , MapLoader maploader, ArrayList<SpaceShip> spaceShips, ArrayList<GameObject> basicBlock, ArrayList<DecorationBlock> decorationBlocks
            , ArrayList<MapInfo> mapInfo, Camera cam) {
        this.background = background;
        this.mapLoader = maploader;
        this.basicBlock = basicBlock;
        this.decorationBlocks = decorationBlocks;
        this.mapInfo = mapInfo;
        this.cam = cam;

    }



    @Override
    public void paint(Graphics g) {
        if (cam != null) {
            g.drawImage(background, (int) cam.painter().left(), (int) cam.painter().top(), null);

            for (int i = 0; i < basicBlock.size(); i++) {
                if (cam.isCollision(basicBlock.get(i))) {
                    basicBlock.get(i).paint(g);
                }
            }
            for (int i = 0; i < basicBlock.size(); i++) {
                basicBlock.get(i).paint(g);
            }
            for(int i=0;i<decorationBlocks.size();i++){
                if(cam.isCollision(decorationBlocks.get(i))){
                    decorationBlocks.get(i).paint(g);
                }
            }
            for(int i =0;i<decorationBlocks.size();i++){
                decorationBlocks.get(i).paint(g);
            }
        }
        else {
            if (background != null) {
                g.drawImage(background, (int) cam.painter().left(), (int) cam.painter().top(), null);
            }
            for (int i = 0; i < basicBlock.size(); i++) {
                basicBlock.get(i).paint(g);
            }
            for(int i =0;i<decorationBlocks.size();i++){
                decorationBlocks.get(i).paint(g);
            }
        }
    }
    public void paintCamStart(Graphics g){
        cam.start(g);
    }
    public void paintCamEnd(Graphics g){
        cam.end(g);
    }

    @Override
    public void update() {
        if (cam != null) {
            cam.update();
        }
    }

public static class Builder {
    private SpaceShip spaceShip;
    private Image background;
    private MapLoader maploader;
    private ArrayList<SpaceShip> spaceShips;
    private ArrayList<MapInfo> mapInfo;
    private ArrayList<GameObject> basicBlock;
    private ArrayList<DecorationBlock> decorationBlocks;
    private Camera cam;


    //透過Builder去控制上面的這些東西
    public Builder() {
        spaceShips = new ArrayList<SpaceShip>();
        basicBlock = new ArrayList<>();
        mapInfo = new ArrayList<>();
        decorationBlocks = new ArrayList<>();
    }

    //鏈式調用
    //用於之後可以直接設定背景
    public Builder setBackground(String path) {
        background = ImageController.getInstance().tryGet(path);
        return this;
    }
    public Builder setSpaceShip(SpaceShip spaceShip) {
        this.spaceShip = spaceShip;
        return this;
    }

    //設定地圖
    public Builder setMaploader(String MapPath, String txtPath) {
        try {
            this.maploader = new MapLoader(MapPath, txtPath);
        } catch (IOException ex) {
            Logger.getLogger(SceneTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        mapInfo = maploader.combineInfo();
        return this;
    }

    //設定相機
    public Builder setCam(int width, int height,GameObject ChaseObj) {
        this.cam = new Camera.Builder(width, height)
                .setChaseObj(ChaseObj, 1, 1)
                .setCameraStartLocation(0,0)
                .gen();
        //之前沒有用
        cam.setObj(ChaseObj);
        return this;
    }
    public void setCam(Camera cam) {
        this.cam = cam;
    }

    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    public void setSpaceShips(SpaceShip spaceShip) {
        this.spaceShip = spaceShip;
    }

    //透過gen創建SceneTool的建構子
    public SceneTool gen() {
        return new SceneTool(background, maploader, spaceShips, basicBlock,
                decorationBlocks, mapInfo, cam);
    }

}

    public MapLoader getMapLoader() {
        return mapLoader;
    }

    public Camera getCam() {
        return cam;
    }

    public Image getBackground() {
        return background;
    }

    public ArrayList<GameObject> getBasicBlock() {
        return basicBlock;
    }
    public ArrayList<SpaceShip> getSpaceShips() {
        return spaceShips;
    }

    public ArrayList<DecorationBlock> getDecorationBlocks() {
        return decorationBlocks;
    }

    public ArrayList<MapInfo> getMapInfo() {
        return mapInfo;
    }

    public void genMap() {
        basicBlock = mapLoader.creatObjectArray("111", 32, mapInfo
                , (String gameObject, String name, MapInfo mapInfo, int MapObjectSize) -> {
                    if (name.equals("collisionBlock_bot")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_BLOCK_BOT);
                    }
                    if (name.equals("collisionBlock_left")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_LEFT);
                    }
                    if (name.equals("collisionBlock_left_bot")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_LEFT_BOT);
                    }
                    if (name.equals("collisionBlock_left_top")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_LEFT_TOP);
                    }
                    if (name.equals("collisionBlock_right")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_RIGHT);
                    }
                    if (name.equals("collisionBlock_right_bot")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_RIGHT_BOT);
                    }
                    if (name.equals("collisionBlock_right_top")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_RIGHT_TOP);
                    }
                    if (name.equals("collisionBlock_top")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_TOP);
                    }
                    if (name.equals("collisionBlock_bot_left")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_BOT_LEFT);
                    }
                    if (name.equals("collisionBlock_bot_right")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_BOT_RIGHT);
                    }
                    if (name.equals("collisionBlock_top_left")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_TOP_LEFT);
                    }
                    if (name.equals("collisionBlock_top_right")) {
                        return new BasicBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, BasicBlock.Type.COLLISION_Block_TOP_RIGHT);
                    }
                    if (name.equals("1")){
                        DecorationBlock tmp =  new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize,DecorationBlock.Type.DecorationBlock_1);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("2")){
                        DecorationBlock tmp =  new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize,DecorationBlock.Type.DecorationBlock_2);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("3")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_3);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("4")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_4);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("5")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_5);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("6")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_6);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("7")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_7);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("8")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_8);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("9")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_9);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("10")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_10);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("11")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_11);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("12")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_12);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("13")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_13);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("15")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_15);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("16")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_16);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("17")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_17);
                        decorationBlocks.add(tmp);
                        return      null                   ;
                    }
                    if (name.equals("18")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_18);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("20")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_20);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("21")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_21);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("22")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_22);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("23")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_23);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("24")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_24);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("25")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_25);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("26")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_26);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("27")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_27);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("28")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_28);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("29")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_29);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("30")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_30);
                        decorationBlocks.add(tmp);
                        return  null;
                    }
                    if (name.equals("31")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_31);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("32")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_32);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("33")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_33);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("34")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_34);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("35")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_35);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("36")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_36);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("39")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_39);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    if (name.equals("40")) {
                        DecorationBlock tmp = new DecorationBlock(mapInfo.getX() * MapObjectSize, mapInfo.getY() * MapObjectSize, mapInfo.getSizeX() * MapObjectSize, mapInfo.getSizeY() * MapObjectSize, DecorationBlock.Type.DecorationBlock_40);
                        decorationBlocks.add(tmp);
                        return null;
                    }
                    return null;
                });

    }

    //清除Scene用
    public void clear() {
        background = null;
        mapLoader = null;
        spaceShips = null;
        decorationBlocks = null;

    }
}
