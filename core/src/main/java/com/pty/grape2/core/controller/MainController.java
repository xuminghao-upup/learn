package com.pty.grape2.core.controller;

import com.jfoenix.controls.*;
import com.pty.grape2.core.ApplicationLaunch;
import com.pty.grape2.core.assets.ServiceAsset;
import com.pty.grape2.core.contants.RuntimeConstant;
import com.pty.grape2.core.service.MainService;
import com.pty.grape2.core.service.WinSerService;
import com.pty.grape2.core.skin.JFXSpinnerSkinCustom;
import com.pty.grape2.core.skin.JFXTextAreaSkinCustom;
import com.pty.grape2.core.util.MenuListUtil;
import com.pty.grape2.core.util.ThreadPoolManager;
import com.pty.grape2.core.util.TitleBarUtil;
import com.pty.grape2.core.view.acb.AcbBackupView;
import com.pty.grape2.core.view.acb.AcbRecoveryView;
import com.pty.grape2.core.view.config.ConfigJVMView;
import com.pty.grape2.core.view.config.ConfigPortView;
import com.pty.grape2.core.view.config.ConfigServiceView;
import com.pty.grape2.core.view.log.MysqlLogView;
import com.pty.grape2.core.view.log.ServerLogView;
import com.pty.grape2.core.view.service.ServiceManagementView;
import com.pty.grape2.core.view.update.UpdateBackupView;
import com.pty.grape2.core.view.update.UpdateOfflineView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author lxl
 */
@Slf4j
@FXMLController
public class MainController implements Initializable {

    /**
     * 输出窗口
     */
    @FXML
    private JFXTextArea textAreaConsole;

    @FXML
    private VBox boxTitle;
    @FXML
    private Text textTitle;
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnMin;
    @FXML
    private AnchorPane titlePane;
    @FXML
    private Label dateLabel;

    /**
     * 服务菜单
     */
    @FXML
    private Label labelMenuService;
    private Label labelServiceManagement;
    @FXML
    private Label labelMenuConfig;
    private Label labelConfigService;
    private Label labelConfigPort;
    private Label labelConfigJVM;
    @FXML
    private Label labelMenuLog;
    private Label labelServerLog;
    private Label labelMysqlLog;
    @FXML
    private Label labelBackupRecovery;
    private Label labelBackup;
    private Label labelRecovery;
    @FXML
    private Label labelUpdate;
    private Label labelUpdateOffline;
    private Label labelUpdateBackUpManagement;

    /**
     * 服务状态
     */
    @FXML
    private JFXSpinner spinnerNginx;
    @FXML
    private Label labelNginx;
    @FXML
    private JFXSpinner spinnerMysql;
    @FXML
    private Label labelMysql;
    @FXML
    private JFXSpinner spinnerServer;
    @FXML
    private Label labelServer;

    /**
     * 操作按钮
     */
    @FXML
    private JFXButton btnStart;
    @FXML
    private JFXButton btnStop;
    @FXML
    private JFXButton btnLogin;

    @Autowired
    private MainService mainService;

    @Autowired
    private WinSerService winSerService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化组件
        initAsset();
        // 初始化属性
        initProperty();
        // 初始化菜单
        labelMenuService.setOnMouseClicked((e) -> menuServicePop().show(labelMenuService, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, -19, 33));
        labelMenuConfig.setOnMouseClicked((e) -> menuConfigPop().show(labelMenuConfig, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, -19, 33));
        labelMenuLog.setOnMouseClicked((e) -> menuLogPop().show(labelMenuLog, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, -19, 33));
        labelBackupRecovery.setOnMouseClicked((e) -> menuAcbBackupPop().show(labelBackupRecovery, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, 33));
        labelUpdate.setOnMouseClicked((e) -> menuUpdatePop().show(labelUpdate, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, -19, 33));
        Task<Void> task1 = new Task<Void>() {
            @Override
            public Void call() {
                mainService.init();
                return null;
            }
        };
        ThreadPoolManager.newInstance().addExecuteTask(task1);

        btnStart.setOnMouseClicked(event -> {
            mainService.stopMysql();
        });
    }

    public void relaunch(Event event) throws IOException {
        ApplicationLaunch bean = ApplicationLaunch.context.getBean(ApplicationLaunch.class);
        bean.relaunch();
    }

    /**
     * 菜单-服务
     *
     * @return
     */
    private JFXPopup menuServicePop() {
        JFXListView<Label> jfxListView = getInitListView();
        labelServiceManagement = new Label("服务管理");

        jfxListView.getItems().add(labelServiceManagement);
        JFXPopup serviceMenuPopup = new JFXPopup(jfxListView);
        labelServiceManagement.setOnMouseClicked(event -> {
            show(serviceMenuPopup,RuntimeConstant.RUNTIME_ASSET.getServiceManagementBurger(), ServiceManagementView.class);
        });
        return serviceMenuPopup;
    }

    /**
     * 菜单-配置
     *
     * @return
     */
    private JFXPopup menuConfigPop() {
        JFXListView<Label> jfxListView = getInitListView();
        labelConfigService = new Label("配置服务");
        labelConfigPort = new Label("配置端口");
        labelConfigJVM = new Label("配置内存");

        jfxListView.getItems().add(labelConfigService);
        jfxListView.getItems().add(labelConfigPort);
        jfxListView.getItems().add(labelConfigJVM);

        JFXPopup configMenuPopup = new JFXPopup(jfxListView);
        // 配置服务
        labelConfigService.setOnMouseClicked(event -> {
            show(configMenuPopup,RuntimeConstant.RUNTIME_ASSET.getConfigServerBurger(), ConfigServiceView.class);
        });

        // 配置端口
        labelConfigPort.setOnMouseClicked(event -> {
            show(configMenuPopup,RuntimeConstant.RUNTIME_ASSET.getConfigPortBurger(), ConfigPortView.class);
        });

        // 配置内存
        labelConfigJVM.setOnMouseClicked(event -> {
            show(configMenuPopup, RuntimeConstant.RUNTIME_ASSET.getConfigJvmBurger(), ConfigJVMView.class);
        });
        return configMenuPopup;
    }

    /**
     * 菜单-日志
     *
     * @return
     */
    private JFXPopup menuLogPop() {
        JFXListView<Label> jfxListView = getInitListView();
        labelServerLog = new Label("应用日志");
        labelMysqlLog = new Label("Mysql日志");

        jfxListView.setStyle("-fx-pref-width: 83;");

        jfxListView.getItems().add(labelServerLog);
        jfxListView.getItems().add(labelMysqlLog);

        JFXPopup logMenuPopup = new JFXPopup(jfxListView);
        // 应用日志
        labelServerLog.setOnMouseClicked(event -> {
            show(logMenuPopup, RuntimeConstant.RUNTIME_ASSET.getServerLogBurger(), ServerLogView.class);
        });
        // Mysql日志
        labelMysqlLog.setOnMouseClicked(event -> {
            show(logMenuPopup, RuntimeConstant.RUNTIME_ASSET.getMysqlLogBurger(), MysqlLogView.class);
        });
        return logMenuPopup;
    }

    /**
     * 菜单-备份/恢复
     *
     * @return
     */
    private JFXPopup menuAcbBackupPop() {
        JFXListView<Label> jfxListView = getInitListView();
        labelBackup = new Label("账套备份");
        labelRecovery = new Label("账套恢复");

        jfxListView.getItems().add(labelBackup);
        jfxListView.getItems().add(labelRecovery);

        JFXPopup backupRecoveryPopup = new JFXPopup(jfxListView);
        // 账套备份
        labelBackup.setOnMouseClicked(event -> {
            show(backupRecoveryPopup, RuntimeConstant.RUNTIME_ASSET.getAcbBackupBurger(), AcbBackupView.class);
        });
        // 账套恢复
        labelRecovery.setOnMouseClicked(event -> {
            show(backupRecoveryPopup, RuntimeConstant.RUNTIME_ASSET.getAcbRecoveryBurger(), AcbRecoveryView.class);
        });
        return backupRecoveryPopup;
    }

    /**
     * 菜单-升级
     *
     * @return
     */
    private JFXPopup menuUpdatePop() {
        JFXListView<Label> jfxListView = getInitListView();
        labelUpdateOffline = new Label("离线升级");
        labelUpdateBackUpManagement = new Label("备份管理");
        // TODO by lxl ----- 2020-03-26 ----- 在线升级
        jfxListView.getItems().add(labelUpdateOffline);
        jfxListView.getItems().add(labelUpdateBackUpManagement);

        JFXPopup updatePopup = new JFXPopup(jfxListView);
        labelUpdateOffline.setOnMouseClicked(event -> {
            show(updatePopup, RuntimeConstant.RUNTIME_ASSET.getUpdateOfflineBurger(), UpdateOfflineView.class);
        });
        labelUpdateBackUpManagement.setOnMouseClicked(event -> {
            show(updatePopup, RuntimeConstant.RUNTIME_ASSET.getUpdateBackupBurger(), UpdateBackupView.class);
        });

        return updatePopup;
    }

    private void show(JFXPopup menuPopup, JFXHamburger burger, Class view) {
        // 隐藏下拉菜单
        menuPopup.hide();
        // 新页面返回箭头动画
        TitleBarUtil.burgerAnimation(burger);
        // 显示新view
        ApplicationLaunch.showInitialView(view);
    }

    private void initProperty() {
        // setting

    }

    private void initAsset() {
        // 初始化标题栏
        TitleBarUtil.initTitleBar(titlePane, btnClose, btnMin, textTitle);
        spinnerNginx.setProgress(0);
        spinnerMysql.setProgress(0);
        spinnerServer.setProgress(0);
        ServiceAsset serviceAsset = new ServiceAsset();
        serviceAsset.setSpinnerNginx(spinnerNginx);
        serviceAsset.setLabelNginx(labelNginx);
        serviceAsset.setSpinnerMysql(spinnerMysql);
        serviceAsset.setLabelMysql(labelMysql);
        serviceAsset.setSpinnerServer(spinnerServer);
        serviceAsset.setLabelServer(labelServer);
        serviceAsset.setBtnStart(btnStart);
        serviceAsset.setBtnStop(btnStop);
        serviceAsset.setBtnLogin(btnLogin);
        RuntimeConstant.SERVICE_ASSET = serviceAsset;

        spinnerNginx.setSkin(new JFXSpinnerSkinCustom(spinnerNginx));
        spinnerMysql.setSkin(new JFXSpinnerSkinCustom(spinnerMysql));
        spinnerServer.setSkin(new JFXSpinnerSkinCustom(spinnerServer));
        textAreaConsole.setSkin(new JFXTextAreaSkinCustom(textAreaConsole));
    }

    private JFXListView<Label> getInitListView() {
        JFXListView<Label> jfxListView = new JFXListView<>();
        jfxListView.getStylesheets().add(MenuListUtil.class.getResource("/css/jfoenix-customer.css").toExternalForm());
        return jfxListView;
    }

}
