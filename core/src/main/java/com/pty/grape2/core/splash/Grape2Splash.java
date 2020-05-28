package com.pty.grape2.core.splash;

import de.felixroske.jfxsupport.SplashScreen;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * @author lxl
 */
public class Grape2Splash extends SplashScreen {

    @Override
    public boolean visible() {
        return false;
    }

    @Override
    public String getImagePath() {
        return "/image/load.jpeg";
    }

    @Override
    public Parent getParent() {
        ImageView imageView = new ImageView(this.getClass().getResource(this.getImagePath()).toExternalForm());
        ProgressBar splashProgressBar = new ProgressBar();
        splashProgressBar.setPrefWidth(imageView.getImage().getWidth());
        VBox vbox = new VBox();
        vbox.getChildren().addAll(imageView, splashProgressBar);
        return vbox;
    }
}
