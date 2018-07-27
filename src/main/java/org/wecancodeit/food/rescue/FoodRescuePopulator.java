package org.wecancodeit.food.rescue;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FoodRescuePopulator implements CommandLineRunner {

	@Resource
	private ItemRepository itemRepo;
	
	@Resource
	private RecipeRepository recipeRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Item avocado = new Item("Avocado");
		Item bananas = new Item("Bananas");
		Item bread = new Item("Bread");
		Item cheese = new Item("Cheese");
		Item chickenBreast = new Item("Chicken Breast");
		Item eggs = new Item("Eggs");
		Item groundBeef = new Item("Ground Beef");
		Item lettuce = new Item("Lettuce");
		Item milk = new Item("Milk");
		Item potato = new Item("Potato");
		Item salmon = new Item("Salmon");
		Item tortillas = new Item("Tortillas");
		itemRepo.save(avocado);
		itemRepo.save(bananas);
		itemRepo.save(bread);
		itemRepo.save(cheese);
		itemRepo.save(chickenBreast);
		itemRepo.save(eggs);
		itemRepo.save(groundBeef);
		itemRepo.save(lettuce);
		itemRepo.save(milk);
		itemRepo.save(potato);
		itemRepo.save(salmon);
		itemRepo.save(tortillas);
		
		Tag breakfast = new Tag("Breakfast");
		Tag dinner = new Tag("Dinner");
		Tag lunch = new Tag("Lunch");
		Tag snack = new Tag("Snack");
		tagRepo.save(breakfast);
		tagRepo.save(dinner);
		tagRepo.save(lunch);
		tagRepo.save(snack);
		
		Recipe avocadoToast = new Recipe("Strawberry Balsamic Avocado Toast", "Toss sliced strawberries with a drizzle of balsamic vinegar, then pile on top of your avocado toast! The unexpected combination of strawberries and avocados works really well, especially with the rich tartness of balsamic vinegar.", 
				"/images/bread.jpg", breakfast, avocado, bread);
		recipeRepo.save(avocadoToast);
		
		Recipe croutons = new Recipe("Croutons", 
				"1. Preheat oven to 350 degrees F (175 degrees C). " + 
				"2. Remove crusts from stale bread slices. Brush bread on both sides with melted butter. Cut bread slices up into small cubes. Sprinkle with garlic salt (if desired). Arrange cubes on an ungreased cookie sheet. " + 
				"3.Bake at 350 degrees F (175 degrees C) for 15 minutes or until browned. Let cool. Store croutons in a covered container or plastic bag. Serve in soups or salads.", "/images/bread.jpg", snack, bread);
		recipeRepo.save(croutons);
		
		Recipe friedBananas = new Recipe("Pan Fried Bananas", "1. Combine the cinnamon, butter and honey in a frying pan over medium-high heat until melted and combined. " + 
				"2. Add the banana slices and cook for 4 minutes until the bottoms start to caramelize and turn golden brown. Adjust the temperature and cooking time as needed so that they don't burn. " + 
				"3. Flip all the banana pieces and cook for another 4 minutes until the other side is golden brown. " + 
				"4. Serve immediately over ice cream, yogurt, pancakes or french toast.", "/images/bread.jpg", snack, bananas);
		
		Recipe grilledCheese = new Recipe("Grilled Cheese", "Preheat skillet over medium heat. Generously butter one side of a slice of bread. Place bread butter-side-down onto skillet bottom and add 1 slice of cheese. Butter a second slice of bread on one side and place butter-side-up on top of sandwich. Grill until lightly browned and flip over; continue grilling until cheese is melted. Repeat with remaining 2 slices of bread, butter and slice of cheese.",
				"/images/bread.jpg", lunch, bread, cheese);
		recipeRepo.save(grilledCheese);
		
		Recipe hashbrownWaffle = new Recipe("Egg & Cheese Hashbrown Waffle", "1. Heat waffle iron on the medium-high setting. Spray each side generously with non-stick cooking spray or brush with melted butter. " + 
				"2. In a medium-sized mixing bowl, whisk together eggs and milk. Stir in potatoes, cheese, chives and season with 1/2 teaspoon salt and 1/4 teaspoon pepper. " + 
				"3. Depending on the size of your waffle iron, scoop a layer of the potato mixture onto the surface (for the round waffle maker, I used about 1 cup of the mixture). Spread to about 1/2 inch from the edges and close the waffle iron. Cook for about 5 minutes, checking every few minutes to avoid burning. When the entire waffle is golden brown in color, carefully remove from the waffle iron with a fork or tongs. " + 
				"4. *Tip: Turn your oven to the \"warm\" function and place finished waffles on a baking sheet in the oven to keep warm which the rest are cooking.", "/images/bread.jpg", breakfast, potato, eggs, cheese);
		
		Recipe lettuceWrap = new Recipe("Chicken, Bacon, Avocado Ranch Lettuce Wrap", "1. Start by seasoning the chicken with salt and pepper, then grill them (you could do stovetop as well). " + 
				"2. Meanwhile, bake your bacon on a foil lined cookie sheet at 420°. Start checking for doneness at 15 minutes but it will likely take closer to 20. " + 
				"3. I had used ends and pieces so they came already chopped, but if you use regular slab bacon crumble it up after it's cooked. " + 
				"4. When the chicken is done, cut it up into bite sized pieces and mix the cilantro in. " + 
				"5. Wash the lettuce, and carefully remove each leaf so that it creates a boat, or cup to put the yumminess in. " + 
				"6. Now set all your ingredients out so people can build their own lettuce wrap. Squeeze the half a lime over the avocado, tomato, and chicken. " + 
				"7. Take the lettuce leaf, fill it with the bacon, avocado, tomato, and chicken. Then drizzle with ranch. " + 
				"8. Eat it like a taco and enjoy!", "/images/bread.jpg", lunch, lettuce, chickenBreast, avocado);
		
		Recipe scrambledEggs = new Recipe("Scrambled Eggs", "1. Beat eggs, milk, salt and pepper in medium bowl until blended. " + 
				"2. Heat butter in large nonstick skillet over medium heat until hot. Pour in egg mixture. As eggs begin to set, gently pull the eggs across the pan with a spatula, forming large soft curds. " + 
				"3.Continue cooking – pulling, lifting and folding eggs – until thickened and no visible liquid egg remains. Do not stir constantly. Remove from heat. Serve immediately.", 
				"/images/bread.jpg", breakfast, eggs, cheese);
		recipeRepo.save(scrambledEggs);
		
		Recipe salmonBurger = new Recipe("Grilled Salmon Burgers with Avocado Slasa", "1. Skin and chop salmon fillet. " + 
				"2. Put in large bowl. " + 
				"3. Add panko, poblano,egg, green onion, lemon or lime juice, salt and pepper. " + 
				"4. Mix well. " + 
				"(Hint: make a cross on top of the salmon mixture before making into patties to ensure consistent size) " + 
				"5. Heat indoor grill pan or outdoor BBQ to medium high heat. " + 
				"6. Cook for about 4 minutes on each side until cooked through. " + 
				"7. Avocado Salsa " + 
				"8. Combine all ingredients in medium bowl. " + 
				"9. Top burgers with salsa. Serve with or without bun.", "/images/bread.jpg", dinner, avocado, salmon);
		recipeRepo.save(salmonBurger);
		
		Recipe tacos = new Recipe("Tacos", "1. Heat oven to 250°F. In medium skillet, brown ground beef and onion over medium heat for 8 to 10 minutes or until beef is thoroughly cooked, stirring frequently. Drain. " + 
				"2. Stir in chili powder, salt, garlic powder and tomato sauce. Reduce heat to low; cover and simmer 10 minutes. " + 
				"3. Meanwhile, place taco shells on ungreased cookie sheet. Heat at 250°F. for 5 minutes. " + 
				"4. To assemble tacos, layer beef mixture, cheese, lettuce and tomatoes in each taco shell. Serve with salsa; top with sour cream.", "/images/bread.jpg", dinner, groundBeef, tortillas);
		recipeRepo.save(tacos);
		
		Recipe teriyakiChicken = new Recipe("Easy Teriyaki Chicken", "1. Season the chicken with a salt and pepper, to taste. Set aside. " + 
				"2. Heat the oil in a heavy bottomed frying pan or wok over medium heat. " + 
				"3. You don't want to start the chicken off over too high a heat otherwise it will not cook through before burning. " + 
				"4. Add chicken and saute, stirring occasionally, until the chicken is no longer pink on the inside, and golden brown on the sides. " + 
				"5. Once the chicken is ready to go, pour the soy sauce, brown sugar, and sesame seeds over it, then toss to combine until the chicken is evenly coated. " + 
				"6. Continue cooking, stirring frequently until the sauce reaches a simmer and thickens. " + 
				"7. Remove from heat, and serve immediately with cooked white rice and steamed broccoli, garnished with thinly-sliced green onions if desired. " + 
				"8. Drizzle the remaining teriyaki sauce over if desired. " + 
				"9. * The magic is not over cooking the chicken during the sautéing process. It gives the glaze mixture more time to cook down. Keep stiring the chicken while its glazing. It will help it not to burn.",
				"/images/bread.jpg", dinner, chickenBreast); 
		recipeRepo.save(teriyakiChicken);
	}

}
