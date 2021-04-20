package menumodule.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import menumodule.menu.*;
import menumodule.menu.Style.StyleRect;
import menumodule.menu.impl.MouseTriggerImpl;
import scene.Scene;
import utils.CommandSolver;
import utils.CommandSolver.MouseListener;

public class MenuScene extends Scene {

    private PopupWindowScene testPop;
    private Label a;
    private Button b;
    private EditText ee;

    @Override
    public void sceneBegin() {
        testPop = new PopupWindowScene(50, 50, 650, 450);
        testPop.setCancelable();
        a = new Label(430, 122);
        b = new Button(430, 410, Theme.get(0));
        b.setClickedActionPerformed((int x, int y) -> System.out.println("ClickedAction"));

        //使用格式：
        //第一行： new Label and set all the Style(normal & hover & focused )
        //第一行：set MouscCommandedListener and KeyListerner
        //一定要分開設定
        Style et = new StyleRect(200, 50, true, new BackgroundType.BackgroundColor(Color.YELLOW))
                .setHaveBorder(true)
                .setTextColor(Color.BLACK)
                .setTextFont(new Font("", Font.BOLD, 20))
                .setBorderColor(Color.BLACK)
                .setBorderThickness(5);

        Style eHover = new StyleRect(200, 50, true, new BackgroundType.BackgroundColor(Color.WHITE))
                .setHaveBorder(true)
                .setBorderColor(Color.BLACK)
                .setBorderThickness(5)
                .setTextColor(Color.BLACK)
                .setTextFont(new Font("", Font.BOLD, 20))
                .setText("HOVER");

        Style eNormal = new StyleRect(200, 50, true, new BackgroundType.BackgroundColor(new Color(128, 128, 128)))
                .setHaveBorder(true)
                .setTextColor(Color.LIGHT_GRAY)
                .setText("請點擊")
                .setTextFont(new Font("", Font.BOLD, 20))
                .setBorderColor(Color.WHITE)
                .setBorderThickness(5);

        this.ee = new EditText(430, 290, "請在此輸入");
        ee.setStyleNormal(eNormal);
        ee.setStyleHover(eHover);
        ee.setStyleFocus(et);
        ee.setEditLimit(10);   //設定文字輸入長度限制
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        a.paint(g);
        b.paint(g);
        ee.paint(g);
        if (testPop.isShow()) {
            testPop.paint(g);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public MouseListener mouseListener() {
        return (MouseEvent e, CommandSolver.MouseState state, long trigTime) -> {
            MouseTriggerImpl.mouseTrig(a, e, state);
            MouseTriggerImpl.mouseTrig(b, e, state);
            MouseTriggerImpl.mouseTrig(ee, e, state);
            if (testPop.isShow()) {
                testPop.mouseListener().mouseTrig(e, state, trigTime);
            }
        };
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
            }

            @Override
            public void keyTyped(char c, long trigTime) {
                ee.keyTyped(c);
                if (c == KeyEvent.VK_SHIFT && !ee.getIsFocus()) {
                    if (testPop.isShow()) {
                        testPop.hide();
                        testPop.sceneEnd();
                    } else {
                        testPop.sceneBegin();
                        testPop.show();
                    }
                }
            }
        };
    }
}
