package controllers;

import play.data.Form;
import play.mvc.*;
import views.html.authentication.*;
public class Authentication extends Controller {

	public static class Login {
		public Integer userid;
		public String password;

		public String validate() {
			if (authenticate(userid, password)) {
				return null;
			}
			return "ユーザーIDまたはパスワードが間違っています";
		}

		private Boolean authenticate(String userid, String password) {
			return Employees.authenticate(userid, password);
		}
	}

	public static Form<Login> loginForm = Form.form(Login.class);

	public static Result index() {
		if (session("login") != null) {
			return ok("あなたは既にログインしています");
		}
		return ok(index.render(loginForm));
	}
	public static Result authenticate() {
		Form<Login> form = loginForm.bindFromRequest();

		if (form.hasErrors()) {
			return badRequest(index.render(form));
		} else {
			Login login = form.get();
			session("login", login.userid);
			return ok(routes.Application.index.render(Thanks.find.byId(session(userid)));
		}
	}
	public static Result logout() {
	    session().clear();
	    return redirect(routes.Authentication.index());
	}
}