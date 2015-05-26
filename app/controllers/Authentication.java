package controllers;

import play.data.Form;
import play.mvc.*;
import views.html.authentication.*;
public class Authentication extends Controller {

	public static Result login() {
		return ok(login.render(Form.form(Employees.class)));
		if(session(emp_id)==null){
        	return ok(login.render(Form.form(Employees.class)));
		}
		return ok(index.render(Thanks.find.byId(session(emp_id)));
    }

    public static Result authenticate() {
        Form<User> loginForm = Form.form(Employees.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        }
        session().clear();
        session("login", loginForm.get().emp_id);
        return ok(index.render(Thanks.find.byId(session(emp_id)));
    }
    public static Result thanks() {

        return ok(thanks.render());
    }
}