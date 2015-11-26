package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Cast;
import com.example.domain.Event;
import com.example.form.CastForm;
import com.example.repository.EventRepository;

/**
 * イベント関連サービスクラス.
 * @author ueno
 *
 */
@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;
	
	/**
	 * キャスト情報一覧を取得.
	 * @return キャスト情報一覧
	 */
	public List<Cast> findAllCast(){
		return eventRepository.findAllCast();
	}
	
	/**
	 * キャスト情報を登録・更新
	 * @param form
	 * @return キャスト情報
	 */
	public CastForm findByCastId(CastForm form){
		return eventRepository.saveCast(form);
	}
	
	/**
	 * キャスト名全件取得.
	 * @return キャスト名一覧
	 */
	public List<String> getCastName(){
		List<Cast> castList = eventRepository.findAllCast();
		List<String> castNameList = new ArrayList<>();
		for(Cast cast : castList){
			castNameList.add(cast.getCastName());
		}
		return castNameList;
	}
	
	/**
	 * id値のイベント情報を取得.
	 * @param id id値
	 * @return 検索されたイベント情報
	 */
	public Event findOne(Integer id){
		return eventRepository.findOne(id);
	}
	
	/**
	 * 引数でもらったイベント情報を登録する.
	 * @param event イベント情報
	 * @return 登録されたイベント情報
	 */
	public Event save(Event event){
		return eventRepository.save(event);
	}
	
	/**
	 * 引数でもらったイベント情報を更新.
	 * @param event イベント情報
	 * @return 行進されたイベント情報
	 */
	public Event update(Event event){
		return eventRepository.save(event);
	}
	
	/**
	 * キャスト情報を登録・更新.
	 * @param form
	 */
	public void saveCast(CastForm form){
		eventRepository.saveCast(form);
	}
	
	/**IDから一件のイベント情報を削除する.
	 * @param id
	 */
	public void delete(Integer id){
		eventRepository.delete(id);
	}
	
	/**
	 * IDから１件のキャスト情報を削除する.
	 * @param id
	 */
	public void deleteCast(Integer id){
		eventRepository.deleteCast(id);
	}
}
