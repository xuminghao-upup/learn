package com.pty.grape2.core.service.impl;

import com.pty.grape2.core.service.WinSerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lxl
 */
@Slf4j
@Service
public class WinSerServiceImpl implements WinSerService {

    @Override
    public void install() {
        log.info("----->服务安装");
    }
}
