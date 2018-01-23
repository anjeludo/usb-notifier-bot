package com.anjeludo.usbnotifierbot.queue;

import java.util.Calendar;

import com.anjeludo.usbnotifierbot.notifier.domain.IUsbEvent;

import lombok.Builder;
import lombok.Getter;

@Builder
public class UsbEventUI implements IUsbEvent {	
	
	@Getter
	private String deviceName;
	
	@Getter
	private String eventType;
	
	@Getter
	private String time;
}
