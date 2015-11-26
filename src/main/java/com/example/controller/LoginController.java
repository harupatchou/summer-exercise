package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.service.UserService;

/**
 * ログイン関連処理を行うコントローラー.
 * @author ueno
 *
 */
@Controller
@RequestMapping(value = "/loginUser")
@SessionAttributes("user")
public class LoginController {
	@Autowired
	private UserService userService;

	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public LoginForm setUpForm() {
		return new LoginForm();
	}

	/**
	 * ログイン画面を表示します.
	 * @return ログイン画面
	 */
	@RequestMapping
	public String index() {
		return "loginForm";
	}
	
	/**
	 * ログイン処理を行います.
	 * @param form　フォーム
	 * @param result　リザルト
	 * @param model　モデル
	 * @return　ログイン成功時:イベント管理画面
	 */
	@RequestMapping(value = "/login")
	public String login(@Validated LoginForm form,
			BindingResult result, Model model) {
		if (result.hasErrors()){
			return index();
		}
		String mailAddress = form.getMailAddress();
		String password = form.getPassword();
		User user = userService.findOneByMailAddressAndPassword(mailAddress, password);
		if (user == null) {
			ObjectError error = new ObjectError("loginerror", "メールアドレスまたはパスワードが違います。");
            result.addError(error);
			return index();
		}
		model.addAttribute("user", user);
		return "redirect:/search";
	}
}