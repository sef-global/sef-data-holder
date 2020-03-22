# sef-data-holder
## Run Locally

- Clone your forked repository
    ```
    git clone https://github.com/USERNAME/sef-data-holder
    cd sef-data-holder
    ```
- Set Username and a password as environment variables
    ```
    export YOUTUBE_API_KEY==your_youtube_api_key
    export LIVE_PLAYLIST_ID=your_playlist_id
    ```
- Build the project
    ```
    mvn install
    ```
- Run the generated jar file
    ```
    java -jar target/data-holder.war
    ```
- Visit your app at http://localhost:8080