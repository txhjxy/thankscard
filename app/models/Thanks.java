package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity
public class Thanks extends Model {

	@Id
    public Integer tnkcard_id;
	@ManyToOne
	@JoinColumn(name = "tnk_id")
	public Employees emp_id;

	public String help_contents;

	public String tnk_contents;
	@ManyToOne
	public Categories category_id;
	@ManyToOne
	@JoinColumn(name = "ywk_id")
	public Employees emp_id2;

	public Date tnk_date;

	public Integer tnk_point;
}
