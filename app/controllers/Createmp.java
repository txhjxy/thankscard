package controllers;

import play.*;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

import views.html.*;

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
}
