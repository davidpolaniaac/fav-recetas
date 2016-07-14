package website.davidpolania.android.facebookrecipes.recipelist;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.Arrays;
import java.util.List;

import website.davidpolania.android.facebookrecipes.entities.Recipe;
import website.davidpolania.android.facebookrecipes.entities.Recipe_Table;
import website.davidpolania.android.facebookrecipes.lib.EventBus;
import website.davidpolania.android.facebookrecipes.recipelist.events.RecipeListEvent;

/**
 * Created by David Polania.
 */
public class RecipeListRepositoryImpl implements RecipeListRepository {
    private EventBus eventBus;

    public RecipeListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        FlowCursorList<Recipe> storedRecipes = new FlowCursorList<Recipe>(true, Recipe.class);
        post(RecipeListEvent.READ_EVENT, storedRecipes.getAll());
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post(RecipeListEvent.UPDATE_EVENT);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT, Arrays.asList(recipe));
    }

    @Override
    public void getFavoritesRecipies() {
        List<Recipe> recipes = new Select().from(Recipe.class).where(Recipe_Table.favorite.is(true)).queryList();
        post(RecipeListEvent.READ_EVENT,recipes);
    }

    private void post(int type, List<Recipe> recipes) {
        RecipeListEvent event = new RecipeListEvent();
        event.setRecipes(recipes);
        event.setType(type);
        eventBus.post(event);
    }

    private void post(int type) {
        post(type, null);
    }

}
