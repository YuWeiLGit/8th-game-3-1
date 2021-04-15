import camera.MapInformation;
import controllers.ImageController;
import controllers.SceneController;
import scene.MapScene;
import scene.OpenScene;
import scene.StartScene;
import scene.TestScene;
import utils.CommandSolver;
import utils.GameKernel;
import utils.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
    private Image image;
    //遊戲建置步驟
    //1.導入遊戲素材包和CommandSolver-->Resource包和src平行的資料夾，並標記為Resource；CommandSolver在src之中
    //2.建置遊戲視窗-->用JFrame類建立
    //3.設置遊戲核心-->GameKernel繼承畫布Canvas專門處理paint和run機制
        //基礎屬性: 兩個介面的屬性  CommandSolver  畫一次圖/更新一次幾奈秒
        //3-1 建立paint和update介面  //3-2 處理繪畫雙緩衝機制 //3-3 處理run的遊戲迴圈，並確保遊戲邏輯運算不會落後於畫面
    //4.建立遊戲物件類-->GameObj類，是飛機 敵機 炸彈 等等類的抽象父類->因為要實現畫和update，但要推遲給子類去實現
        //子類自己有的屬性:例如 速度 方向 圖片屬性
    //5.建立場景類-->
       //5-1 Scene類是抽象父類(必要方法有繪畫和update以及開場和閉場)  //5-2 SceneController控制換場，實現繪畫及更新，及滑鼠鍵盤的介面 //5-3 GameScene繼承Scene，作為遊戲主場景

    //問題:1.延時器好像沒有起到作用?!!!
    public static void main(String[] args) {
        JFrame jframe=new JFrame();
        //滑鼠鼠標的圖片
        Image image = ImageController.getInstance().tryGet("/MousePointV2.png");
        SceneController sceneController=SceneController.getInstance(); //取得單例模式的控場實體
        sceneController.changeScene(new TestScene()); //一開始使用開場畫面
        GameKernel gameKernel = new GameKernel.Builder().input(  //創建遊戲核心
                new CommandSolver.BuildStream().mouseTrack().subscribe(sceneController).keyboardTrack()
                        .add(KeyEvent.VK_W,2)
                        .add(KeyEvent.VK_S,3)
                        .add(KeyEvent.VK_R,0)
                        .next().keyCleanMode().subscribe(sceneController)
        ).paint(sceneController).update(sceneController).gen();

        jframe.setSize(1000,1000);
        jframe.setTitle("打飛機遊戲");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //設置關閉時結束程式
        jframe.add(gameKernel);
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image,new Point(10,10),"image");
        jframe.setCursor(cursor);
        jframe.setVisible(true);

        gameKernel.run();
    }
}
