/*package app.foodapp.model.node;

import app.foodapp.model.recipe.Recipe;
import org.junit.jupiter.api.Test;

import javax.management.InstanceAlreadyExistsException;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class FavoriteTest {

   /* Favorite testFavorite = new Favorite();
    Recipe testRecipe1 = new Recipe("1620", null, null,8,30);
    Recipe testRecipe2 = new Recipe("1400", null, null, 9, 45);

    @Test
    void testAddToFavorite() throws InstanceAlreadyExistsException {
        assertEquals(testFavorite.addToFavorite(testRecipe1), true);
        assertEquals(testFavorite.addToFavorite(testRecipe2), true);

        assertThrows(InstanceAlreadyExistsException.class, () -> {
            testFavorite.addToFavorite(testRecipe1);
        });
    }

    @Test
    void testIsFavorite() throws InstanceAlreadyExistsException {
        testFavorite.addToFavorite(testRecipe1);
        assertEquals(testFavorite.recipeIsInFavoriteList(testRecipe1), true);
        assertEquals(testFavorite.recipeIsInFavoriteList(testRecipe2), false);
    }

    @Test
    void testGetFavorites() throws InstanceAlreadyExistsException {
        testFavorite.addToFavorite(testRecipe1);
        testFavorite.addToFavorite(testRecipe2);
        assertEquals(testFavorite.getFavoriteList(), List.of(testRecipe1, testRecipe2));
    }

    @Test
    void testRemoveFromFavorite() throws InstanceAlreadyExistsException {
        testFavorite.addToFavorite(testRecipe1);
        testFavorite.addToFavorite(testRecipe2);
        assertEquals(testFavorite.removeFromFavorite(testRecipe1), true);
        assertThrows(NoSuchElementException.class, () -> {
            testFavorite.removeFromFavorite(testRecipe1);
        });
    }

    @Test
    void testGetFavorite() throws InstanceAlreadyExistsException {
        testFavorite.addToFavorite(testRecipe1);
        testFavorite.addToFavorite(testRecipe2);
        assertEquals(testFavorite.getRecipe(0), testRecipe1);
        assertEquals(testFavorite.getRecipe(1), testRecipe2);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
           testFavorite.getRecipe(-1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            testFavorite.getRecipe(3);
        });
    }

    @Test
    void testSaveFavorites() throws InstanceAlreadyExistsException {
        assertEquals(testFavorite.addToFavorite(testRecipe1), true);
    }

    @Test
    void testReadSavedFavorites() throws InstanceAlreadyExistsException {
        testFavorite.addToFavorite(testRecipe1);
        testFavorite.addToFavorite(testRecipe2);

        Favorite testFavorite2 = new Favorite();
        testFavorite2.readSavedFavorites();
        assertEquals(testRecipe1.equals(testFavorite2.getRecipe(0)), true);
    }*/

    /*@Test
    void testIsFavoritesSavedExists(){
        assertEquals(testFavorite.favoriteListIsSaved(), true);
    }
}*/
