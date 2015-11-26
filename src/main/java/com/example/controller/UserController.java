package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.UserService;

/**
 * メンバー関連処理を行うコントローラー.
 * @author ueno
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * フォームを初期化する.
	 * @return フォーム
	 */
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}

	/**
	 * メンバー情報登録画面を表示する.
	 * @return メンバー情報登録画面
	 */
	@RequestMapping(value = "form")
	public String form() {
		return "/user/form";
	}

	/**
	 * メンバー情報を登録する.
	 * 
	 * @param form　フォーム
	 * @param result　リザルト
	 * @param model モデル
	 * @return ログイン画面
	 */
	@RequestMapping(value = "create")
	public String create(UserForm form, BindingResult result, Model model) {
		System.out.println(form);
		if (result.hasErrors()) {
			System.out.println("えらー");
			return form();
		}
		if(userService.findByMailAddress(form.getMailAddress())!=null){
			ObjectError error = new ObjectError("errorAddress", "すでに使用中のメールアドレスです");
			result.addError(error);
			return form();
		}
		
		if(!(form.getPassword().equals(form.getSecondPassword()))){
			ObjectError error = new ObjectError("errorPassword", "パスワードが一致しません");
			result.addError(error);
			return form();
		}
		userService.save(form);
		return "redirect:/";
	}
}


