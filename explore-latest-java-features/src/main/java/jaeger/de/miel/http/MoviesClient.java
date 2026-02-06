package jaeger.de.miel.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

//import com.fasterxml.jackson.databind.ObjectMapper;


public class MoviesClient {

    // Make sure jwebserver is running (see README.md)!!

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(2))
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);


    private static final String ALL_MOVIES_URL = "http://127.0.0.1:8000/explore-latest-java-features/src/main/resources/movies.json";
    private static final String MOVIE_BY_ID_URL = "http://127.0.0.1:8000/explore-latest-java-features/src/main/resources/movie_by_id.json";


    public Movie getMovieById() {

        var request = requestBuilder(MOVIE_BY_ID_URL);
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP status code: " + response.statusCode());
            return objectMapper.readValue(response.body(), Movie.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public CompletableFuture<Movie> getMovieByIdAsync() {

        var request = requestBuilder(MOVIE_BY_ID_URL);

        try {
            var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            return response.thenApply(httpResponse -> {
                System.out.println("HTTP status code: " + httpResponse.statusCode());
                try {
                    return objectMapper.readValue(httpResponse.body(), Movie.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public List<Movie> getAllMovies() {

        var request = requestBuilder(ALL_MOVIES_URL);
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP status code: " + response.statusCode());
            System.out.println("HTTP body: " + response.body());
            return objectMapper.readValue(response.body(), new TypeReference<>() {});
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest requestBuilder(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }


}
