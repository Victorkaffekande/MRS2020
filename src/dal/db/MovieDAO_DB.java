package dal.db;

import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.IMovieDataAccess;
import dal.MovieDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDataAccess {

    private MyDatabaseConnector databaseConnector;

    public MovieDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }

    public List<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> allMovies = new ArrayList<>();
        try(Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT * FROM Movie;";

            Statement statement = connection.createStatement();

            if (statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    int year = resultSet.getInt("year");

                    Movie movie = new Movie(id,year,title);
                    allMovies.add(movie);
                }
            }

        }
        return allMovies;
    }

    public Movie createMovie(String title, int year) throws Exception {
        //TODO Do this
        throw new UnsupportedOperationException();
    }

    public void updateMovie(Movie movie) throws Exception {
        //TODO Do this
        throw new UnsupportedOperationException();
    }

    public void deleteMovie(Movie movie) throws Exception {
        //TODO Do this
        throw new UnsupportedOperationException();
    }

    public List<Movie> searchMovies(String query) throws Exception {

        //TODO Do this
        throw new UnsupportedOperationException();
    }

    /**
     * add testdata to server
     * @throws IOException
     */
    private void addFileDataToDB() throws IOException {

        List<Movie> allMovies = new MovieDAO().getAllMovies();
        try(Connection connection = databaseConnector.getConnection()){

            Statement statement = connection.createStatement();

            String sql = "INSERT INTO Movie VALUES(Title "+"testman"+",Year 1999)";
            statement.execute(sql);
            /*
            for (Movie movie : allMovies){
                String sql = "INSERT INTO Movie"+" VALUES("+movie.getTitle()+","+movie.getYear()+")";
                statement.execute(sql);
            }
            */
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        MovieDAO_DB movieDAO_DB = new MovieDAO_DB();
        movieDAO_DB.addFileDataToDB();

    }
}
