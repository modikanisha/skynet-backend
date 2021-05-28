package com.skynet.initdata;

import com.skynet.initdata.relational.RelationChangeSetProcessor;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ApplicationStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final RelationChangeSetProcessor relationChangeSetProcessor;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        relationChangeSetProcessor.process();
    }

}
