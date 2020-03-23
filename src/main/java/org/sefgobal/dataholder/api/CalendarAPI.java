package org.sefgobal.dataholder.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


@RestController
public class CalendarAPI {

    @GetMapping("/upcoming-events")
    public void getUpcomingEvents(HttpServletRequest request, HttpServletResponse response) {
        String googleApiKey = System.getenv("GOOGLE_API_KEY");
        String calendarId = System.getenv("CALENDAR_ID");
        String url = "https://www.googleapis.com/calendar/v3/calendars/"+calendarId+"/events?key="+googleApiKey;

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
            response.getWriter().write(result.toString());
        } catch (IOException e) {
            response.setStatus(500);
        }
    }
}
