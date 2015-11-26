package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Cast;
import com.example.domain.Event;
import com.example.form.CastForm;
import com.example.form.EventSearchForm;

/**
 * eventsテーブル操作用のリポジトリクラス.
 * @author ueno
 */
@Repository
public class EventRepository {
	private static final RowMapper<Event> EVENT_ROW_MAPPER = (rs, i) -> {
		Integer memberId = rs.getInt("member_id");
		Integer id = rs.getInt("event_id");
		String name = rs.getString("name");
		String cast = rs.getString("main_cast");
		String place = rs.getString("place");
		Integer price = rs.getInt("price");
		Integer year = rs.getInt("year");
		Integer month = rs.getInt("month");
		Integer day = rs.getInt("day");
		return new Event(memberId,id, name, cast,place, price, year, month, day);
	};
	
	private static final RowMapper<Cast> CAST_ROW_MAPPER = (rs, i) -> {
		Integer castId = rs.getInt("cast_id");
		String castName = rs.getString("cast_name");
		String reading = rs.getString("reading");
		return new Cast(castId, castName,reading);
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * イベント情報一覧を取得.
	 * @return イベント情報一覧
	 */
	public List<Event> findAll(Integer userId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", userId);
		List<Event> events = jdbcTemplate.query("SELECT * FROM events WHERE member_id=:id ORDER BY year, month, day",param,EVENT_ROW_MAPPER);
		return events;
	}
	
	/**
	 * キャスト情報一覧を取得.
	 * @return キャスト情報一覧
	 */
	public List<Cast> findAllCast(){
		List<Cast> casts = jdbcTemplate.query("SELECT * FROM master_cast ORDER BY reading", CAST_ROW_MAPPER);
		return casts;
	}
	
	/**
	 * id値のキャスト情報取得.
	 * @param id
	 * @return キャスト情報
	 */
	public Cast findByCastId(String name){
		SqlParameterSource param = new MapSqlParameterSource("cast_name",name);
		Cast cast = jdbcTemplate.queryForObject("SELECT * FROM master_cast WHERE cast_name=:cast_name", param,CAST_ROW_MAPPER);
		return cast;
	}
	
	/**
	 * id値のイベント情報を取得.
	 * @param id id値
	 * @return 検索されたイベント
	 */
	public Event findOne(Integer userId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", userId);
		Event event = jdbcTemplate.queryForObject(
				"SELECT * FROM events WHERE event_id=:id", param, EVENT_ROW_MAPPER);
		return event;
	}

	/**
	 * year値のイベント情報を取得.
	 * @param year year値
	 * @return 検索されたイベント情報
	 */
	public List<Event> findByYear(Integer year,Integer userId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("year", year).addValue("id", userId);
		List<Event> event = jdbcTemplate.query(
				"SELECT * FROM events WHERE member_id=:id AND year=:year ORDER BY month, day", param, EVENT_ROW_MAPPER);
		return event;
	}

	/**
	 * year値、month値の両値に一致するイベント情報を取得.
	 * @param event イベント
	 * @return 検索されたイベント情報
	 */
	public List<Event> findByYearAndMonth(Event event) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(event);
		List<Event> events = jdbcTemplate.query(
				"SELECT * FROM events WHERE member_id=:id AND year=:year AND month=:month ORDER BY day", param,EVENT_ROW_MAPPER);
		return events;
	}
	
	/**
	 * 検索条件と一致するイベント情報を取得.
	 * @param form フォーム
	 * @param sql sql文
	 * @return 検索されたイベント情報
	 */
	public List<Event> search(EventSearchForm form, String sql){
		SqlParameterSource param = new BeanPropertySqlParameterSource(form);
		List<Event> events = jdbcTemplate.query("SELECT * FROM events " + sql, param, EVENT_ROW_MAPPER);
		return events;
	}
	
	

	/**
	 * 引数でもらったイベント情報を登録または更新する.
	 * id値がnullなら新規登録を行う。
	 * id値に数値が入っていればそのid値のイベント情報の更新を行う。
	 * @param event イベント情報
	 * @return 登録または更新されたイベント情報
	 */
	public Event save(Event event) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(event);
		if (event.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO events(member_id,name, main_cast, place, price, year, month, day) values(:userId, :name, :cast, :place, :price, :year, :month, :day)",param);
		} else {
			jdbcTemplate.update(
					"UPDATE events SET member_id=:userId, name=:name, main_cast=:cast,place=:place, price=:price, year=:year, month=:month, day=:day WHERE event_id=:id",param);
		}
		return event;
	}
	
	/**
	 * キャスト情報を登録・更新.
	 * @param form
	 */
	public CastForm saveCast(CastForm form){
		SqlParameterSource param = new BeanPropertySqlParameterSource(form);
		if(form.getCastId() == null){
			jdbcTemplate.update("INSERT INTO master_cast(cast_name, reading) values(:castName, :reading)", param);
		}else{
			jdbcTemplate.update("UPDATE master_cast SET cast_name=:castName, reading=:reading WHERE cast_id=:castId", param);
		}
		return form;
	}
	/**
	 * IDから1件のイベント情報を削除する.
	 * @param id
	 */
	public void delete (Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		jdbcTemplate.update("DELETE FROM events WHERE event_id=:id", 
				param);
	}
	
	/**
	 * idから１件のキャスト情報を削除する.
	 * @param id
	 */
	public void deleteCast(Integer id){
		SqlParameterSource param = new MapSqlParameterSource().addValue("cast_id", id);
		jdbcTemplate.update("DELETE FROM master_cast WHERE cast_id=:cast_id", param);
	}
}
