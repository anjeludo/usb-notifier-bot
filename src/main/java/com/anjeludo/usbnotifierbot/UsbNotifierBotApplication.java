package com.anjeludo.usbnotifierbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anjeludo.usbnotifierbot.usb.UsbDetectorService;

@SpringBootApplication
public class UsbNotifierBotApplication implements CommandLineRunner {
	
	@Autowired
	private UsbDetectorService usbService;

	public static void main(String[] args) {
		SpringApplication.run(UsbNotifierBotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		usbService.listenToUsbConnections();
	}
}
