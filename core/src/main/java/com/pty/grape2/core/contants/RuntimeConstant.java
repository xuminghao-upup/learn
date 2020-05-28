package com.pty.grape2.core.contants;

import com.pty.grape2.core.assets.RuntimeAsset;
import com.pty.grape2.core.assets.ServiceAsset;

import java.nio.file.Paths;

/**
 * 启动程序运行时相关常量
 *
 * @author lxl
 */
public class RuntimeConstant {

    /** 主页组件 */
    public static ServiceAsset SERVICE_ASSET;
    /** 其他组件 */
    public static RuntimeAsset RUNTIME_ASSET;

    /** 运行根路径 */
    public static String BASE_DIR = System.getProperty("user.dir");

    /**
     * config.properties
     * 启动程序配置
     */
    public static String CONFIG_PROPERTIES_PATH = Paths.get(BASE_DIR, "app", "config", "config.properties").toString();


    static {
        RUNTIME_ASSET = new RuntimeAsset();
    }
}
