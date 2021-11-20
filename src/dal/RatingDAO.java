package dal;

import be.Rating;
import be.User;

import java.util.List;

public class RatingDAO {

    /**
     * Persists the given rating.
     * @param rating the rating to persist.
     */
    public void createRating(Rating rating)
    {
        //TODO Rate movie
    }

    /**
     * Updates the rating to reflect the given object.
     * @param rating The updated rating to persist.
     */
    public void updateRating(Rating rating)
    {
        //TODO Update rating
    }

    /**
     * Removes the given rating.
     * @param rating
     */
    public void deleteRating(Rating rating)
    {
        //TODO Delete rating
    }

    /**
     * Gets all ratings from all users.
     * @return List of all ratings.
     */
    public List<Rating> getAllRatings()
    {
        //TODO Get all rating.
        return null;
    }

    /**
     * Get all ratings from a specific user.
     * @param user The user
     * @return The list of ratings.
     */
    public List<Rating> getRatings(User user)
    {
        //TODO Get user ratings.
        return null;
    }
}
