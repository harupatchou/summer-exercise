package com.example.form;

import lombok.Data;

/**
 * イベント関連検索用フォーム
 * @author ueno
 *
 */
@Data
public class EventSearchForm {
	/**ユーザーID*/
	private Integer userId;
	/**イベント名*/
	private String name;
	/**開催地*/
	private String place;
	/**キャスト*/
	private String cast;
	/**チケット代*/
	private Integer price;
	/**チケットフラグ*/
	private Integer ticketFlag;
	/**開催年*/
	private Integer year;
	/**開催月*/
	private Integer month;
	/**開催日*/
	private Integer day;
	/**ラジオボタンフラグ*/
	private Integer button;

}
