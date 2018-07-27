package org.wecancodeit.food.rescue;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(FoodRescueController.class)
public class FoodRescueControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ItemRepository itemRepo;
	
	@MockBean
	private RecipeRepository recipeRepo;
	
	@MockBean
	private TagRepository tagRepo;
	
	@Mock
	private Item item;
	
	@Mock
	private Recipe recipe;
	
	@Mock
	private Tag tag;
	
	@Test
	public void shouldRouteToSingleItemView() throws Exception {
		long itemId = 1;
		when(itemRepo.findById(itemId)).thenReturn(Optional.of(item));
		mvc.perform(get("/item?id=1")).andExpect(view().name(is("item")));
	}
	
	@Test
	public void shouldBeOkForSingleItem() throws Exception {
		long itemId = 1;
		when(itemRepo.findById(itemId)).thenReturn(Optional.of(item));
		mvc.perform(get("/item?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllItemsView() throws Exception {
		mvc.perform(get("/show-items")).andExpect(view().name(is("items")));
	}
	
	@Test
	public void shouldBeOkForAllItems() throws Exception {
		mvc.perform(get("/show-items")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToSingleRecipeView() throws Exception {
		long recipeId = 1;
		when(recipeRepo.findById(recipeId)).thenReturn(Optional.of(recipe));
		mvc.perform(get("/recipe?id=1")).andExpect(view().name(is("recipe")));
	}
	
	@Test
	public void shouldBeOkForSingleRecipe() throws Exception {
		long recipeId = 1;
		when(recipeRepo.findById(recipeId)).thenReturn(Optional.of(recipe));
		mvc.perform(get("/recipe?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllRecipesView() throws Exception {
		mvc.perform(get("/show-recipes")).andExpect(view().name(is("recipes")));
	}
	
	@Test
	public void shouldBeOkForAllRecipes() throws Exception {
		mvc.perform(get("/show-recipes")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToSingleTagView() throws Exception {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tag")));
	}
	
	@Test
	public void shouldBeOkForSingleTag() throws Exception {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllTagsView() throws Exception {
		mvc.perform(get("/show-tags")).andExpect(view().name(is("tags")));
	}
	
	@Test
	public void shouldBeOkForAllTags() throws Exception {
		mvc.perform(get("/show-tags")).andExpect(status().isOk());
	}

}
