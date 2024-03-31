package com.ttd.accomidation.qry_accomidation.service.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ttd.accomidation.qry_accomidation.config.AppProperties;
import com.ttd.accomidation.qry_accomidation.service.mail.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@AllArgsConstructor
@Service
@Slf4j
public class TTDApiClientService {

    private RestTemplate restTemplate;
    private EmailService emailService;
    private AppProperties appProperties;
    private static long counter;

    public String getAccommodationSlots() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String body = restTemplate.exchange(appProperties.getTtdAccUrl(), HttpMethod.GET, entity, String.class).getBody();
        counter++;
        log.info("counter {} API Response body {} ",counter ,body);
        return processJson(body);
    }
    private String processJson(String body){
        Gson gson = new Gson();
        // Parse JSON string into a JsonObject
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);

        // Extract specific elements
        String status = jsonObject.get("status").getAsString();
        if(status.equalsIgnoreCase("success")){
            JsonObject resultObj = jsonObject.getAsJsonObject("result");
            JsonObject accomidationDate23rdMarch = resultObj.getAsJsonObject("20240323");
            log.info("accomidationDate23rdMarch {} ",accomidationDate23rdMarch);
            int avl = accomidationDate23rdMarch.get("avl").getAsInt();
            if(avl > 0) {
                String slots = accomidationDate23rdMarch.get("slots").toString();
                return slots;
            }
        }
        log.info("No slots available on 20240323 date ");
        return "No slots available on 20240323 date ";
    }

}
