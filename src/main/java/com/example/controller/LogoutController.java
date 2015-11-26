package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.domain.User;

/**
 * ログアウト関連処理を行うコントローラー.
 * @author ueno
 *
 */
@Controller
@RequestMapping(value = "/logout")
@SessionAttributes("user")
public class LogoutController {

	/**
	 * セッション情報に含まれるMemberオブジェクトをクリアします.
	 * @param member Sessionに入っているメンバー情報
	 * @param sessionStatus　セッションステータス
	 * @return　ログイン画面
	 */
	@RequestMapping(value = "sessionInvalidate")
	public String sessionInvalidate(User user
			, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/loginUser";
	}

}
