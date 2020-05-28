package com.pty.grape2.core.enums;


import java.util.ArrayList;
import java.util.List;

public enum ServiceEnum {

    /**
     * 不可用
     */
    DISABLE(0),

    /**
     * 可用
     */
    AVAILABLE(1),
    /**
     * 未安装
     */
    NOT_INSTALLED(10),

    /**
     * 已安装
     */
    INSTALLED(11),

    /**
     * 已停止
     */
    STOP(12),

    /**
     * 启动中
     */
    STARTING(13),

    /**
     * 运行中
     */
    RUNNING(14);


    private int code;

    ServiceEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static List<Integer> toList(){
        List<Integer> list = new ArrayList<>();
        ServiceEnum[] serviceEnums = ServiceEnum.values();
        for(ServiceEnum e : serviceEnums) {
            list.add(e.getCode());
        }
        return list;
    }

    public static ServiceEnum getById(Integer code) {
        for (ServiceEnum serviceEnum : values()) {
            if (serviceEnum.getCode() == code) {
                //获取指定的枚举
                return serviceEnum;
            }
        }
        return null;

    }

}
