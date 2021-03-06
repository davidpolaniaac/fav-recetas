package website.davidpolania.android.facebookrecipes.recipemain;

import website.davidpolania.android.facebookrecipes.entities.Recipe;

/**
 * Created by David Polania.
 */
public interface RecipeMainRepository {
    public final static int COUNT = 1;
    public final static String RECENT_SORT = "r";
    public final static int RECIPE_RANGE = 100000;

    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void setRecipePage(int recipePage);
}
