package com.anjeludo.usbnotifierbot.notifier.domain;

public interface IUsbEvent {
	public String getTime();
	public String getDeviceName();
	public String getEventType();

}
