package models;

import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import models.*;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.data.validation.*;



@Entity
public class Employees extends Model {

	@Id
	@OneToMany(mappedBy="emp_id2")
	public String emp_id;

	public String emp_name;

	@ManyToOne
	@JoinColumn(name = "dept_id")
	public Departments dept_id;

	public String password;

	public Integer job_id;

	  public static Finder<String, Employees> find = new Finder<String,Employees>(
			    String.class, Employees.class
			  );

	  public String validate() {
		    if (authenticate(emp_id, password)) {
		      return null;
		    }
		    return "Invalid user and password";
		  }


	  public Boolean authenticate(String emp_id, String password) {
		    Employees user = find.where().eq("emp_id", emp_id).findUnique();
		    return (user != null && user.password.equals(password));
		  }
}
