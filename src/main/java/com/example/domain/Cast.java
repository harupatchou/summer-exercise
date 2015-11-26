package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * キャスト情報.
 * @author ueno
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cast {
	/**キャストID*/
	private Integer castId;
	/**キャスト名*/
	private String castName;
	/**よみがな*/
	private String reading;

}
