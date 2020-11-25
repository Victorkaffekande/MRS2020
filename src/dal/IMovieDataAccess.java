package dal;

import be.Movie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IMovieDataAccess {

    public List<Movie> getAllMovies() throws Exception;



}
