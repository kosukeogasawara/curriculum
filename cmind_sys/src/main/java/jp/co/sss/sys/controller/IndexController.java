package jp.co.sss.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.sys.entity.Employee;
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
	 * ログアウト処理を行う
	 * 
	 */
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);//セッションを取得（存在した場合は作成しない）
	    if(session != null) {
	        session.invalidate();//セッションを無効に（破棄）
	    }
	    return "redirect:/login";
	}
	/**
	 * 入力された値を元にログイン認証し、トップ画面に遷移する
	 * @param loginForn
	 * @param req
	 * @param res
	 * @return top.html
	 */
	@RequestMapping(path = "/top", method = RequestMethod.POST)
	public String login(@Validated LoginForm loginForm, BindingResult errors, Model model) {
	    if (errors.hasErrors()) {
	        // バリデーションエラーがある場合
	        return "login";
	    }

	    String empId = loginForm.getEmpId();
	    String password = loginForm.getPassword();

	    Employee employee = empRepository.findByEmpIdAndPassword(empId, password);

	    if (employee == null) {
	        // ログインエラー：該当データが見つからない場合
	        model.addAttribute("errorMessage", "社員番号またはパスワードが違います");
	        return "login";
	    }
	   
	    //合致する情報が存在した場合、データを一覧表示
	    List<Employee> employeelist = empRepository.findAll();
        model.addAttribute("employeelist", employeelist);
	        
	        return "top";
	    }
	
	
	/**
	 * 社員一覧項目ページへ遷移
	 * @param dataExists 
	 */
	@RequestMapping(path = "/employee", method = RequestMethod.GET)
	public String employee(@ModelAttribute Employee employee, String empId, Model model) {
	    
	  //通常処理
        List<Employee> employees = empRepository.findAll();
        model.addAttribute("employees", employees);
        
	    return "employee";
	}
	
	/**
     * マイページ画面へ遷移
     * @mypage.html
     */
    @RequestMapping(path = "/mypage", method = RequestMethod.GET)
    public String myPage(Employee employee, Model model, HttpSession session) {
        
        return "mypage";
    }
	
	/**
	 * 更新画面で入力された値を元に、社員情報を更新
	 * @param updateEmployee 
	 */
	@RequestMapping(path = "/complete", method = RequestMethod.POST)
	public String updateMypage(@ModelAttribute @Validated Employee updateEmployee, BindingResult error, Model model) {
	  //入力チェック
        if(error.hasErrors()) {
            return "mypage";
        }
        
	    Employee employee = empRepository.findByEmpId(updateEmployee.getEmpId());
	    //該当データが存在しないもしくは更新ができなかった場合、error画面へ遷移
	    if(employee == null) {
	        return "error";
	    }
	    
	        // フォームから送信されたデータでエンティティを更新
	        employee.setEmpId(updateEmployee.getEmpId());
	        System.out.println("EmpId: " + updateEmployee.getEmpId());

	        employee.setEmpName(updateEmployee.getEmpName());
	        System.out.println("EmpName: " + updateEmployee.getEmpName());

	        employee.setPassword(updateEmployee.getPassword());
	        System.out.println("Password: " + updateEmployee.getPassword());
	        
	        employee.setGender(updateEmployee.getGender());
            System.out.println("Gender: " + updateEmployee.getGender());
            
	        employee.setBirthday(updateEmployee.getBirthday());
	        System.out.println("Birthday" + updateEmployee.getBirthday());
	        
            // エンティティを保存
            empRepository.save(employee);
            
            model.addAttribute("employee", employee);
	    
	    //更新が完了した場合
	    return "redirect:/complete";
	}
	
	/**
	 * 更新完了画面からマイページ画面へ
	 */
	@RequestMapping(path = "/mypage", method = RequestMethod.POST)
	public String returnMypage(Model model) {
	    return "redirect:/mypage";
	}
}

