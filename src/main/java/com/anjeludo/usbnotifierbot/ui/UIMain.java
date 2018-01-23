package com.anjeludo.usbnotifierbot.ui;



import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import com.anjeludo.usbnotifierbot.notifier.domain.IUsbEvent;
import com.anjeludo.usbnotifierbot.queue.QueueService;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.shared.ui.ui.Transport;

import lombok.extern.slf4j.Slf4j;

@Push(transport = Transport.WEBSOCKET)
@Title("Push USB notifications")
@SpringUI
@Theme(ValoTheme.THEME_NAME)
@Slf4j
public class UIMain extends UI {
	private static final long serialVersionUID = -531331135648157276L;
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    
    @Autowired
    private QueueService queue;

    private Grid<IUsbEvent> grid;
    private ScheduledFuture<?> jobHandle;
    private ArrayList<IUsbEvent> events;

    private Runnable checkQueue = new Runnable() {
        public void run() {
            access(new Runnable() {
                @Override
                @SuppressWarnings("unchecked")
                public void run() {
                	IUsbEvent usbEvent;
//					try {
                		log.debug("Queue size: " + queue.size());
						usbEvent = queue.get();
						if(usbEvent != null) {
							log.debug(usbEvent.toString());
							events.add(usbEvent);
							grid.setItems(events);
							Notification.show("New USB interaction!");
						}
                }
            });
        }
    };

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        events = new ArrayList<>();
        setContent(buildGrid());
        log.info("Scheduling background job");
        jobHandle = executorService.scheduleWithFixedDelay(checkQueue, 500, 3000, TimeUnit.MILLISECONDS);
    }
    
    private Grid buildGrid() {
    	grid = new Grid(IUsbEvent.class);
        grid.setSizeFull();
        return grid;
    }

    @PreDestroy
    void destroy() {
        log.info("Canceling background job");
        jobHandle.cancel(true);
    }
}
