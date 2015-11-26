package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * イベント情報.
 * @author ueno
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
	/**メンバーID*/
	private Integer userId; 
	/**イベントID*/
	private Integer id;
	/**イベント名*/
	private String name;
	/**キャスト*/
	private String cast;
	/**開催地*/
	private String place;
	/**チケット代*/
	private Integer price;
	/**開催年*/
	private Integer year;
	/**開催月*/
	private Integer month;
	/**開催日*/
	private Integer day;

}
