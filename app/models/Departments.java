package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
@Entity
public class Departments extends Model {

	@Id
	@OneToMany
    public Integer dept_id;

    public String dept_name;

	public static Finder<Integer, Departments> find = new Finder<Integer, Departments>(
			Integer.class, Departments.class
			);

}
