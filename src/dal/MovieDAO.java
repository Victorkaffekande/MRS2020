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
        File file = new File(MOVIES_FILE);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

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
        // creating the movie object
        List<Movie> movieList = getAllMovies();
        int size = movieList.size();
        int lastId = movieList.get(size-1).getId();
        int id = lastId+1;


        // adding the movie info to the file
        FileWriter fileWriter = new FileWriter(MOVIES_FILE, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(id + "," + year + "," + title + "\n");
        bufferedWriter.close();

        return new Movie(id, year, title);
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {

    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {
        int id = movie.getId();
        String title = movie.getTitle();
        int year = movie.getYear();

        File testFile = new File(TEST_FILE);
        FileWriter fileWriter = new FileWriter(testFile,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        

    }

}