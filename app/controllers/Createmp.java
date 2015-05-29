package controllers;

import play.*;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

import views.html.*;

import models.Employees;
import models.Thanks;
import models.Departments;
import models.Categories;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class Createmp {
	@Security.Authenticated(Secured.class)
	public static Result createmp() {
		List<Departments> dept=Departments.find.all();
		return ok(createmp.render(dept));
	}
	@Security.Authenticated(Secured.class)
	public static Result addemp() {
		return ok(createmp.render());
	}
	@Security.Authenticated(Secured.class)
	public static Result complete() {
		return ok(createmp.render());
	}
}
