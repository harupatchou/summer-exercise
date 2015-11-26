package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メンバー情報.
 * @author ueno
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {	
	/** 主キー */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;
}
