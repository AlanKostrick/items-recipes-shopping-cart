package org.wecancodeit.food.rescue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private ItemRepository itemRepo;

	@Resource
	private RecipeRepository recipeRepo;

	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private CartItemRepository cartRepo;

	@Test
	public void shouldSaveAndLoadAnItem() {
		Item item = new Item("Item Name");
		itemRepo.save(item);

		long itemId = item.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Item> result = itemRepo.findById(itemId);
		item = result.get();

		assertThat(item.getItemName(), is("Item Name"));
		assertTrue(result.isPresent());
	}

	@Test
	public void shouldGenerateItemId() {
		Item item = new Item("Item Name");
		itemRepo.save(item);

		long itemId = item.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(itemId, is(greaterThan(0L)));
	}

	@Test
	public void shouldBeAbleToReturnAFullInventory() {
		Item item1 = new Item("Item1 Name");
		itemRepo.save(item1);

		Item item2 = new Item("Item2 Name");
		itemRepo.save(item2);

		entityManager.flush();
		entityManager.clear();

		Iterable<Item> items = itemRepo.findAll();

		assertThat(items, containsInAnyOrder(item1, item2));
	}

	@Test
	public void shouldBeAbleToRemoveAnItem() {
		Item item = new Item("Item Name");
		itemRepo.save(item);

		long itemId = item.getId();

		entityManager.flush();
		entityManager.clear();

		itemRepo.deleteById(itemId);

		Optional<Item> result = itemRepo.findById(itemId);

		assertFalse(result.isPresent());
	}

	@Test
	public void shouldFindItemByName() {
		Item item = new Item("Item Name");
		itemRepo.save(item);

		String itemName = item.getItemName();

		entityManager.flush();
		entityManager.clear();

		Item result = itemRepo.findByItemName(itemName);

		assertThat(result, is(item));
	}

	@Test
	public void shouldSaveAndLoadATag() {
		Tag tag = new Tag("Meal");
		tagRepo.save(tag);

		long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();

		assertThat(tag.getMeal(), is("Meal"));
		assertTrue(result.isPresent());
	}

	@Test
	public void shouldGenerateTagId() {
		Tag tag = new Tag("");
		tagRepo.save(tag);

		long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(tagId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadARecipe() {
		Item item1 = new Item("bread");
		Item item2 = new Item("cheese");
		Tag tag = new Tag("lunch");

		tagRepo.save(tag);
		itemRepo.save(item1);
		itemRepo.save(item2);

		Recipe recipe = new Recipe("Recipe Name", "Instructions", "Image", tag, item1, item2);
		recipeRepo.save(recipe);

		long recipeId = recipe.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Recipe> result = recipeRepo.findById(recipeId);

		recipe = result.get();

		assertThat(recipe.getRecipeName(), is("Recipe Name"));
		assertTrue(result.isPresent());

		assertThat(recipe.getInstructions(), is("Instructions"));

		assertThat(recipe.getImage(), is("Image"));

	}

	@Test
	public void shouldEstablishRecipeToItemRelationship() {
		Item item = new Item("");
		itemRepo.save(item);

		Tag tag = new Tag("");
		tagRepo.save(tag);

		Recipe recipe = new Recipe("Name", "Instructions", "Image", tag, item);
		recipeRepo.save(recipe);
		long recipeId = recipe.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Recipe> result = recipeRepo.findById(recipeId);
		recipe = result.get();

		assertThat(recipe.getItems(), contains(item));

	}

	@Test
	public void shouldEstablishItemToRecipeRelationship() {
		Item item = new Item("");
		itemRepo.save(item);

		Tag tag = new Tag("");
		tagRepo.save(tag);

		Recipe recipe = new Recipe("Name", "Instructions", "Image", tag, item);
		recipeRepo.save(recipe);
		long itemId = item.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Item> result = itemRepo.findById(itemId);
		item = result.get();

		assertThat(item.getRecipes(), contains(recipe));
	}
	
	@Test
	public void shouldEstablishRecipeToTagRelationship() {
		Item item = new Item("");
		itemRepo.save(item);

		Tag tag = new Tag("");
		tagRepo.save(tag);

		Recipe recipe = new Recipe("Name", "Instructions", "Image", tag, item);
		recipeRepo.save(recipe);
		long recipeId = recipe.getId();

		entityManager.flush();
		entityManager.clear();
		
		Optional<Recipe> result = recipeRepo.findById(recipeId);
		recipe = result.get();

		assertThat(recipe.getTag(), is(tag));
	}
	
	@Test
	public void shouldEstablishTagToRecipeRelationship() {
		Item item = new Item("");
		itemRepo.save(item);

		Tag tag = new Tag("");
		tagRepo.save(tag);

		Recipe recipe1 = new Recipe("Name1", "Instructions", "Image", tag, item);
		Recipe recipe2 = new Recipe("Name2", "Instructions", "Image", tag, item);
		recipeRepo.save(recipe1);
		recipeRepo.save(recipe2);
		long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();

		assertThat(tag.getRecipes(), containsInAnyOrder(recipe1, recipe2));
	}
	
	@Test
	public void shouldFindAllRecipesForItemAndMeal() {
		Item cheese = itemRepo.save(new Item("cheese"));
		Item eggs = itemRepo.save(new Item("eggs"));
		
		Tag breakfast = tagRepo.save(new Tag("breakfast"));
		Tag lunch = tagRepo.save(new Tag("lunch"));
		
		Recipe recipe1 = new Recipe("Name1", "Instructions", "Image", breakfast, cheese, eggs);
		Recipe recipe2 = new Recipe("Name2", "Instructions", "Image", breakfast, cheese);
		Recipe recipe3 = new Recipe("Nam3", "Instructions", "Image", lunch, cheese);
		
		recipeRepo.save(recipe1);
		recipeRepo.save(recipe2);
		recipeRepo.save(recipe3);
		
		Collection<Recipe> breakfastCheeseRecipes = recipeRepo.findByTagAndItems(breakfast, cheese);
		
		assertThat(breakfastCheeseRecipes, containsInAnyOrder(recipe1, recipe2));
	
	}
	
	@Test
	public void shouldSaveAndLoadCartItem() {
		
		Item item = itemRepo.save(new Item("cheese"));
				
		CartItem cart = cartRepo.save(new CartItem(item));
		long cartItemId = cart.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<CartItem> cartItemOptional = cartRepo.findById(cartItemId);
		CartItem resultCartItem = cartItemOptional.get();
		
		assertThat(resultCartItem.getItem().getItemName(), is("cheese"));
	}
}
