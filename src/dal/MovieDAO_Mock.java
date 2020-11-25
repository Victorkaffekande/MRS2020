package dal;

import be.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO_Mock implements IMovieDataAccess {

    private List<Movie> allMovies;

    public MovieDAO_Mock()
    {
        allMovies = new ArrayList<>();
        allMovies.add(new Movie(1, "Trump - the movie", 2020));
        allMovies.add(new Movie(1, "Trump - I did it again", 2024));
        allMovies.add(new Movie(1, "Trump - The new dictator on the block", 2028));

    }

    @Override
    public List<Movie> getAllMovies() {
        return allMovies;
    }

}
