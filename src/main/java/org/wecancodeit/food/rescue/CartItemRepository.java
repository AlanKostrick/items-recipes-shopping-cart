package org.wecancodeit.food.rescue;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	Optional<CartItem> findByItem(Item item);

}
