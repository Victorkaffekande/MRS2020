package gui;

import be.Movie;
import be.User;
import bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class MovieModel {

    private ObservableList<Movie> moviesToBeViewed;


    private MovieManager movieManager;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());


    }

    public ObservableList<Movie> getObservableMovies() {
        return moviesToBeViewed;
    }


    public void searchMovie(String query) throws Exception {
        List<Movie> searchResults = movieManager.searchMovies(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(searchResults);
    }

    public void createMovie(String title, int year) throws Exception {
        Movie movie = movieManager.createMovie(title,year);
        moviesToBeViewed.add(movie);
    }

    public void updateMovie(Movie movie) throws Exception{
        movieManager.updateMovie(movie);
        moviesToBeViewed.remove(movie);
        moviesToBeViewed.add(movie);
        moviesToBeViewed.sort(Comparator.comparingInt(Movie::getId));
    }

    public void deleteMovie(Movie movie) throws Exception {
        movieManager.deleteMovie(movie);
    }
}
