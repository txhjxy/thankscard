package controllers;

import play.data.Form;
import play.mvc.*;
import views.html.authentication.*;
public class Authentication extends Controller {

	public static Result login() {
        return ok(login.render(Form.form(Employees.class)));
    }

    public static Result authenticate() {
        Form<User> loginForm = Form.form(Employees.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        }
        session().clear();
        session("login", loginForm.get().emp_id);
        return ok(index.render(Thanks.find.byId(session(userid)));
    }
}