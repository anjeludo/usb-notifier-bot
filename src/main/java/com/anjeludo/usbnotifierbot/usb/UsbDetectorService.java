package com.anjeludo.usbnotifierbot.usb;

import org.springframework.stereotype.Service;

import com.anjeludo.usbnotifierbot.usb.impl.UsbManager;

@Service
public class UsbDetectorService {
	
	public void listenToUsbConnections() {
		UsbManager.getInstance().listen();
	}
	
	public void printDevices() {
		UsbManager.getInstance().printDevices();
	}

}
