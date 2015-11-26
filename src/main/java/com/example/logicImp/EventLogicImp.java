package com.example.logicImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.Event;
import com.example.form.EventSearchForm;
import com.example.logic.EventLogic;
import com.example.repository.EventRepository;

@Component
public class EventLogicImp implements EventLogic{
	
	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public List<Event> searchAll(EventSearchForm form){
		//キャストを配列に変換用
		ArrayList<String> castList = new ArrayList<>();
		String castName = null;
		String[] casts = null;
		
		//キャストを配列に格納
		if(form.getCast() != null){
			castName = form.getCast();
			casts =  castName.split("\\,");
			for(String cast : casts){
				castList.add(cast);
			}
		}
		//中身が空文字の時にnullをセット
		if(form.getName() == ""){
			form.setName(null);
		}
		if(form.getPlace() == ""){
			form.setPlace(null);
		}
		if(form.getCast() == ""){
			form.setCast(null);
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("where member_id=:userId");
		//全てnullの場合はイベント情報全件取得
		if(form.getName() == null && form.getPlace() == null && form.getCast() == null
				&& form.getPrice() == null && form.getYear() == null && form.getMonth() == null
				&& form.getDay() == null){
			return eventRepository.findAll(form.getUserId());
		}
		
		//検索条件に合ったsql文作成
		if(form.getName() != null){
			sql.append(" and name ilike '%' || :name || '%'");
		}
		if(form.getPlace() != null){
			sql.append(" and place ilike '%' || :place || '%'");
		}
		if(form.getCast() != null && castList.size() == 1){
			sql.append(" and main_cast ilike '%' || :cast || '%'");
		}
		if(form.getPrice() != null){
			if(form.getTicketFlag() == 1){
			sql.append(" and price >= :price");
			}else{
				sql.append(" and price <= :price");
			}
		}
		if(form.getYear() != null){
			sql.append(" and year=:year");
		}
		if(form.getMonth() != null){
			sql.append(" and month=:month");
		}
		if(form.getDay() != null){
			sql.append(" and day=:day");
		}
		sql.append(" ORDER BY year,month,day");
		
		//検索
		List<Event> events = eventRepository.search(form, sql.toString());
		
		//キャスト情報が2つ以上の時判定
		if(form.getCast() != null && castList.size() > 1){
			//一致する情報格納用リスト
			List<Event> findByCastEventList = new ArrayList<>();
			
			for(Event event : events){
				//返ってきたイベントのキャスト情報をリストに変換用
				ArrayList<String> returnCastList = new ArrayList<>();
				String returnCastName = null;
				String[] returnCasts = null;
				
				//キャスト情報をリストに格納
				returnCastName = event.getCast();
				returnCasts = returnCastName.split("\\,");
				for(String returnCast : returnCasts){
					returnCastList.add(returnCast);
				}
				
				//検索条件に一致したイベント情報をリストに格納
				for(String cast : castList){
					for(String returnCast : returnCastList){
						if(cast.equals(returnCast)){
							findByCastEventList.add(event);								
						}
					}
				}
			}
			
			//リストから同じ要素を削除
			List<Event> completeEventList = new ArrayList<>();
			Event tempEvent = null;
			for(int i = 0; i <findByCastEventList.size();i++){
				if(i == 0){
					tempEvent = findByCastEventList.get(i);
					completeEventList.add(tempEvent);
				}
				if(tempEvent != findByCastEventList.get(i)){
					tempEvent = findByCastEventList.get(i);
					completeEventList.add(tempEvent);
				}
			}
			return completeEventList;
		}
		return events;
	}
}
