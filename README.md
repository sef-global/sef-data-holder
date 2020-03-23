# sef-data-holder
## Run Locally

- Clone your forked repository
    ```
    git clone https://github.com/USERNAME/sef-data-holder
    cd sef-data-holder
    ```
- Set Youtube API key and Youtube playlist ID as environment variables
    ```
    export YOUTUBE_API_KEY=your_youtube_api_key
    export YOUTUBE_PLAYLIST_ID=your_playlist_id
    ```
- Set Google API key and Google Calendar ID as environment variables to use the Calendar API
    ```
    export GOOGLE_API_KEY=your_google_api_key
    export CALENDAR_ID=your_calendar_id
    ```

- Build the project
    ```
    mvn install
    ```
- Run the generated jar file
    ```
    java -jar target/data-holder.jar
    ```
- Visit your app at http://localhost:8080