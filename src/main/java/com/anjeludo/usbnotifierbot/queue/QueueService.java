package com.anjeludo.usbnotifierbot.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anjeludo.usbnotifierbot.notifier.domain.IUsbEvent;

@Service
public class QueueService {
	
	@Autowired
	private QueueConf queue;
	
	public void add(IUsbEvent e) {
		queue.blockingQueue()
					.add(UsbEventUI.builder()
							.deviceName(e.getDeviceName())
							.eventType(e.getEventType())
							.time(e.getTime())
							.build()
					);
	}
	
	public IUsbEvent get() {
		return queue.blockingQueue().poll();
	}
	
	public int size() {
		return queue.blockingQueue().size();
	}

}
