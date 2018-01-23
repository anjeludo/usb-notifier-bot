package com.anjeludo.usbnotifierbot.notifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.anjeludo.usbnotifierbot.bot.BotService;
import com.anjeludo.usbnotifierbot.notifier.domain.UsbEvent;
import com.anjeludo.usbnotifierbot.queue.QueueService;

@Component
public class UsbListener implements ApplicationListener<UsbEvent> {
	
	@Autowired
	private BotService botService;
	
	@Autowired
	private QueueService queue;

	@Override
    public void onApplicationEvent(UsbEvent event) {
		botService.sendMessage(event.toString());
		queue.add(event);
    }

}
