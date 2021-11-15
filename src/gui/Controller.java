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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public TextField txtMovieSearch;
    public ListView<Movie> lstMovies;
    public TextField releaseYearInput;
    public TextField titleInput;

    private MovieModel movieModel;

    public Controller()  {

        try {
            movieModel = new MovieModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        lstMovies.setItems(movieModel.getObservableMovies());
        //Selection mode SINGLE / MULTIPLE
        lstMovies.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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

    public void createMovieButton(ActionEvent actionEvent) throws Exception {
        String idString = releaseYearInput.getText();
        String titleString =  titleInput.getText();

        if ( idString.isBlank() || titleString.isBlank()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Missing information");
            alert.setContentText("Please make sure all the boxes are filled out and try again");
            alert.showAndWait();
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

    public void deleteMovieButton(ActionEvent actionEvent) {
        ObservableList<Movie> selectedRows, allMovies;
        allMovies = movieModel.getObservableMovies();
        selectedRows = lstMovies.getSelectionModel().getSelectedItems();

        //loop over selected rows and delete them from allTask list selected rows is not empty
        if (!selectedRows.isEmpty()){
            allMovies.removeAll(selectedRows);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("No task was selected");
            alert.setContentText("Please select a task and try again");

            alert.showAndWait();
        }

    }
}
