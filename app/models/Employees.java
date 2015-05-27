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
	public Integer emp_id;

	public String emp_name;

	@ManyToOne
	@JoinColumn(name = "dept_id")
	public Departments dept_id;

	public String password;

	public Integer job_id;

	  public static Finder<Long, Employees> find = new Finder<Long,Employees>(
			    Long.class, Employees.class
			  );

	  public String validate() {
		    if (authenticate(emp_name, password)) {
		      return null;
		    }
		    return "Invalid user and password";
		  }


	  public Boolean authenticate(String emp_name, String password) {
		    Employees user = find.where().eq("emp_name", emp_name).findUnique();
		    return (user != null && user.password.equals(password));
		  }
}