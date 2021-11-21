package bll;

import be.Movie;
import be.User;
import bll.util.MovieSearcher;
import dal.IMovieDataAccess;
import dal.MovieDAO;
import dal.MovieDAO_Mock;
import dal.UserDAO;

import java.io.FileNotFoundException;
import java.util.List;

public class MovieManager {

    private MovieSearcher movieSearcher = new MovieSearcher();

    private IMovieDataAccess movieDAO;
    private UserDAO userDAO;

    public MovieManager() {
        movieDAO = new MovieDAO();
        userDAO = new UserDAO();
    }

    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    public Movie createMovie(String title, int year) throws Exception {
        return movieDAO.createMovie(title,year);
    }

    public void updateMovie(Movie movie) throws Exception{
       movieDAO.updateMovie(movie);
    }

    public List<Movie> searchMovies(String query) throws Exception {
        List<Movie> allMovies = getAllMovies();
        List<Movie> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }

    public void deleteMovie(Movie movie) throws Exception {
        movieDAO.deleteMovie(movie);
    }

    public List<User> getAllUsers() throws FileNotFoundException {
        return userDAO.getAllUsers();
    }






}
