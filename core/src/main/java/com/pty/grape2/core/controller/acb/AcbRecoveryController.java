package com.pty.grape2.core.controller.acb;

import com.jfoenix.controls.JFXHamburger;
import com.pty.grape2.core.contants.RuntimeConstant;
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
public class AcbRecoveryController implements Initializable {

    @FXML
    private AnchorPane titlePane;
    @FXML
    private JFXHamburger burger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化组件
        initAsset();
        // 初始化标题栏
        TitleBarUtil.initTitleBar(titlePane);
        // 返回按钮
        TitleBarUtil.setBurgerBack(burger);
        // 初始化属性
        initProperty();
        // 其他初始化
        // TODO by lxl ----- 2020-03-25 -----
        //configService.init();


    }

    private void initProperty() {
        // setting
    }

    private void initAsset() {
        RuntimeConstant.RUNTIME_ASSET.setAcbRecoveryBurger(burger);
    }


}
