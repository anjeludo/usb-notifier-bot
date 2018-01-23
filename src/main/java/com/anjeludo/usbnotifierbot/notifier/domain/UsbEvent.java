package com.anjeludo.usbnotifierbot.notifier.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;


public class UsbEvent extends ApplicationEvent implements IUsbEvent {
	private static final long serialVersionUID = 4978860202859203490L;
	
	@Getter
	private String deviceName;
	
	@Getter
	private String eventType;
	
	//@Getter
	private Calendar time;
    
    public UsbEvent(Builder b) {
    	super(b.source);
    	this.deviceName = b.deviceName;
    	this.eventType = b.eventType;
    	this.time = b.time;
    }
    
    public static class Builder {
    	private String deviceName;
    	private String eventType;
    	private Object source;
    	private Calendar time;

        public Builder(Object source, String deviceName, String eventType) {
          this.source = source;
          this.deviceName = deviceName;
          this.eventType = eventType;
          this.time = GregorianCalendar.getInstance();
        }

        public UsbEvent build() {
          return new UsbEvent(this);
        }

      }

	@Override
	public String getTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdfDate.format(time.getTime());
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		return b.append("Device name: ")
				 .append( getDeviceName())
				 .append(" ")
				 .append("Event type: ")
				 .append(getEventType())
				 .append(" ")
				 .append("Time: ")
				 .append(getTime())
				 .toString();
	}

}
