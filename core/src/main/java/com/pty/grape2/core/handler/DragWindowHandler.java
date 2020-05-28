package com.pty.grape2.core.handler;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author lxl
 */
public class DragWindowHandler implements EventHandler<MouseEvent> {
    private Stage primaryStage;
    private double oldStageX;
    private double oldStageY;
    private double oldScreenX;
    private double oldScreenY;

    public DragWindowHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(MouseEvent e) {
        //鼠标按下的事件
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
            this.oldStageX = this.primaryStage.getX();
            this.oldStageY = this.primaryStage.getY();
            this.oldScreenX = e.getScreenX();
            this.oldScreenY = e.getScreenY();
        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            //鼠标拖动的事件
            this.primaryStage.setX(e.getScreenX() - this.oldScreenX + this.oldStageX);
            this.primaryStage.setY(e.getScreenY() - this.oldScreenY + this.oldStageY);
        }
    }

}
