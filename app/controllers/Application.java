package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.Employees;
import models.Thanks;
import models.Departments;
import models.Categories;
import java.util.*;

import play.data.Form;

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
		Form<Thanks> thanksForm = Form.form(Thanks.class).bindFromRequest();
		Thanks newThanks = new Thanks();
		/*Departments dept=Departments.find.where().eq("dept_id", "thanksForm.get().dept_id").findList().get(0);
		Employees emp=Employees.find.where().eq("emp_name", "thanksForm.get().ywk_name")
		.eq("dept","dept_id").findList().get(0);
		newThanks.emp2_id=emp;*/
		newThanks.help_contents=thanksForm.get().help_contents;
		newThanks.category_id=thanksForm.get().category_id;
		newThanks.tnk_point=thanksForm.get().tnk_point;
		newThanks.tnk_contents=thanksForm.get().tnk_contents;
		newThanks.tnk_date=thanksForm.get().tnk_date;
		Employees emp2=Employees.find.all().get(0);
		newThanks.emp_id=emp2;
		newThanks.save();
		return ok("登録されました");
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
        session("emp_id", loginForm.get().emp_name);
        return redirect(routes.Application.index());
    }
}
