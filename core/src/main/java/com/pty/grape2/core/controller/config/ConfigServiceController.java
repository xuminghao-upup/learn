package com.pty.grape2.core.controller.config;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.pty.grape2.core.contants.PropertiesConstant;
import com.pty.grape2.core.contants.RuntimeConstant;
import com.pty.grape2.core.service.ConfigService;
import com.pty.grape2.core.util.RuntimeUtil;
import com.pty.grape2.core.util.TitleBarUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


/**
 * @author lxl
 */
@Slf4j
@FXMLController
public class ConfigServiceController implements Initializable {

    @FXML
    private AnchorPane titlePane;
    @FXML
    private JFXToggleButton tglBtnMysql;
    @FXML
    private JFXToggleButton tglBtnNginx;
    @FXML
    private JFXTextField textFieldServiceSuffix;
    @Autowired
    private ConfigService configService;
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
        configService.init();


        tglBtnMysql.setOnMouseClicked(event -> {
            log.info("----->[{}]", textFieldServiceSuffix.getText());
        });

        tglBtnNginx.setOnMouseClicked(event -> {
            RuntimeConstant.RUNTIME_ASSET.getRuntimeProperty().getServiceSuffix().setValue("" + new Random().nextInt(10));
        });
    }

    private void initProperty() {
        String serviceSuffixStr = RuntimeUtil.CONFIG_PROPERTIES_UTIL.getProperty(PropertiesConstant.SERVICE_SUFFIX);
        RuntimeConstant.RUNTIME_ASSET.getRuntimeProperty().getServiceSuffix().setValue(StringUtils.isEmpty(serviceSuffixStr) ? "" : serviceSuffixStr);
    }

    private void initAsset() {
        RuntimeConstant.RUNTIME_ASSET.setConfigServerBurger(burger);
        RuntimeConstant.RUNTIME_ASSET.setTextFieldServiceSuffix(textFieldServiceSuffix);
    }


}
