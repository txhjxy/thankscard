package controllers;

import play.*;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

import views.html.*;

import models.Employees;
import models.Thanks;
import models.Departments;
import models.Categories;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import com.avaje.ebean.*;

public class Board extends Controller {
	@Security.Authenticated(Secured.class)
	public static Result board() {
		List<Thanks> thanksList = Thanks.find.all();
		List<Employees> employees=Employees.find.all();
		List<Departments> deptList=Departments.find.all();
		return ok(board.render(thanksList,employees,deptList));
	}
	@Security.Authenticated(Secured.class)
	public static Result serchboard() {
		String[] params = { "dept_id","emp_name","tnk_date","dept_id2","emp_name2","key"};
		DynamicForm input = Form.form();
		input = input.bindFromRequest(params);
		List<Employees> employees = Employees.find.all();
		List<Departments> deptList = Departments.find.all();
		List<String> strList=new ArrayList<String>();
		if(input.data().get("dept_id")!=null){
			strList.add("AND e.dept_id="+input.data().get("dept_id")+" ");
		}
		if(input.data().get("emp_name")!=null){
			strList.add("AND e.emp_name="+input.data().get("emp_name")+" ");
		}
		if(input.data().get("dept_id2")!=null){
			strList.add("AND e2.dept_id="+input.data().get("dept_id2")+" ");
		}
		if(input.data().get("emp_name2")!=null){
			strList.add("AND e2.emp_name="+input.data().get("emp_name2")+" ");
		}
		if(input.data().get("key")!=null){
			strList.add("AND (t.help_contents like "+input.data().get("key")+" or t.tnk_contents like "+input.data().get("key")+") ");
		}
		if(input.data().get("tnk_date")!=null){
			strList.add("AND t.tnk_date="+input.data().get("tnk_date")+" ");
		}
		String sql="select * from employees e,employees e2,departments d,departments d2,categories c,thanks t "
				+ "where e.emp_id=t.tnk_id "
				+ "and e2.emp_id=t.ywk_id "
				+ "and t.category_id=c.category_id ";
		for(String str:strList){
			sql=sql+str;
		}
		sql=sql+"orderby t.tnk_date desc;";
		List<SqlRow> thanksList=Ebean.createSqlQuery(sql).findList();
		return ok(serchboard.render(thanksList,employees,deptList));
	}
}