package com.example.eurekaserver.listener;

import javassist.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class AppRunListener implements SpringApplicationRunListener {

    private static final String SSL_BYPASS_HOST_VERIFICATION = "ssl.bypassHostVerification";
    private static boolean isBypass = true;
    private SpringApplication application;

    public AppRunListener(SpringApplication application, String[] args) {
        this.application = application;
    }

    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        boolean isBypassFromConfig = environment.getProperty(SSL_BYPASS_HOST_VERIFICATION, Boolean.class, Boolean.FALSE);
        if (isBypass && isBypassFromConfig) {
            try {
                ClassPool classPool = ClassPool.getDefault();
                classPool.appendClassPath(new LoaderClassPath(application.getClassLoader()));
                CtClass clz = classPool.get("org.apache.http.conn.ssl.DefaultHostnameVerifier");
                CtMethod method = clz.getDeclaredMethod("verify", new CtClass[] {
                        classPool.get("java.lang.String"), classPool.get("javax.net.ssl.SSLSession")
                });
                method.setBody("{\n return true; \n }");
                clz.toClass();
                log.info("Successfully bypass SSL hostname verification");
                isBypass = false;

            } catch (NotFoundException | CannotCompileException e) {
                log.warn("Unable to bypass SSL hostname verification", e);
            }
        }

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
