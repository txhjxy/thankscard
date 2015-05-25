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

}
