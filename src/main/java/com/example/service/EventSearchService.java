package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Cast;
import com.example.domain.Event;
import com.example.form.EventSearchForm;
import com.example.logic.EventLogic;
import com.example.repository.EventRepository;

/**
 * イベント検索サービス
 * @author ueno
 */
@Service
public class EventSearchService {
	
	@Autowired
	private EventLogic eventLogic;
	@Autowired
	private EventRepository eventRepository;

	/**
	 * id値のキャスト情報取得.
	 * @param id
	 * @return キャスト情報
	 */
	public Cast findByCastName(String name){
		return eventRepository.findByCastId(name);
	}
	
	/**
	 * イベント情報一覧を取得.
	 * @return イベント情報一覧
	 */
	public List<Event> findAll(Integer userId) {
		return eventRepository.findAll(userId);
	}

	/**
	 * year値のイベント情報を取得.
	 * @param year year値
	 * @return 検索されたイベント情報
	 */
	public List<Event> findByYear(Integer year,Integer userId) {
		return eventRepository.findByYear(year,userId);
	}

	/**
	 * year値、month値の両値と一致するイベント情報を取得.
	 * @param event イベント
	 * @return 検索されたイベント情報
	 */
	public List<Event> findByYearAndMonth(Event event) {
		return eventRepository.findByYearAndMonth(event);
	}
	
	/**
	 * 検索条件と一致したイベント情報を全件取得.
	 * @param form
	 * @return 一致したイベント情報
	 */
	public List<Event> searchAll(EventSearchForm form){
		return eventLogic.searchAll(form);
	}
}
