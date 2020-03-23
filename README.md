# sef-data-holder
## Run Locally

- Clone your forked repository
    ```
    git clone https://github.com/USERNAME/sef-data-holder
    cd sef-data-holder
    ```

- Setup an API key for Google API 
    - Use the tutorial given below.
    https://support.google.com/googleapi/answer/6158862?hl=en
    
    - Enable Calender API and Youtube API on the Google API console

- Set Google API key, Youtube playlist ID, Google Calendar ID as environment variables
    ```
    export GOOGLE_API_KEY=your_google_api_key
    export YOUTUBE_PLAYLIST_ID=your_playlist_id
    export ONELIVE_CALENDAR_ID=your_calendar_id
    ```
    *Note: The Calendar should be public.*

- Build the project
    ```
    mvn install
    ```
- Run the generated jar file
    ```
    java -jar target/data-holder.jar
    ```
- Visit your app at http://localhost:8080