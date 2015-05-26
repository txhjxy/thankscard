package models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Employees extends Model {

	@Id
	@OneToMany(cascade=CascadeType.ALL,mappedBy="emp_id2")
	public Integer emp_id;

	public String emp_name;

	public Departments dept_id;

	public String password;

	public Integer job_id;

	public String validate() {
		if (authenticate(emp_id, password)) {
			return null;
		}
		return "IDまたはパスワードが違います";
	}

	private Boolean authenticate(String name, String password) {
		User user = find.where().eq("name", name).eq("password", password).findUnique();
		return (user != null);
	}

}