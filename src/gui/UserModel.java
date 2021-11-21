package gui;

import be.User;
import bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {
    private ObservableList<User> usersToBeViewed;
    private MovieManager movieManager;

    public UserModel() throws Exception{
        movieManager = new MovieManager();

        usersToBeViewed = FXCollections.observableArrayList();
        usersToBeViewed.addAll(movieManager.getAllUsers());
    }
    public ObservableList<User> getObservableUsers(){
        return usersToBeViewed;
    }

}
