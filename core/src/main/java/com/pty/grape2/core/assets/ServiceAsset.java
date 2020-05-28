package com.pty.grape2.core.assets;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.pty.grape2.core.property.MysqlProperty;
import com.pty.grape2.core.property.NginxProperty;
import com.pty.grape2.core.property.ServerProperty;
import javafx.scene.control.Label;
import lombok.Data;

/**
 * 主页相关组件
 *
 * @author lxl
 */
@Data
public class ServiceAsset {
    private JFXSpinner spinnerNginx;
    private Label labelNginx;
    private JFXSpinner spinnerMysql;
    private Label labelMysql;
    private JFXSpinner spinnerServer;
    private Label labelServer;
    private JFXButton btnStart;
    private JFXButton btnStop;
    private JFXButton btnLogin;

    private MysqlProperty mysqlProperty = new MysqlProperty();
    private NginxProperty nginxProperty = new NginxProperty();
    private ServerProperty serverProperty = new ServerProperty();
}
