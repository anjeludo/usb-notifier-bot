# usb-notifier-bot

Application based on the idea of hackplayers http://www.hackplayers.com/2018/01/alerta-en-telegram-monitorizacion-usb.html.

This application uses:

- Spring Boot 2 (2.0.0.M7, latest version currently)
- https://github.com/rubenlagus/TelegramBots for communication with the telegram bot
- https://github.com/samuelcampos/usbdrivedetector for the detection of USB devices
- Vaadin for the user interface

The application could be launched as a service. Each time a USB device is connected or disconnected, it will detect it, generating an event (using Spring events) which will be listened to and sent to the telegram bot (the bot must be configured en in application.properties file) and the user interface (in http://localhost:8080).

To run this app clone this repo and execute mvn spring-boot:run
