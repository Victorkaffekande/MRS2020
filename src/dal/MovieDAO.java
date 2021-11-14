package dal;

import be.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements IMovieDataAccess {

    private static final String MOVIES_FILE = "data/movie_titles.txt";
    private static final String TEST_FILE = "data/test_doc.txt";


    /**
     * if movie list text file is not empty, turn them into movie objects add them to list
     * @return List of movie objects
     */
    public List<Movie> getAllMovies() throws IOException {
        List<Movie> movieList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(MOVIES_FILE));

        if (bufferedReader.lines() != null){
            for (String line : bufferedReader.lines().toList()){
                String[] lineSplit = line.split(",");
                int id = Integer.parseInt(lineSplit[0]);
                int releaseYear = Integer.parseInt(lineSplit[1]);
                String title = lineSplit[2];

                Movie movie = new Movie(id,releaseYear,title);
                movieList.add(movie);
            }
        }
        return movieList;
    }

    @Override
    public Movie createMovie(String title, int year) throws Exception {

        FileWriter fileWriter = new FileWriter(TEST_FILE, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        List<Movie> movieList = getAllMovies();

        int id = movieList.get(movieList.lastIndexOf(movieList)).getId();
        Movie movie = new Movie(id, year, title);


        bufferedWriter.write(id + "," + year + "," + title + "\n");
        bufferedWriter.close();

        return null;
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {

    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {

    }

}