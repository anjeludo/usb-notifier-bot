package com.anjeludo.usbnotifierbot.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anjeludo.usbnotifierbot.notifier.domain.IUsbEvent;
import com.anjeludo.usbnotifierbot.notifier.domain.UsbEvent;

@Configuration
public class QueueConf {

    @Bean
    public ConcurrentLinkedQueue<IUsbEvent> blockingQueue() {
        return new ConcurrentLinkedQueue<>();
    }
}