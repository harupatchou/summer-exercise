package com.example.form;

import lombok.Data;

/**
 * メンバー関連フォーム.
 * @author ueno
 *
 */
@Data
public class UserForm {
	/** 主キー */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;
	/**パスワード確認*/
	private String secondPassword;

}
