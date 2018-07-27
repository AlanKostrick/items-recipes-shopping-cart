package org.wecancodeit.food.rescue;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recipe {

	@Id
	@GeneratedValue
	private long id;

	private String recipeName;
	@Lob
	private String instructions;
	private String imagePath;

	@JsonIgnore
	@ManyToMany
	private Collection<Item> items;

	@JsonIgnore
	@ManyToOne
	private Tag tag;

	public Recipe(String recipeName, String instructions, String imagePath, Tag tag, Item... items) {
		this.recipeName = recipeName;
		this.instructions = instructions;
		this.imagePath = imagePath;
		this.tag = tag;
		this.items = new HashSet<>(Arrays.asList(items));
	}

	public Recipe() {

	}

	public long getId() {

		return id;
	}

	public String getRecipeName() {

		return recipeName;
	}

	public String getInstructions() {

		return instructions;
	}

	public String getImage() {

		return imagePath;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public Tag getTag() {
		return tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void removeItem(Item item) {
		items.remove(item);

	}

}
