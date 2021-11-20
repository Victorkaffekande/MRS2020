package dal;

import be.Movie;
import be.User;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserDAO {
    private static final String USER_FILE = "data/users.txt";
    private static final String TMP_FILE = "data/tmp_users";
    /**
     * Gets a list of all known users.
     * @return List of users.
     */
    public List<User> getAllUsers() throws FileNotFoundException {
        List<User> userList = new ArrayList<>();
        File file = new File(USER_FILE);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        if (bufferedReader.lines() != null){
            for (String line : bufferedReader.lines().toList()){
                String[] lineSplit = line.split(",");
                int id = Integer.parseInt(lineSplit[0]);
                String name = lineSplit[1];
                User user = new User(id,name);
                userList.add(user);
            }
        }
        return userList;

    }

    /**
     * Gets a single User by its ID.
     * @param id The ID of the user.
     * @return The User with the ID.
     */
    public User getUser(int id) throws FileNotFoundException {
        List<User> userList = getAllUsers();

        //sort list from smallest id to largest
        userList.sort((o1, o2) -> o1.getId()- o2.getId());

        int first =0;
        int last = userList.size()-1;

        while(first <= last)
        {
            int middle = last + ((first- last) / 2);

            User middlePerson  = userList.get(middle);

            if (middlePerson.getId() == id){
                return middlePerson;
            }
            else if( middlePerson.getId() < id){
                first = middle +1;
            }else{
                last = middle - 1;
            }
        }
        return null;
    }

    /**
     * Updates a user so the persistence storage reflects the given User object.
     * @param user The updated user.
     */
    public void updateUser(User user)
    {
        try {
            File tmpFile = new File(TMP_FILE);
            File userFile = new File(USER_FILE);

            List<User> allUsers = getAllUsers();
            allUsers.removeIf((User t) -> t.getId() == user.getId());
            allUsers.add(user);

            //sort users by ID
            allUsers.sort(Comparator.comparingInt(User :: getId));

            BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
                for (User u : allUsers){
                    bw.write(u.getId() + "," + u.getName());
                    bw.newLine();
                }
            bw.close();

            //Overwrite the movie file with the tmp file
            InputStream in = new FileInputStream(tmpFile);
            OutputStream out = new FileOutputStream(userFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            // Delete tmp file
            Files.delete(tmpFile.toPath());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
