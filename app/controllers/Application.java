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

public class Application extends Controller {
	@Security.Authenticated(Secured.class)
	public static Result index() {
		List<Thanks> tnk=Thanks.find.where().eq("ywk_id",session("emp_id")).orderBy("tnk_date desc").findList();
		Employees emp=Employees.find.byId(session("emp_id"));
		if(tnk.size()==0){
			String str=new SimpleDateFormat("yyyy-MM-dd").format(Thanks.find.byId(0).tnk_date);
			return ok(index.render(Thanks.find.byId(0),emp,str));
		}else{
			String str=new SimpleDateFormat("yyyy-MM-dd").format(tnk.get(0).tnk_date);
			return ok(index.render(tnk.get(0),emp,str));
		}
	}
	@Security.Authenticated(Secured.class)
	public static Result cardview() {
		List<Thanks> tnk =Thanks.find.where().eq("tnk_id",session("emp_id")).orderBy("tnk_date desc").findList();

		return ok(cardview.render(tnk));
	}
	@Security.Authenticated(Secured.class)
	public static Result example() {
		List<Thanks> thanksList = Thanks.find.orderBy("tnk_point desc").findList();
		List<Departments> dept=Departments.find.all();
		return ok(example.render(thanksList,dept));
	}
	@Security.Authenticated(Secured.class)
	public static Result detail() {
		return ok(detail.render());
	}
	@Security.Authenticated(Secured.class)
	public static Result thanks() {
		List<Departments> dept=Departments.find.all();
		List<Categories> category=Categories.find.all();
		return ok(thanks.render(dept,category));
	}
	@Security.Authenticated(Secured.class)
	public static Result creatthanks() {
		Thanks newThanks =new Thanks();
		String[] params = { "dept_id","emp_","help_contents","category_id",
				"tnk_point","tnk_contents","tnk_date"};
		DynamicForm input = Form.form();
		input = input.bindFromRequest(params);
		newThanks.help_contents = input.data().get("help_contents");
		Integer num=Integer.parseInt(input.data().get("category_id"));
		newThanks.category_id = Categories.find.where().eq("category_id", num).findList().get(0);
		newThanks.tnk_point = Integer.parseInt(input.data().get("tnk_point"));
		newThanks.tnk_contents = input.data().get("tnk_contents");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			newThanks.tnk_date = format.parse(input.data().get("tnk_date"));
		} catch (ParseException e) {
			return ok("もう一度入力してください");
		}
		num=Integer.parseInt(input.data().get("dept_id"));
		Departments dept=Departments.find.where().eq("dept_id", num).findList().get(0);
		Employees emp=Employees.find.where().eq("emp_name", input.data().get("emp_name"))
				.eq("dept_id",dept).findList().get(0);
		newThanks.emp_id2 =emp;
		newThanks.emp_id=Employees.find.byId(session("emp_id"));
		newThanks.save();
		return redirect(routes.Application.index());
	}
	public static Result login() {
		return ok(login.render(Form.form(Employees.class)));
	}
	public static Result authenticate() {
		Form<Employees> loginForm = Form.form(Employees.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		}
		session().clear();
		session("emp_id", loginForm.get().emp_id);
		return redirect(routes.Application.index());
	}
	public static Result logout() {
		session().clear();
		return ok(login.render(Form.form(Employees.class)));
	}
}

