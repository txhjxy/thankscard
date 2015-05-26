package controllers;

import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Authentication extends Controller {
	public static Result logout() {
		return ok(login.render());
	}
	public static Result authenticate() {
		return ok(login.render());
	}
	public static Result login() {
		return ok(login.render());
	}
}
