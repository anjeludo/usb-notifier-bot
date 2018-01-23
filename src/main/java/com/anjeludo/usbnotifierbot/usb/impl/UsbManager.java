package com.anjeludo.usbnotifierbot.usb.impl;

import com.anjeludo.usbnotifierbot.usb.IUsb;
import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;

public class UsbManager implements IUsb {
	private static UsbManager instance = null;
	 private USBDeviceDetectorManager driveDetector = null;
	
	protected UsbManager() {}
	
	public static IUsb getInstance() {
	    if(instance == null) {
	       instance = new UsbManager();
	       instance.driveDetector = new USBDeviceDetectorManager();
	    }
	    return instance;
	 }

	@Override
	public void listen() {
	     this.driveDetector.addDriveListener(UsbEventListener.getInstance());
	}

	@Override
	public void printDevices() {
		this.driveDetector.getRemovableDevices().forEach(System.out::println);
	}

}
