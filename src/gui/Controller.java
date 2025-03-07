package gui;

import be.Movie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public TextField txtMovieSearch;
    public ListView<Movie> lstMovies;
    public TextField releaseYearInput;
    public TextField titleInput;
    public TextField updateTitleInput;
    public TextField updateRealeaseYearInput;
    public ListView lstUsers;

    private MovieModel movieModel;
    private UserModel userModel;

    public Controller()  {

        try {
            movieModel = new MovieModel();
            userModel = new UserModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        lstMovies.setItems(movieModel.getObservableMovies());
        lstUsers.setItems(userModel.getObservableUsers());

        //Selection mode SINGLE / MULTIPLE
        lstMovies.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                movieModel.searchMovie(newValue);
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }
        });

    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    private void infoError(String error){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Missing information");
        alert.setContentText(error);
        alert.showAndWait();
    }

    public void createMovieButton(ActionEvent actionEvent) throws Exception {
        String idString = releaseYearInput.getText();
        String titleString =  titleInput.getText();

        if ( idString.isBlank() || titleString.isBlank()){
            infoError("Please make sure all the boxes are filled out and try again");
        }
        else
        {
            int idInt = Integer.parseInt(idString);
            titleString = titleString.trim();
            movieModel.createMovie(titleString,idInt);
            titleInput.clear();
            releaseYearInput.clear();
            lstMovies.scrollTo(movieModel.getObservableMovies().size());
        }

    }

    public void deleteMovieButton(ActionEvent actionEvent) throws Exception {
        ObservableList<Movie> selectedRows, allMovies;
        allMovies = movieModel.getObservableMovies();
        selectedRows = lstMovies.getSelectionModel().getSelectedItems();

        //loop over selected rows and delete them from allTask list selected rows is not empty
        if (selectedRows.isEmpty()){
            infoError("Please select a task and try again");
        }
        else
        {
            Movie movie = lstMovies.getSelectionModel().getSelectedItem();
            allMovies.remove(movie);
            movieModel.deleteMovie(movie);
            lstMovies.getSelectionModel().clearSelection();
        }

    }

    public void updateMovieButton(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = lstMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie == null){
            infoError("Please select a movie and try again");
        }
        else
        {
            String titleInput =updateTitleInput.getText();
            int yearInput = Integer.parseInt(updateRealeaseYearInput.getText());
            Movie updatedMovie = new Movie(selectedMovie.getId(),yearInput,titleInput);
            movieModel.updateMovie(updatedMovie);
            lstMovies.getSelectionModel().clearSelection();
        }

    }

    public void titleAndYearToUpdater(MouseEvent mouseEvent) {
        Movie selectedMovie = lstMovies.getSelectionModel().getSelectedItem();
        updateTitleInput.setText(selectedMovie.getTitle());
        updateRealeaseYearInput.setText(selectedMovie.getYear() + "");
    }
}
