package dal;

import be.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO_File implements IMovieDataAccess {

    public static void main(String[] args) {

        MovieDAO_File movieDAO = new MovieDAO_File();

        for (Movie movie : movieDAO.getAllMovies()) {
            System.out.println(movie);
        }


    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();

        //TODO Fill list with movie objects

        return movieList;
    }

}
