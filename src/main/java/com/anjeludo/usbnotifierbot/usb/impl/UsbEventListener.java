package com.anjeludo.usbnotifierbot.usb.impl;

import com.anjeludo.usbnotifierbot.util.SpringContextBridge;

import net.samuelcampos.usbdrivedetector.events.IUSBDriveListener;
import net.samuelcampos.usbdrivedetector.events.USBStorageEvent;


public class UsbEventListener implements IUSBDriveListener {
	private static UsbEventListener instance = null;
	
	protected UsbEventListener() {}
	
	public static UsbEventListener getInstance() {
	    if(instance == null) {
	       instance = new UsbEventListener();
	    }
	    return instance;
	 }

	@Override
	public void usbDriveEvent(USBStorageEvent event) {
		SpringContextBridge.services().getUsbProducer().createEvent(event.getStorageDevice().getSystemDisplayName(), event.getEventType().toString());
	}
}
