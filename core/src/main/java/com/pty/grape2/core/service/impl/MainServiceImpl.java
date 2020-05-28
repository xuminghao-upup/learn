package com.pty.grape2.core.service.impl;

import com.pty.grape2.core.contants.RuntimeConstant;
import com.pty.grape2.core.enums.ServiceEnum;
import com.pty.grape2.core.service.MainService;
import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author lxl
 */
@Slf4j
@Service
public class MainServiceImpl implements MainService {

    @Override
    public void init() {
        // 添加监听
        addListeners();

    }

    private void addListeners() {
        // Mysql监听
        RuntimeConstant.SERVICE_ASSET.getMysqlProperty().getStatus().addListener((observable, oldValue, newValue) -> {
            if (!Objects.isNull(newValue)) {
                ServiceEnum se = ServiceEnum.getById(newValue.intValue());
                if (!Objects.isNull(se)) {
                    switch (se) {
                        case DISABLE:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setDisable(true);
                            RuntimeConstant.SERVICE_ASSET.getLabelMysql().setDisable(true);
                            break;
                        case AVAILABLE:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelMysql().setDisable(false);
                            break;
                        case NOT_INSTALLED:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setDisable(true);
                            RuntimeConstant.SERVICE_ASSET.getLabelMysql().setDisable(false);
                            break;
                        case INSTALLED:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelMysql().setDisable(false);
                            break;
                        case STOP:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelMysql().setDisable(false);
                            break;
                        case STARTING:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setProgress(-1);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelMysql().setDisable(false);
                            break;
                        case RUNNING:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setProgress(100);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerMysql().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelMysql().setDisable(false);
                            break;
                        default:
                    }
                }
            }
        });

        // NGINX监听
        RuntimeConstant.SERVICE_ASSET.getNginxProperty().getStatus().addListener((observable, oldValue, newValue) -> {
            if (!Objects.isNull(newValue)) {
                ServiceEnum se = ServiceEnum.getById(newValue.intValue());
                if (!Objects.isNull(se)) {
                    switch (se) {
                        case DISABLE:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setDisable(true);
                            RuntimeConstant.SERVICE_ASSET.getLabelNginx().setDisable(true);
                            break;
                        case AVAILABLE:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelNginx().setDisable(false);
                            break;
                        case NOT_INSTALLED:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setDisable(true);
                            RuntimeConstant.SERVICE_ASSET.getLabelNginx().setDisable(false);
                            break;
                        case INSTALLED:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelNginx().setDisable(false);
                            break;
                        case STOP:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelNginx().setDisable(false);
                            break;
                        case STARTING:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setProgress(-1);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelNginx().setDisable(false);
                            break;
                        case RUNNING:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setProgress(100);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerNginx().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelNginx().setDisable(false);
                            break;
                        default:
                    }
                }
            }
        });

        // 服务监听
        RuntimeConstant.SERVICE_ASSET.getServerProperty().getStatus().addListener((observable, oldValue, newValue) -> {
            if (!Objects.isNull(newValue)) {
                ServiceEnum se = ServiceEnum.getById(newValue.intValue());
                if (!Objects.isNull(se)) {
                    switch (se) {
                        case DISABLE:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setDisable(true);
                            RuntimeConstant.SERVICE_ASSET.getLabelServer().setDisable(true);
                            break;
                        case AVAILABLE:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelServer().setDisable(false);
                            break;
                        case NOT_INSTALLED:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setDisable(true);
                            RuntimeConstant.SERVICE_ASSET.getLabelServer().setDisable(false);
                            break;
                        case INSTALLED:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelServer().setDisable(false);
                            break;
                        case STOP:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setProgress(0);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelServer().setDisable(false);
                            break;
                        case STARTING:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setProgress(-1);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelServer().setDisable(false);
                            break;
                        case RUNNING:
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setProgress(100);
                            RuntimeConstant.SERVICE_ASSET.getSpinnerServer().setDisable(false);
                            RuntimeConstant.SERVICE_ASSET.getLabelServer().setDisable(false);
                            break;
                        default:
                    }
                }
            }
        });
    }

    @Override
    public void testCMD() {
        //String line = "java -version";
        String line = "E:\\install\\test\\pro\\mysql\\bin\\mysqladmin.exe --defaults-file=\"E:\\\\install\\\\test\\\\pro\\\\mysql\\\\my.ini\" -u root -p123456 shutdown";
        executeCMDAsync(line);
    }

    @Override
    public void startMysql() {
        String line = "E:\\install\\test\\pro\\mysql\\start.bat";
        executeCMDAsync(line);
    }

    @Override
    public void stopMysql() {
        //String line = "E:\\install\\test\\pro\\mysql\\stop.bat";
        //String line = "E:\\install\\test\\pro\\mysql\\bin\\mysqladmin.exe --defaults-file=\"E:\\\\install\\\\test\\\\pro\\\\mysql\\\\my.ini\" -u root -p123456 -h \"127.0.0.1\" shutdown";
        //executeCMDSync(line);
        List<Integer> integers = ServiceEnum.toList();
        //int value = RuntimeConstant.SERVICE_ASSET.getMysqlProperty().getStatus().intValue();
        Integer integer = integers.get(new Random().nextInt(7));
        System.out.println(integer+" "+ServiceEnum.getById(integer).toString());
        RuntimeConstant.SERVICE_ASSET.getMysqlProperty().getStatus().setValue(integer);

    }

    @Override
    public void checkMysql() {
        Platform.runLater(() -> {
            String line = "E:\\install\\test\\pro\\mysql\\status.bat";
            executeCMDSync(line);
        });
    }


    private void executeCMDAsync(String line, String... arg) {
        CommandLine cmdLine = CommandLine.parse(line);
        for (String ar : arg) {
            cmdLine.addArgument(ar);
        }
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        executor.setStreamHandler(new PumpStreamHandler(baos, baos));
        try {
            executor.execute(cmdLine, resultHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //try {
        //    resultHandler.waitFor();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //String result = "";
        //try {
        //    result = baos.toString("GBK").trim();
        //} catch (UnsupportedEncodingException e) {
        //    e.printStackTrace();
        //}
        //log.debug("=====>调用CMD命令:{}", line);
        //log.debug("=====>调用CMD返回:{}", result);
    }

    private void executeCMDSync(String line, String... arg) {
        CommandLine cmdLine = CommandLine.parse(line);
        for (String ar : arg) {
            cmdLine.addArgument(ar);
        }
        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        executor.setStreamHandler(new PumpStreamHandler(baos, baos));
        try {
            executor.execute(cmdLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        try {
            result = baos.toString("GBK").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.debug("=====>调用CMD命令:{}", line);
        log.debug("=====>调用CMD返回:{}", result);
    }
}
