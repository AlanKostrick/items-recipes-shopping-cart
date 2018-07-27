package org.wecancodeit.food.rescue;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	Collection<Recipe> findByItemsContains(Item item);

	Collection<Recipe> findByTagAndItems(Tag tag, Item item);

	

}
