package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
@Entity
public class Categories extends Model {

	@Id
	@OneToMany
    public Integer category_id;

    public String category_name;

	public static Finder<Integer, Categories> find = new Finder<Integer, Categories>(
			Integer.class, Categories.class
			);


}
