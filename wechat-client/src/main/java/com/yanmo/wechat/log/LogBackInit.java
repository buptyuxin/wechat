package com.yanmo.wechat.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

/**
 * 这个类主要是在二方库中加入一个二方库的专属日志，用于采集应用的数据
 *
 * Created by yanmo.yx on 2015/9/8.
 */
public class LogBackInit {

    // 这里直接就加上了配置文件里的logger，太爽了，而且没有覆盖原有的logger，因为这些logger都是在cache里缓存的
    static {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure(LogBackInit.class.getClassLoader().getResource("/test/logbacktest.xml"));
        } catch (JoranException e) {

        }
        // 这里就是在控制台输出一点错误信息
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
    }

    public  static org.slf4j.Logger log = LoggerFactory.getLogger("test");

    private static Logger createLogger(String str, String file) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

//        ContextInitializer ci = new ContextInitializer(lc);
//        lc.putProperty("log_path", "logback.log");

//        JoranConfigurator configurator = new JoranConfigurator();
//        configurator.setContext(lc);
//        lc.reset();
//        try {
//            configurator.doConfigure("logbacktest.xml");
//        } catch (JoranException e) {
//
//        }

        PatternLayoutEncoder ple = new PatternLayoutEncoder();

        ple.setPattern("%date %level [%thread] %logger{10} [%file:%line] %msg%n");
        ple.setContext(lc);
        ple.start();

        FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
        fileAppender.setFile(file);
        fileAppender.setEncoder(ple);
        fileAppender.setContext(lc);
        fileAppender.start();

        Logger log = (Logger) LoggerFactory.getLogger(str);
        log.addAppender(fileAppender);
        log.setLevel(Level.DEBUG);
        log.setAdditive(false);

        return log;
    }
}
