package com.pty.grape2.core.property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Data;

/**
 * @author lxl
 */
@Data
public class BaseProperty {
    /**
     * 服务状态
     */
    protected IntegerProperty status = new SimpleIntegerProperty(0);

}
