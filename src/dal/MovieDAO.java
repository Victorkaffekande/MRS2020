package dal;

import be.Movie;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MovieDAO implements IMovieDataAccess {

    private static final String MOVIES_FILE = "data/movie_titles.txt";
    private static final String TMP_FILE = "data/tmp.txt";


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


        try{// adding the movie info to the file
            FileWriter fileWriter = new FileWriter(MOVIES_FILE, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(id + "," + year + "," + title + "\n");
        }catch (Exception e){
            throw e;
        }


        return new Movie(id, year, title);
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {
        try{
            File tmpFile = new File(TMP_FILE);
            File movieFile = new File(MOVIES_FILE);
            List<Movie> allMovies = getAllMovies();
            allMovies.removeIf((Movie t) -> t.getId() == movie.getId());
            allMovies.add(movie);

            //sort movies by ID
            allMovies.sort(Comparator.comparingInt(Movie::getId));

            BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
                for (Movie mov : allMovies){
                    bw.write(mov.getId() + "," + mov.getYear() + "," + mov.getTitle());
                    bw.newLine();
                }
            bw.close();

            //Overwrite the movie file with the tmp file
            InputStream in = new FileInputStream(tmpFile);
            OutputStream out = new FileOutputStream(movieFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            // Delete tmp file
            Files.delete(tmpFile.toPath());
        }
        catch (IOException ex){
            System.out.println("error");
        }

    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {
        File tmpFile = new File(TMP_FILE);
        File movieFile = new File(MOVIES_FILE);
        List<Movie> allMovies = getAllMovies();
        allMovies.removeIf((Movie t) -> t.getId() == movie.getId());

        //sort movies by ID
        allMovies.sort(Comparator.comparingInt(Movie::getId));

        BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
        for (Movie mov : allMovies){
            bw.write(mov.getId() + "," + mov.getYear() + "," + mov.getTitle());
            bw.newLine();
        }
        bw.close();

        //Overwrite the movie file with the tmp file
        InputStream in = new FileInputStream(tmpFile);
        OutputStream out = new FileOutputStream(movieFile);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        // Delete tmp file
        Files.delete(tmpFile.toPath());
    }


}