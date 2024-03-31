package com.ttd.accomidation.qry_accomidation.scheduler;

import com.ttd.accomidation.qry_accomidation.config.AppProperties;
import com.ttd.accomidation.qry_accomidation.service.api.TTDApiClientService;
import com.ttd.accomidation.qry_accomidation.service.mail.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@EnableScheduling
@Configuration
@AllArgsConstructor
@Slf4j
public class AccommodationScheduler {

    private AppProperties appProperties;
    private TTDApiClientService ttdApiClientService;
    private EmailService emailService;

    @Scheduled(fixedDelay = 60000, initialDelay = 100000)
    public void scheduleFixedDelayTask() {
        log.info("Date {} " , LocalDateTime.now());
        String accommodationSlots = ttdApiClientService.getAccommodationSlots();
        log.info("accommodationSlots data {} ",accommodationSlots);
        if(isSlotsAvailable(accommodationSlots)) {
            log.info("Sending email to {} ",appProperties.getEmailRecipient());
            emailService.sendSimpleMessage(appProperties.getEmailRecipient(),"TTD Accommodation Slots on 23rd March 2024", accommodationSlots);
        }
    }

    private static boolean isSlotsAvailable(String accommodationSlots) {
        return !accommodationSlots.contains("No slots available");
    }
}
