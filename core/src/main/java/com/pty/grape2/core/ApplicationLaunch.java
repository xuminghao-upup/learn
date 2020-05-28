package com.pty.grape2.core;

import com.pty.grape2.core.splash.Grape2Splash;
import com.pty.grape2.core.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * springboot-javafx启动入口
 *
 * @author lxl
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.pty.grape2")
public class ApplicationLaunch extends AbstractJavaFxApplicationSupport {

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch(ApplicationLaunch.class, MainView.class, new Grape2Splash(), args);
    }

    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        log.error("=====>beforeInitialView");
        ApplicationLaunch.context = ctx;
        stage.setOnCloseRequest(event -> System.exit(2));
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void beforeShowingSplash(Stage splashStage) {
        log.error("=====>beforeShowingSplash");
    }

    public void relaunch() {
        Platform.runLater(() -> {
            getStage().close();
            try {
                this.stop();
                this.init();
                this.start(new Stage());
                Platform.runLater(()->{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
                    alert.show();
                    alert.hide();
                });

                //ApplicationLaunch.test(MainView.class);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
    }
}
