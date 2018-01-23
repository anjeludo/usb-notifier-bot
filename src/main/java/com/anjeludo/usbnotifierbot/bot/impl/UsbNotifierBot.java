package com.anjeludo.usbnotifierbot.bot.impl;

import org.telegram.telegrambots.ApiContextInitializer;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import com.anjeludo.usbnotifierbot.bot.IBot;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class UsbNotifierBot extends TelegramLongPollingBot  implements IBot {
	private String token;
	private String botName;
	private String chatId;
	
	private static UsbNotifierBot instance = null;
	
	private void initBot() {
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}
	
	public static IBot getInstance(String token, String botName, String chatId) {
      if(instance == null) {
    	  ApiContextInitializer.init();
    	  instance = new UsbNotifierBot(token, botName, chatId);
    	  instance.initBot();
      }
      return instance;
	}

	@Override
	public String getBotUsername() {
		return botName;
	}
	
	public void onUpdateReceived(String text) {
	    if (text != null) {
	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                //.setChatId(update.getMessage().getChatId())
	        		.setChatId(chatId)
	                .setText(text);
	        try {
	            execute(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public void onUpdateReceived(Update update) {
		 // We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	    	log.debug("Chat id " + update.getMessage().getChatId());
	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                //.setChatId(update.getMessage().getChatId())
	        		.setChatId(chatId)
	                .setText(update.getMessage().getText());
	        
	        System.out.println( update.getMessage().getChatId() );
	        
	        try {
	            execute(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
		
	}

	@Override
	public String getBotToken() {
		return token;
	}


	@Override
	public void sendMessage(String message) {
		onUpdateReceived(message); 
	}

}
