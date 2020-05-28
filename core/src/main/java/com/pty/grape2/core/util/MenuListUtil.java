package com.pty.grape2.core.util;

/**
 * 菜单生成类
 * 编程式生成菜单
 *
 * @author lxl
 */
public class MenuListUtil {

    private static class InstanceHolder {
        public static final MenuListUtil INSTANCE = new MenuListUtil();
    }

    private MenuListUtil() {
    }

    public static MenuListUtil getInstance() {
        return InstanceHolder.INSTANCE;
    }

}
