package com.pty.grape2.core.controller.service;

import com.jfoenix.controls.*;
import com.pty.grape2.core.contants.PropertiesConstant;
import com.pty.grape2.core.contants.RuntimeConstant;
import com.pty.grape2.core.controller.BaseController;
import com.pty.grape2.core.skin.JFXTextAreaSkinCustom;
import com.pty.grape2.core.util.RuntimeUtil;
import com.pty.grape2.core.util.TitleBarUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author lxl
 */
@Slf4j
@FXMLController
public class ServiceManagementController implements Initializable, BaseController {

    @FXML
    private AnchorPane titlePane;
    @FXML
    private AnchorPane serviceManagementPane;
    @FXML
    private JFXHamburger burger;
    @FXML
    private JFXTextField textFieldServiceSuffix;
    @FXML
    private JFXToggleButton tglBtnMysql;
    @FXML
    private JFXToggleButton tglBtnNginx;
    @FXML
    private JFXToggleButton tglBtnServer;
    @FXML
    private JFXTextArea textAreaConsole;
    @FXML
    private JFXButton btnInstallService;
    @FXML
    private JFXButton btnUnInstallService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAsset();
    }

    @Override
    public void initProperty() {
        String serviceSuffixStr = RuntimeUtil.CONFIG_PROPERTIES_UTIL.getProperty(PropertiesConstant.SERVICE_SUFFIX);
        log.info("----->配置文件中服务名称后缀:{}", serviceSuffixStr);
        RuntimeConstant.RUNTIME_ASSET.getRuntimeProperty().getServiceSuffix().setValue(StringUtils.isEmpty(serviceSuffixStr) ? "" : serviceSuffixStr);
    }

    @Override
    public void initAsset() {
        // 返回按钮
        // 初始化标题栏
        TitleBarUtil.initTitleBar(titlePane);
        TitleBarUtil.setBurgerBack(burger);
        RuntimeConstant.RUNTIME_ASSET.setServiceManagementBurger(burger);
        RuntimeConstant.RUNTIME_ASSET.setTextFieldServiceSuffix(textFieldServiceSuffix);
        textAreaConsole.setSkin(new JFXTextAreaSkinCustom(textAreaConsole));
        // 初始化启动项
        // 初始化按钮
    }
}
