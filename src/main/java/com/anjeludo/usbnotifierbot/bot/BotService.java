package com.anjeludo.usbnotifierbot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anjeludo.usbnotifierbot.bot.impl.UsbNotifierBot;


@Service
public class BotService {
	
	@Value("${usbnotifierbot.token}")
	private String token;
	
	@Value("${usbnotifierbot.botname}")
	private String botName;
	
	@Value("${usbnotifierbot.chatid}")
	private String chatId;
	
	
	public void sendMessage(String message) {
		IBot b = UsbNotifierBot.getInstance(token, botName, chatId);
		b.sendMessage(message);
	}

}
