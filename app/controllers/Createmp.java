package controllers;

import play.*;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

import views.html.*;

import models.Thanks;
import models.Categories;
import models.Employees;
import models.Departments;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class Createmp extends Controller {
	@Security.Authenticated(Secured.class)
	public static Result createmp() {
		List<Departments> dept=Departments.find.all();
		return ok(createmp.render(dept));
	}

	@Security.Authenticated(Secured.class)
	public static Result complete() {
		Employees newEmp =new Employees();
		String[] params = { "password","dept_id","emp_name","emp_id","job_id"};
		DynamicForm input = Form.form();
		input = input.bindFromRequest(params);
		newEmp.password = input.data().get("password");
		newEmp.dept_id =Departments.find.byId(Integer.parseInt(input.data().get("dept_id")));
		newEmp.emp_name = input.data().get("emp_name");
		newEmp.emp_id = input.data().get("emp_id");
		newEmp.job_id = Integer.parseInt(input.data().get("job_id"));
		newEmp.save();
		return ok(complete.render(newEmp));
	}
	@Security.Authenticated(Secured.class)
	public static Result changemp() {
		List<Departments> dept=Departments.find.all();
		return ok(changemp.render(dept));
	}
	@Security.Authenticated(Secured.class)
	public static Result change() {
		String[] params = { "dept_id","emp_name","dept_id2","emp_name2","job_id"};
		DynamicForm input = Form.form();
		input = input.bindFromRequest(params);
		Departments dept=Departments.find.byId(Integer.parseInt(input.data().get("dept_id")));
		Employees emp=Employees.find.where().eq("emp_name",input.data().get("emp_name")).eq("dept_id",dept).findList().get(0);
		emp.emp_name=input.data().get("emp_name2");
		emp.dept_id=Departments.find.byId(Integer.parseInt(input.data().get("dept_id2")));
		emp.job_id=Integer.parseInt(input.data().get("job_id"));
		emp.update();
		return redirect(routes.Application.index());
	}
}
