package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.domain.Cast;
import com.example.domain.Event;
import com.example.form.CastForm;
import com.example.form.EventForm;
import com.example.form.EventSearchForm;
import com.example.security.LoginUserDetails;
import com.example.service.EventService;

/**
 * イベント関連操作を行うコントローラー.
 * @author ueno
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/events")
@SessionAttributes("user")
public class EventController {
	@Autowired
	private EventService eventService;
	
	/**
	 * イベントフォーム初期化.
	 * @return フォーム
	 */
	@ModelAttribute
	public EventForm setUpForm() {
		return new EventForm();
	}
	
	/**イベント情報検索フォーム初期化.
	 * @return フォーム
	 */
	@ModelAttribute
	public EventSearchForm setUpSearchForm(){
		return new EventSearchForm();
	}
	
	/**
	 * キャストフォーム初期化.
	 * @return フォーム
	 */
	@ModelAttribute
	public CastForm setUpCastForm(){
		return new CastForm();
	}
	
	/**
	 * イベント情報登録画面を表示する.
	 * @param model モデル
	 * @return イベント情報登録画面
	 */
	@RequestMapping
	public String list(Model model){
		return "/event/register";
	}
	
	/**
	 * イベント情報の登録.
	 * @param form フォーム
	 * @param result リザルト
	 * @param model モデル
	 * @return イベント一覧画面
	 */
	@RequestMapping(value = "create")
	public String create(@Validated EventForm form, BindingResult result,Model model) {
		if (result.hasErrors()) {
			return list(model);
		}
		Event event = new Event();
		BeanUtils.copyProperties(form, event);
		eventService.save(event);
		model.addAttribute("event",form);
		return list(model);
	}
	
	/**
	 * イベント情報更新画面へ遷移.
	 * @param id イベントID
	 * @param form　フォーム
	 * @return　イベント情報更新画面
	 */
	@RequestMapping(value = "editForm")
	public String editForm(@RequestParam Integer id, EventForm form,Model model) {
		Event event = eventService.findOne(id);
		BeanUtils.copyProperties(event, form);
		model.addAttribute("event", form);
		
		return "/event/edit";
	}
	
	/**
	 * イベント情報の更新.
	 * @param id　イベントID
	 * @param form フォーム
	 * @param result　リザルト
	 * @return　イベント一覧画面
	 */
	@RequestMapping(value = "edit")
	public String edit(@RequestParam Integer id, @Validated EventForm form,
			BindingResult result,Model model) {
		if (result.hasErrors()) {
			return editForm(id, form,model);
		}
		Event event = new Event();
		BeanUtils.copyProperties(form, event);
		event.setId(id);
		eventService.update(event);
		return editForm(id,form,model);
	}
	
	/**
	 * イベント一覧画面へ遷移.
	 * @return イベント一覧画面
	 */
	@RequestMapping(value = "edit", params = "goToTop")
	public String goToTop() {
		return "redirect:/search";
	}
	
	/**
	 * キャスト情報の登録・更新.
	 * @param form
	 * @return キャスト選択画面
	 */
	@RequestMapping(value = "saveCast")
	public String saveCast(CastForm form){
		eventService.saveCast(form);
		return "event/FinishedRegistCast";
	}
	
	/**
	 * キャスト選択窓を開く
	 * @return キャスト選択画面
	 */
	@RequestMapping(value = "flowSelectCast")
	public String flowSelectCast(Model model){
		List<Cast> casts = eventService.findAllCast();
		model.addAttribute("castList",casts);
		return "/event/selectCast";
	}
	
	
	/**
	 * キャスト情報登録・更新窓を開く
	 * @param model
	 * @return キャスト情報登録・更新画面
	 */
	@RequestMapping(value = "flowSaveCast")
	public String flowSaveCast(Model model){
		List<String> castNameList = eventService.getCastName();
		model.addAttribute("castList",castNameList);
		return "/event/registCast";
	}
	
	/**
	 * 検索ページに遷移.
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goToSearch")
	public String goToSearch(@AuthenticationPrincipal LoginUserDetails user,Model model){
		model.addAttribute("user",user);
		return "redirect:/search";
	}
	
	/**
	 * id値のイベント情報を削除する.
	 * @param id id値
	 * @return イベント情報一覧画面
	 */
	@RequestMapping(value = "delete")
	public String delete(@RequestParam Integer id) {
		eventService.delete(id);
		return "redirect:/search";
	}
	
	/**
	 * id値のキャスト情報を削除する.
	 * @param form
	 */
	@RequestMapping(value = "deleteCast")
	public String deleteCast(CastForm form){
		eventService.deleteCast(form.getCastId());
		return "redirect:/events/flowSaveCast";
	}
}
