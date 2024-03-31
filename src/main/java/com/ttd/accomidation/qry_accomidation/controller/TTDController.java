package com.ttd.accomidation.qry_accomidation.controller;

import com.ttd.accomidation.qry_accomidation.config.AppProperties;
import com.ttd.accomidation.qry_accomidation.service.api.TTDApiClientService;
import com.ttd.accomidation.qry_accomidation.service.mail.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@Slf4j
public class TTDController {
   // private final String url = "https://online.tirupatibalaji.ap.gov.in/sdn/rest/v1/acc/get_availability?for=dashboard&location=TIRUPATI";

    private TTDApiClientService ttdApiClientService;
    private EmailService emailService;
    private AppProperties appProperties;
    @GetMapping(value = "/ttd/acc")
    public String getAccommodationSlots() {
        return ttdApiClientService.getAccommodationSlots();
    }

    @GetMapping(value = "/ttd/test")
    public String test() {
        LocalDateTime now = LocalDateTime.now();
        log.info("test service invoked {} ",now);
        return "Hello "+ now;
    }

    @GetMapping("/send")
    public void sendEmail(){
        String accommodationSlots = ttdApiClientService.getAccommodationSlots();
        String subject = "API Response triggered on "+ LocalDateTime.now();
        emailService.sendSimpleMessage(appProperties.getEmailRecipient(), subject,  accommodationSlots);
    }
}
