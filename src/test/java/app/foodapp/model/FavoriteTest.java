package app.foodapp.model;

import org.junit.jupiter.api.Test;

import javax.management.InstanceAlreadyExistsException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FavoriteTest {

    @Test
    void testAddToFavorite() throws InstanceAlreadyExistsException {
        Favorite testFavorite = new Favorite();
        Recipe testRecipe1 = new Recipe(1620, null, null,8,30);
        Recipe testRecipe2 = new Recipe(1400, null, null, 9, 45);

        testFavorite.addToFavorite(testRecipe1);
        testFavorite.addToFavorite(testRecipe2);
        assertEquals(testFavorite.getFavorites(), List.of(testRecipe1, testRecipe2));

        assertThrows(InstanceAlreadyExistsException.class, () -> {
           testFavorite.addToFavorite(testRecipe1);
        });
    }

}
