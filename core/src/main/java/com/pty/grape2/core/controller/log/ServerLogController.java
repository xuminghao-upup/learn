package com.pty.grape2.core.controller.log;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.pty.grape2.core.contants.RuntimeConstant;
import com.pty.grape2.core.skin.JFXTextAreaSkinCustom;
import com.pty.grape2.core.util.TitleBarUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author lxl
 */
@Slf4j
@FXMLController
public class ServerLogController implements Initializable {

    @FXML
    private AnchorPane titlePane;
    @FXML
    private JFXHamburger burger;
    @FXML
    private JFXTextArea textAreaLog;
    @FXML
    private JFXButton btnFolder;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO by lxl ----- 2020-03-25 ----- 日志过大的问题
        // 初始化组件
        initAsset();
        // 初始化标题栏
        TitleBarUtil.initTitleBar(titlePane);
        // 返回按钮
        TitleBarUtil.setBurgerBack(burger);
        // TODO by lxl ----- 2020-03-25 ----- 文件夹路径常量
        TitleBarUtil.setBtnFolder(btnFolder, null);
        // 初始化属性
        initProperty();
        // 其他初始化
        // TODO by lxl ----- 2020-03-25 -----
        //configService.init();


    }

    private void initProperty() {
        // setting
        textAreaLog.setSkin(new JFXTextAreaSkinCustom(textAreaLog));
    }

    private void initAsset() {
        RuntimeConstant.RUNTIME_ASSET.setServerLogBurger(burger);
    }


}
