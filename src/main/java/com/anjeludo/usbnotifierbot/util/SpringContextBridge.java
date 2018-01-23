package com.anjeludo.usbnotifierbot.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.anjeludo.usbnotifierbot.notifier.UsbProducer;
 
/**
* Register this SpringContextBridge as a Spring Component.
*/
@Component 
public class SpringContextBridge implements ApplicationContextAware {
 
    private static ApplicationContext applicationContext;
 
    @Autowired
    private UsbProducer usbProducer;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) 
            throws BeansException {
        this.applicationContext = applicationContext;
    }
 
    public static SpringContextBridge services() {
        return applicationContext.getBean(SpringContextBridge.class);
    }
 
    public UsbProducer getUsbProducer() {
        return usbProducer; 
    }
}
