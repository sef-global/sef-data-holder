package org.sefgobal.dataholder.api;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class YoutubeAPI {

    private static final String MAX_RESULTS = "10";
    @GetMapping("/youtube/onelive")
    public void getOneLive(@RequestParam(required = false) String nextPageToken, HttpServletResponse response) {
        String googleApiKey = System.getenv("GOOGLE_API_KEY");
        String youtubePlaylistId = System.getenv("YOUTUBE_PLAYLIST_ID");
        String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="
                + youtubePlaylistId
                + "&maxResults="
                + MAX_RESULTS
                + "&key="
                + googleApiKey;

        // Check if the user has sent a page token for the next page and add it to the url
        if (nextPageToken != null) {
            url = url + "&pageToken=" + nextPageToken;
        }

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
