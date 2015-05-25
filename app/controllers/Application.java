package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(Thanks.find.byId(session(userid)));
    }

    public static Result changepass(){

    }
}
