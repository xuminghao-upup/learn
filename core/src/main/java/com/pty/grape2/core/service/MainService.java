package com.pty.grape2.core.service;

/**
 * @author lxl
 */
public interface MainService {

    void testCMD();

    void startMysql();

    void stopMysql();

    void checkMysql();

    /**
     * 初始化组件及相关服务监听
     */
    void init();
}
