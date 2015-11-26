package com.example.form;

import lombok.Data;

/**
 * ログイン関連フォーム.
 * @author ueno
 *
 */
@Data
public class LoginForm {
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;

}
