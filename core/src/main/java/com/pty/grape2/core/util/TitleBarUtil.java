package com.pty.grape2.core.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.svg.SVGGlyph;
import com.pty.grape2.core.ApplicationLaunch;
import com.pty.grape2.core.handler.DragWindowHandler;
import com.pty.grape2.core.view.MainView;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author lxl
 */
@Slf4j
public class TitleBarUtil {

    public static void setBtnMin(JFXButton btnMin) {
        SVGGlyph minus = new SVGGlyph(0,
                "MINUS",
                "M804.571 420.571v109.714q0 22.857-16 38.857t-38.857 16h-694.857q-22.857 0-38.857-16t-16-38.857v-109.714q0-22.857 16-38.857t38.857-16h694.857q22.857 0 38.857 16t16 38.857z",
                Color.WHITE);
        minus.setSize(12, 2);
        minus.setTranslateY(4);
        btnMin.getStyleClass().add("jfx-decorator-button");
        btnMin.setCursor(Cursor.HAND);
        btnMin.setOnAction((action) -> ApplicationLaunch.getStage().setIconified(true));
        btnMin.setGraphic(minus);
        btnMin.setRipplerFill(Color.WHITE);
    }

    public static void setBtnClose(JFXButton btnClose) {
        SVGGlyph close = new SVGGlyph(0,
                "CLOSE",
                "M810 274l-238 238 238 238-60 60-238-238-238 238-60-60 238-238-238-238 60-60 238 238 238-238z",
                Color.WHITE);
        close.setSize(12, 12);
        btnClose.getStyleClass().add("jfx-decorator-button");
        btnClose.setCursor(Cursor.HAND);
        btnClose.setOnAction((action) -> Platform.runLater(() -> {
            ApplicationLaunch.getStage().close();
            try {
                if (!Objects.isNull(ApplicationLaunch.context)) {
                    ApplicationLaunch.context.close();
                }
                System.exit(2);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }));
        btnClose.setGraphic(close);
        btnClose.setRipplerFill(Color.WHITE);
    }

    public static void setBtnFolder(JFXButton btnFolder, String filePath) {
        SVGGlyph close = new SVGGlyph(0,
                "FOLDER",
                //"M832 0l192 512h-832l-192-512zM128 576l-128-576v832h288l128-128h416v-128z",
                "M1017.714 418.857q0 20-30.286 20h-621.714q-22.857 0-48.857-12.286t-40.857-30l-168-207.429q-10.286-13.714-10.286-22.857 0-20 30.286-20h621.714q22.857 0 49.143 12.571t40.571 30.286l168 207.429q10.286 12.571 10.286 22.286zM365.714 512h438.857v91.429q0 22.857-16 38.857t-38.857 16h-329.143q-22.857 0-38.857 16t-16 38.857v36.571q0 22.857-16 38.857t-38.857 16h-182.857q-22.857 0-38.857-16t-16-38.857v-487.429l146.286 180q25.143 30.286 66.286 50t80 19.714zM1090.857 418.857q0-35.429-26.286-68.571l-168.571-207.429q-24.571-30.286-66.286-50t-80-19.714h-621.714q-52.571 0-90.286 37.714t-37.714 90.286v548.571q0 52.571 37.714 90.286t90.286 37.714h182.857q52.571 0 90.286-37.714t37.714-90.286v-18.286h310.857q52.571 0 90.286-37.714t37.714-90.286v-91.429h109.714q30.857 0 56.571-14t38.286-40.286q8.571-18.286 8.571-38.857z",
                Color.WHITE);
        close.setSize(18, 16);
        btnFolder.getStyleClass().addAll("jfx-decorator-button", "button-folder");
        btnFolder.setCursor(Cursor.HAND);
        btnFolder.setOnAction((action) -> Platform.runLater(() -> {
            // TODO by lxl ----- 2020-03-25 ----- 打开文件夹
            System.out.println("----->打开文件夹");
        }));
        btnFolder.setGraphic(close);
        btnFolder.setRipplerFill(Color.WHITE);
    }

    /**
     * 初始化标题栏
     *
     * @param titlePane
     * @param btnClose
     * @param btnMin
     * @param textTitle
     */
    public static void initTitleBar(AnchorPane titlePane, JFXButton btnClose, JFXButton btnMin, Text textTitle) {
        if (!Objects.isNull(btnClose)) {
            // 关闭按钮
            TitleBarUtil.setBtnClose(btnClose);
        }
        if (!Objects.isNull(btnMin)) {
            // 最小化按钮
            TitleBarUtil.setBtnMin(btnMin);
        }
        if (!Objects.isNull(textTitle)) {
            // 标题文字
            textTitle.getStyleClass().addAll("jfx-decorator-text", "title-cus", "jfx-decorator-title", "jfx-decorator-title-container");
            textTitle.setFill(Color.WHITE);
            textTitle.textProperty().bind(ApplicationLaunch.getStage().titleProperty());
        }
        if (!Objects.isNull(titlePane)) {
            DragWindowHandler handler = new DragWindowHandler(ApplicationLaunch.getStage());
            titlePane.setOnMousePressed(handler);
            titlePane.setOnMouseDragged(handler);
        }
    }

    public static void initTitleBar(AnchorPane titlePane) {
        initTitleBar(titlePane, null, null, null);
    }

    public static void setBurgerBack(JFXHamburger burger) {
        burgerAnimation(burger);
        burger.setOnMouseClicked(event -> {
            burger.getAnimation().setRate(burger.getAnimation().getRate() * -1);
            burger.getAnimation().play();
            ApplicationLaunch.showInitialView(MainView.class);
        });
    }

    public static void burgerAnimation(JFXHamburger burger) {
        if (!Objects.isNull(burger)) {
            burger.getAnimation().setRate(1);
            burger.getAnimation().play();
        }
    }

}
