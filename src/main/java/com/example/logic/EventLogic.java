package com.example.logic;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.domain.Event;
import com.example.form.EventSearchForm;

/**
 * イベント用ロジック
 * @author ueno
 */
@Component
public interface EventLogic {
	
	//条件に一致するイベント情報をすべて取得
	public List<Event> searchAll(EventSearchForm form);
	
}
