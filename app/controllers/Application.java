package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import java.util.Map;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public static Result login() {
        return ok(login.render());
    }
    public static Result mainpage() {
        return ok(mainpage.render());
    }
    public static Result changepass() {
        return ok(changepass.render());
    }
    public static Result board() {
        return ok(board.render());
    }
}