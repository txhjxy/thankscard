package controllers;

import play.*;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

import views.html.*;

import models.Categories;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class Creatcategory extends Controller {
	@Security.Authenticated(Secured.class)
	public static Result category() {
		List<Categories> cate=Categories.find.all();
		return ok(category.render(cate));
	}

	@Security.Authenticated(Secured.class)
	public static Result creatcategory() {
		Categories newCate =new Categories();
		String[] params = { "category_name"};
		DynamicForm input = Form.form();
		input = input.bindFromRequest(params);
		newCate.category_name = input.data().get("category_name");
		newCate.save();
		return redirect(routes.Application.index());
	}
}
