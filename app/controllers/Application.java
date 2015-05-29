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

	public static Result index() {
		List<Thanks> tnk=Thanks.find.where().eq("tnk_id","1").orderBy("tnk_date desc").findList();
		return ok(index.render(tnk.get(0)));
	}

	public static Result changepass(){
		return ok("新しいパスワードと確認用パスワードが違います");
	}
	public static Result password() {
		return ok(changepass.render());
	}
	public static Result board() {
		List<Thanks> thanksList = Thanks.find.all();
		List<Departments> dept=Departments.find.all();
		return ok(board.render(thanksList,dept));
	}
	public static Result cardview() {
		return ok(cardview.render());
	}
	public static Result category() {
		return ok(category.render());
	}
	public static Result changemp() {
		return ok(changemp.render());
	}
	public static Result complete() {
		return ok(complete.render());
	}
	public static Result createmp() {
		return ok(createmp.render());
	}
	public static Result example() {
		return ok(example.render());
	}

	public static Result detail() {
		return ok(detail.render());
	}
	public static Result thanks() {
		List<Departments> dept=Departments.find.all();
		List<Categories> category=Categories.find.all();
		return ok(thanks.render(dept,category));
	}
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
		newThanks.emp_id=Employees.find.all().get(0);
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
