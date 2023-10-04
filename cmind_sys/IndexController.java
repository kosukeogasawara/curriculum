package jp.co.sss.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public String login(LoginForm loginForn, HttpServletRequest req, HttpServletResponse res, Model model, HttpServletRequest request, HttpSession session) {
	    String empId = loginForn.getEmpId();
	    String password = loginForn.getPassword();
	    
	    Employee employee = empRepository.findByEmpIdAndPassword(empId, password);
	    session.setAttribute("loginId", empId);
	    
	    //入力チェック
	    if(empId.isEmpty()) {
	        model.addAttribute("errorMessage", "社員番号は入力必須項目です。");
	        return "login";
	    
	    } else if(empId.length() > 5) {
	        model.addAttribute("errorMessage", "社員番号は5文字以内で入力してください。");
	        return "login";
	        
	    } else if(password.isEmpty()) {
	        model.addAttribute("errorMessage", "パスワードは入力必須項目です。");
	        return "login";
	        
	    } else if(password.length() > 16) {
	        model.addAttribute("errorMessage", "パスワードは16文字以内で入力してください。");
	        return "login";
	    }
	    
	    //テーブルチェック
	    if(employee == null) {
	        model.addAttribute("errorMessage", "社員番号またはパスワードが違います");
	        
	        return "login";
	        
	    }
	    //テーブルが存在した場合
	        return "redirect:/top";
	}
	
	/**
	 * ログイン後に社員一覧を表示
	 * @param  
	 * 
	 */
	@RequestMapping(path = "/top", method = RequestMethod.GET)
	public String employee(String empId, String password, Model model, HttpSession session) {
	    
	    List<Employee> employeelist = empRepository.findAll();
	    model.addAttribute("employeelist", employeelist);
	    
	    Employee employee = null;
	    for (Employee emp : employeelist) {
	        if (emp.getEmpId().equals(empId)) {
	            employee = emp; // 条件に一致するEmployeeオブジェクトを代入
	            break; // 見つかったらループを終了
	        }
	    }
 	    
	    if(employee != null) {
	    model.addAttribute("empName", employee.getEmpName());
	    }
	    
	    return "top";
	}
	
	/**
	 * 社員一覧項目ページへ遷移
	 * @param dataExists 
	 */
	@RequestMapping(path = "/employee", method = RequestMethod.GET)
	public String employee(@ModelAttribute @Validated Employee employee, String empId, BindingResult bindingResult, Model model) {
	    
	  //通常処理
        List<Employee> employees = empRepository.findAll();
        model.addAttribute("employees", employees);
        
        Employee employee2 = null;
        for (Employee emp : employees) {
            if (emp.getEmpId().equals(empId)) {
                employee2 = emp; // 条件に一致するEmployeeオブジェクトを代入
                break; // 見つかったらループを終了
            }
        }
        
        if(employee2 != null) {
        model.addAttribute("empName", employee2.getEmpName());
        }
        
	    //バリデーションチェック
	    if(bindingResult.hasErrors()) {
	        return "error";
	    }
	    //データが存在しない場合
	    if(employees == null) {
	        model.addAttribute("errorMessage", "社員番号またはパスワードが違います。");
	        return "employee";
	    }
        
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
	@RequestMapping("/complete")
	public String updateMypage(@ModelAttribute @Validated Employee updateEmployee, BindingResult bindingResult, Model model) {
	    
	    Employee employee = empRepository.findByEmpId(updateEmployee.getEmpId());
	    if(employee != null) {
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
            
            model.addAttribute("updateEmployee", employee);
	    }
	    
        //バリデーションチェック
        if(bindingResult.hasErrors()) {
            return "error";
            
        }else if (updateEmployee.getEmpName().isEmpty()) {
            model.addAttribute("errorMessage", "名前は入力必須項目です。");
            return "mypage";
            
        }else if (updateEmployee.getEmpName().length() > 16) {
            model.addAttribute("errorMessage", "名前は16文字以内で入力してください。");
            return "mypage";
            
        }else if (updateEmployee.getPassword().isEmpty()) {
            model.addAttribute("errorMessage", "パスワードは入力必須項目です。");
            return "mypage";
            
        }else if (updateEmployee.getPassword().length() > 16) {
            model.addAttribute("errorMessage", "パスワードは16文字以内で入力してください。");
            return "mypage";
            
        }else if (updateEmployee.getBirthday() == null) {
            model.addAttribute("errorMessage", "生年月日は入力必須項目です");
            return "mypage";
	    }
        
	    //更新が完了した場合
	    return "redirect:/complete";
	}
}
