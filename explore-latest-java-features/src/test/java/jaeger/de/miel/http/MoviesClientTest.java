package jaeger.de.miel.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoviesClientTest {

    private final MoviesClient moviesClient = new MoviesClient();


    // Make sure jwebserver is running (see README.md)!!

    @Test
    public void getMovieByIdTest() {
        var movie = moviesClient.getMovieById();
        assertNotNull(movie);
        assertEquals("Batman Begins", movie.name());
    }

    @Test
    public void getMovieByIdAsyncTest() {
        var movie = moviesClient.getMovieByIdAsync().join();
        assertNotNull(movie);
        assertEquals("Batman Begins", movie.name());
    }

    @Test
    public void getAllMoviesTest() {
        var moviesList = moviesClient.getAllMovies();
        assertEquals(10, moviesList.size());
    }

}
