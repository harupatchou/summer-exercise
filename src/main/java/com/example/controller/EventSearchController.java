package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Cast;
import com.example.domain.Event;
import com.example.form.CastForm;
import com.example.form.EventForm;
import com.example.form.EventSearchForm;
import com.example.service.EventSearchService;
/**
 * イベント情報検索コントローラー.
 * @author ueno
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/search")
@SessionAttributes("user")
public class EventSearchController {
	
	@Autowired
	private EventSearchService eventSearchService;
	
	//イベント情報格納用リスト
	private List<Event> eventList;
	/**
	 * フォームを初期化する.
	 * @return フォーム
	 */
	@ModelAttribute
	public EventForm setUpForm() {
		return new EventForm();
	}
	
	/**フォームを初期化する.
	 * @return フォーム
	 */
	@ModelAttribute
	public EventSearchForm setUpFindForm(){
		return new EventSearchForm();
	}
	
	/**
	 * イベント情報検索画面を表示する.
	 * @return イベント情報検索画面
	 */
	@RequestMapping
	public String list(Model model){
		Map<Integer, String> yearMap = new LinkedHashMap<Integer, String>();
		yearMap.put(2014, "2014年");
		yearMap.put(2015, "2015年");
		yearMap.put(2016, "2016年");
		model.addAttribute("yearMap", yearMap);
		
		Map<Integer, String> monthMap = new LinkedHashMap<Integer, String>();
		monthMap.put(1, "1月");
		monthMap.put(2, "2月");
		monthMap.put(3, "3月");
		monthMap.put(4, "4月");
		monthMap.put(5, "5月");
		monthMap.put(6, "6月");
		monthMap.put(7, "7月");
		monthMap.put(8, "8月");
		monthMap.put(9, "9月");
		monthMap.put(10, "10月");
		monthMap.put(11, "11月");
		monthMap.put(12, "12月");
		model.addAttribute("monthMap", monthMap);
		
				
		return "/event/list";
	}
	
	
	/**
	 * 全てのイベント情報を取得.
	 * @return イベント情報一覧
	 */
	@RequestMapping(value = "findAll")
	public String findAll(Integer button,Integer userId,Model model){
		eventList = eventSearchService.findAll(userId);
		model.addAttribute("eventList", eventList);
		model.addAttribute("button",button);
		return list(model);
	}
	
	/**
	 * year値のイベント情報を取得.
	 * @return 検索されたイベント情報
	 */
	@RequestMapping(value = "findByYear")
	public String findByYear(Integer year, Integer button,Integer userId, Model model){
		eventList = eventSearchService.findByYear(year,userId);
		model.addAttribute("eventList", eventList);
		model.addAttribute("button",button);
		return list(model);
	}
	
	/**
	 * year値、month値の両値と一致するイベント情報を取得.
	 * @return 検索されたイベント情報
	 */
	@RequestMapping(value = "findByYearAndMonth")
	public String findByYearAndMonth(EventSearchForm form, Model model){
		Event event = new Event();
		BeanUtils.copyProperties(form, event);
		eventList = eventSearchService.findByYearAndMonth(event);
		model.addAttribute("eventList",eventList);
		model.addAttribute("button",form.getButton());
		return list(model);
	}
	
	/**
	 * 検索条件と一致したイベント情報を取得
	 * @return 検索ページ
	 */
	@RequestMapping(value = "all")
	public String searchAll(EventSearchForm form, Model model){
		eventList = eventSearchService.searchAll(form);
		model.addAttribute("button",form.getButton());
		model.addAttribute("eventList",eventList);
		return list(model);
	}
	
	/**
	 * name値のキャスト情報取得.
	 * @return キャスト情報
	 */
	@RequestMapping(value = "findByCastId")
	public String findByCastName(CastForm form,RedirectAttributes redirect){
		Cast cast = eventSearchService.findByCastName(form.getCastName());
		redirect.addFlashAttribute("cast",cast);
		return "redirect:/events/flowSaveCast";
	}
	
	/**
	 * id値のキャスト情報取得.
	 * @return キャスト情報
	 */
	public String findByCastId(CastForm form,RedirectAttributes redirect){
		Cast cast = eventSearchService.findByCastName(form.getCastName());
		redirect.addFlashAttribute("cast",cast);
		return "redirect:/events/flowSaveCast";
	}
	
	/**
	 * 非同期検索(ajax)
	 * @param json
	 * @return ajaxSearchIndex
	 */
	@RequestMapping(value = "/ajaxSearch" ,consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public void ajaxSearch(@RequestBody JsonData json, RedirectAttributes result){
		//jsonデータをフォームにコピー
		EventSearchForm form = new EventSearchForm();
		System.out.println(json);
		BeanUtils.copyProperties(json, form);
		
		eventList = eventSearchService.searchAll(form);
	}
	
	/**
	 * 非同期検索更新(ajax)
	 * @return ajaxSearch.jsp
	 */
	@RequestMapping(value = "ajaxSearchIndex")
	public String ajaxSearchIndex(Model model){
		model.addAttribute("eventList",eventList);
		return "/event/ajaxSearch";
	}
	
}
