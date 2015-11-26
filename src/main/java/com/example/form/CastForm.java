package com.example.form;

import lombok.Data;

/**
 * キャスト情報フォーム.
 * @author ueno
 */
@Data
public class CastForm {
	/**キャストID*/
	private Integer castId;
	/**キャスト名*/
	private String castName;
	/**よみがな*/
	private String reading;
}
