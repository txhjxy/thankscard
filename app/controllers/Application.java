package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render(Thanks.find.byId(session(userid))));
	}

	public static Result changepass(){
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		if(validation.equals(params.get("password")[0], params.get("pass")[0])){
			Employees emp=Employees.find.byId(session(userid));
			emp.password = Form.form(Employees.class).bindFromRequest().get();
			return ok(Thanks.find.byId(session(userid)));
		}
		return ok("新しいパスワードと確認用パスワードが違います")
	}
}
