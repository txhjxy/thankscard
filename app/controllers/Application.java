package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.Thanks;
import java.util.*;

public class Application extends Controller {

	public static Result index() {
		List<Thanks> tnk=Thanks.find.where().eq("tnk_id","2").orderBy("tnk_date desc").findList();
		return ok(index.render(tnk.get(0)));
	}

	public static Result changepass(){
		return ok("新しいパスワードと確認用パスワードが違います");
	}
	public static Result password() {
		return ok(changepass.render());
	}
	public static Result board() {
		return ok(board.render());
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
		return ok(thanks.render());
	}

	public static Result addthanks(){
		return ok(thanks.render());
	}


}
