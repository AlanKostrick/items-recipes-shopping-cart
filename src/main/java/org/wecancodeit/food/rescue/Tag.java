package org.wecancodeit.food.rescue;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	private long id;

	private String meal;

	@OneToMany(mappedBy = "tag")
	private Collection<Recipe> recipes;

	public Tag(String meal) {
		this.meal = meal;
	}

	public Tag() {

	}

	public long getId() {
		return id;
	}

	public String getMeal() {
		return meal;
	}

	public Collection<Recipe> getRecipes() {
		return recipes;
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
