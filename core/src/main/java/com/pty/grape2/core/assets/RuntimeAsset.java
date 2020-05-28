package com.pty.grape2.core.assets;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.pty.grape2.core.property.RuntimeProperty;
import lombok.Data;

/**
 * @author lxl
 */
@Data
public class RuntimeAsset {
    /** burger */
    private JFXHamburger serviceManagementBurger;
    private JFXHamburger configServerBurger;
    private JFXHamburger configPortBurger;
    private JFXHamburger configJvmBurger;
    private JFXHamburger serverLogBurger;
    private JFXHamburger mysqlLogBurger;
    private JFXHamburger acbBackupBurger;
    private JFXHamburger acbRecoveryBurger;
    private JFXHamburger updateOfflineBurger;
    private JFXHamburger updateBackupBurger;

    private JFXTextField textFieldServiceSuffix;

    private RuntimeProperty runtimeProperty = new RuntimeProperty();
}
