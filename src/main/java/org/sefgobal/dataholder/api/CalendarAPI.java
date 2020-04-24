package org.sefgobal.dataholder.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CalendarAPI {

    @GetMapping("/calendar/onelive")
    public void getUpcomingOnelives(HttpServletRequest request, HttpServletResponse response) {
        String googleApiKey = System.getenv("GOOGLE_API_KEY");
        String oneliveCalendarId = System.getenv("ONELIVE_CALENDAR_ID");
        //Get the current time and format it to the RFC3339 format
        ZonedDateTime timeNow = ZonedDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:'00':'00'Z");
        String formattedDateTime = timeNow.format(format);
        formattedDateTime = formattedDateTime.substring(0, 22) + ":" + formattedDateTime.substring(22);
        formattedDateTime = formattedDateTime.replace(":", "%3A").replace("+", "%2B");
        String url = "https://www.googleapis.com/calendar/v3/calendars/" +
                oneliveCalendarId +
                "/events?orderBy=startTime&singleEvents=true&timeMin=" +
                formattedDateTime +
                "&key=" +
                googleApiKey;
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet executor = new HttpGet(url);
        try {
            HttpResponse resp = httpclient.execute(executor);
            BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent(),
                    StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            response.setContentType("application/json");
            response.getWriter().write(result.toString());
        } catch (IOException e) {
            response.setStatus(500);
        }
    }
}
