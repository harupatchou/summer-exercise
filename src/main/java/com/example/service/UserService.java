package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.repository.UserRepository;

/**
 * メンバー関連サービスクラス.
 * @author ueno
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * メールアドレスとパスワードからメンバー情報を取得.
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return 検索されたメンバー情報
	 */
	public User findOneByMailAddressAndPassword(String mailAddress, String password){
		return userRepository.findByMailAddressAndMember(mailAddress, password);
	}
	
	/**
	 * メールアドレスからメンバー情報を取得.
	 * @param mailAddress メールアドレス
	 * @return 検索されたメンバー情報
	 */
	public User findByMailAddress(String mailAddress){
		return userRepository.findByMailAddress(mailAddress);
	}
	
	/**
	 * メンバー情報の登録・更新を行う.
	 * @param member メンバー
	 * @return 登録または更新されたメンバー情報
	 */
	public UserForm save(UserForm user){
		System.out.println("サービス");
		return userRepository.save(user);
	}

}
