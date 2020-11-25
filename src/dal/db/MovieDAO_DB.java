package dal.db;

import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.IMovieDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDataAccess {

    private MyDatabaseConnector databaseConnector;

    public MovieDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }

    public List<Movie> getAllMovies() throws SQLServerException {

        //Create return data structure:
        ArrayList<Movie> allMovies = new ArrayList<>();

        //Create a connection:
        try (Connection connection = databaseConnector.getConnection()) {
            //Create SQL command:
            String sql = "SELECT * FROM movie;";
            //Create some kind of statement:
            Statement statement = connection.createStatement();
            //Do relevant treatment of statement:
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String title = resultSet.getString("Title");
                    int year = resultSet.getInt("Year");
                    Movie movie = new Movie(id, title, year);
                    allMovies.add(movie);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allMovies;
    }

    public Movie createMovie(String title, int year) throws Exception {
        //Datasource
        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();
        //Connection
        try (Connection con = databaseConnector.getConnection()) {

            //SQL
            String sql = "INSERT INTO Movie VALUES (?,?);";
            //Statement
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, year);

            // INSERT INTO Movie VALUES ('Star Wars Ep. 9',2020);DROP TABLE Movie;--', 2020);

            //Execute update
            if (0 < preparedStatement.executeUpdate()) {
                //Result Set
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    //Return some value
                    int id = resultSet.getInt(1);
                    Movie movie = new Movie(id, title, year);
                    return movie;
                }
            }
        }
        throw new Exception("Could not create movie");
    }

    public static void main(String[] args)  {

        MovieDAO_DB movieDAO_db = new MovieDAO_DB();

        try {

            Movie movie = movieDAO_db.createMovie("Star Wars Ep. 9',2020);DROP TABLE Movie;--", 2020);

            System.out.println(movie);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
