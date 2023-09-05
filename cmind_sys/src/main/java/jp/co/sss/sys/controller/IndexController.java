package jp.co.sss.sys.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.sys.form.LoginForm;
import jp.co.sss.sys.repository.EmployeeRepository;

/**
 * コントローラークラス
 * @author Inoue Nami
 *
 */
@Controller
public class IndexController {

	@Autowired
	EmployeeRepository empRepository;

	/**
	 * ログイン画面を表示する
	 * @param loginForm
	 * @return login.html
	 */
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(LoginForm loginForm) {
		return "login";		
	}

	/**
	 * 入力された値を元にログイン認証し、トップ画面に遷移する
	 * @param loginForn
	 * @param req
	 * @param res
	 * @return top.html
	 */
	@RequestMapping(path = "/top", method = RequestMethod.POST)
	public String login(LoginForm loginForn, HttpServletRequest req, HttpServletResponse res, Model model) {
	    String empId = loginForn.getEmpId();
	    String password = loginForn.getPassword();
	    
	    Optional employee = empRepository.findById(empId);
	    
	    //バリデーションチェック
	    if(empId.isEmpty()) {
	        model.addAttribute("エラー", "IDが違います");
	        
	        return "login";
	    
	    } else if(password.isEmpty()) {
	        model.addAttribute("エラー", "パスワードが違います");
	        
	        return "login";
	        
	    }
	    //テーブルチェック
	    //テーブルが存在しない場合
	    if(employee == null) {
	        model.addAttribute("エラー", "IDとパスワードが違います");
	        
	        return "login";
	        
	    }
	    //テーブルが存在した場合
	        return "top";
	}
}

	
