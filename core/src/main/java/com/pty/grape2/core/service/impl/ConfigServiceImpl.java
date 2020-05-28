package com.pty.grape2.core.service.impl;

import com.pty.grape2.core.contants.RuntimeConstant;
import com.pty.grape2.core.service.ConfigService;
import org.springframework.stereotype.Service;

/**
 * @author lxl
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Override
    public void init() {
        // 添加监听
        addListeners();
    }

    private void addListeners() {
        // 服务名称后缀监听
        RuntimeConstant.RUNTIME_ASSET.getRuntimeProperty().getServiceSuffix().addListener((observable, oldValue, newValue) -> {
            RuntimeConstant.RUNTIME_ASSET.getTextFieldServiceSuffix().setText(newValue);
        });
    }
}
