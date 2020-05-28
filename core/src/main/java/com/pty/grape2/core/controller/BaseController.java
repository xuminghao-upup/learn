package com.pty.grape2.core.controller;

/**
 * @author lxl
 */
public interface BaseController {
    /**
     * 初始化组件
     */
    default void initAsset() {
    }

    /**
     * 初始化属性
     */
    default void initProperty() {
    }
}
