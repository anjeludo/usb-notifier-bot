package com.anjeludo.usbnotifierbot.notifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.anjeludo.usbnotifierbot.notifier.domain.UsbEvent;
import com.anjeludo.usbnotifierbot.queue.QueueConf;
import com.anjeludo.usbnotifierbot.queue.QueueService;

@Component
public class UsbProducer {
	
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;

	
	public void createEvent(String deviceName, String eventType) {
		applicationEventPublisher.publishEvent(new UsbEvent.Builder(this, deviceName, eventType).build());
	}

}
