package website.davidpolania.android.facebookrecipes.recipelist;

/**
 * Created by David Polania.
 */
public class RecipeListInteractorImpl implements RecipeListInteractor {
    private RecipeListRepository repository;

    public RecipeListInteractorImpl(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getSavedRecipes();
    }

    @Override
    public void searchFavs() {
        repository.getFavoritesRecipies();
    }
}
