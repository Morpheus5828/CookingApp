package app.foodapp.model;

import org.junit.jupiter.api.Test;

import javax.management.InstanceAlreadyExistsException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FavoriteTest {

    Favorite testFavorite = new Favorite();
    Recipe testRecipe1 = new Recipe(1620, null, null,8,30);
    Recipe testRecipe2 = new Recipe(1400, null, null, 9, 45);

    @Test
    void testAddToFavorite() throws InstanceAlreadyExistsException {
        assertEquals(testFavorite.addToFavorite(testRecipe1), true);
        assertEquals( testFavorite.addToFavorite(testRecipe2), true);

        assertThrows(InstanceAlreadyExistsException.class, () -> {
            testFavorite.addToFavorite(testRecipe1);
        });
    }

    @Test
    void testGetFavorite(){
        assertEquals(testFavorite.getFavorites(), List.of(testRecipe1, testRecipe2));
    }


}
