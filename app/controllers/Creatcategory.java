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
		return ok(category.render());
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
	public static Result changecategory(){
		List<Categories> cate=Categories.find.all();
		return ok(changecategory.render(cate));
	}
	public static Result chancategory() {
		String[] params = { "category_id","category_name"};
		DynamicForm input = Form.form();
		input = input.bindFromRequest(params);
		Integer num=Integer.parseInt(input.data().get("category_id"));
		Categories cate=Categories.find.byId(num);
		cate.category_name = input.data().get("category_name");
		cate.save();
		return redirect(routes.Application.index());
	}
}
