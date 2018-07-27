package org.wecancodeit.food.rescue;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeRestController {

	@Resource
	private ItemRepository itemRepo;

	@Resource
	private RecipeRepository recipeRepo;

	@RequestMapping("")
	public Iterable<Recipe> findAllRecipes() {
		return recipeRepo.findAll();
	}

	@RequestMapping("/items/{itemName}")
	public Collection<Recipe> findAllRecipesForItem(@PathVariable(value = "itemName") String itemName, Model model) {
		Item item = itemRepo.findByItemNameIgnoreCaseLike(itemName);
		return recipeRepo.findByItemsContains(item);
	}
}
