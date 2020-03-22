# sef-data-holder
## Run Locally

- Clone your forked repository
    ```
    git clone https://github.com/USERNAME/sef-data-holder
    cd sef-data-holder
    ```
- Set Youtube API key and Youtube playlist ID as environment variables
    ```
    export YOUTUBE_API_KEY==your_youtube_api_key
    export YOUTUBE_PLAYLIST_ID=your_playlist_id
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