package com.example.form;

import lombok.Data;

/**イベント関連フォーム.
 * @author ueno
 *
 */
@Data
public class EventForm {
	/**メンバーID*/
	private Integer userId;
//	/**イベントID*/
//	private Integer id;
	/**イベント名*/
	private String name;
	/**開催地*/
	private String place;
	/**キャスト*/
	private String cast;
	/**チケット代*/
	private Integer price;
	/**開催年*/
	private Integer year;
	/**開催月*/
	private Integer month;
	/**開催日*/
	private Integer day;

}
