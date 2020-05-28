package com.pty.grape2.core.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

/**
 * 启动程序运行时属性
 *
 * @author lxl
 */
@Data
public class RuntimeProperty {

    /** config */
    /** 服务名后缀 */
    private StringProperty serviceSuffix = new SimpleStringProperty("");
}
