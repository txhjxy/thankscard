package controllers;

import play.*;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

import views.html.*;

import models.Employees;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class Changepass extends Controller {
	@Security.Authenticated(Secured.class)
	public static Result password() {
		return ok(changepass.render());
	}
	@Security.Authenticated(Secured.class)
	public static Result changepass(){
		String[] params = { "password","pass"};
		DynamicForm input = Form.form();
		input = input.bindFromRequest(params);
		Employees emp=Employees.find.byId(session("emp_id"));
		emp.password=input.data().get("pass");
		emp.update();
		return redirect(routes.Application.index());

	}
}
