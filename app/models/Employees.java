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



}