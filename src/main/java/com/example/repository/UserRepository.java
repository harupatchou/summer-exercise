package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.form.UserForm;

/**
 * membersテーブル操作用のリポジトリクラス.
 * @author ueno
 *
 */
@Transactional
@Repository
public class UserRepository {
	/**
	 * ResultSetオブジェクトからMemberオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<User> MEMBER_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("member_id");
		String name = rs.getString("name");
		String mailAddress = rs.getString("mail_address");
		String password = rs.getString("password");
		return new User(id, name, mailAddress, password);
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

//	private StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();

	/**
	 * メールアドレスとパスワードからメンバーを取得.
	 * 
	 * @param mailAddress
	 *            メールアドレス
	 * @param password
	 *            パスワード
	 * @return メンバー情報.メンバーが存在しない場合はnull.
	 */
	public User findByMailAddressAndMember(String mailAddress, String password) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress);
		User member = null;
		try {
			member = jdbcTemplate.queryForObject("SELECT * FROM members WHERE mail_address=:mail_address",
					param, MEMBER_ROW_MAPPER);
			
			//パスワードマッチング
//			Boolean pass = standardPasswordEncoder.matches(password, member.getPassword());
//			if (pass) {
				return member;
//			}
//			return null;
		} catch (DataAccessException e) {
			return null;
		}
	}

	/**
	 * email値のメンバー情報を取得.
	 * 
	 * @param email
	 *            検索するemail値
	 * @return 検索されたメンバー情報
	 */
	public User findByMailAddress(String mailAddress) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress);
		
		try {
			User user = jdbcTemplate.queryForObject(
					"SELECT * FROM members WHERE mail_address = :mail_address", param,
					MEMBER_ROW_MAPPER);
			return user;
		} catch (DataAccessException e) {
			return null;
		}
	}

	/**
	 * メンバー情報の保存・更新をする.
	 * 
	 * @param member
	 *            保存または更新するメンバー情報
	 * @return 保存または更新されたメンバー情報
	 */
	public UserForm save(UserForm user) {
		//パスワードハッシュ化
//		String password = standardPasswordEncoder.encode(user.getPassword());
//		user.setPassword(password);
		System.out.println(user);
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		if (user.getId() == null) {
			jdbcTemplate.update("INSERT INTO members(name,mail_address,password) values(:name,:mailAddress,:password)",
					param);
		} else {
			jdbcTemplate.update(
					"UPDATE members SET name=:name,mail_address=:mailAddress,password=:password WHERE id=:id", param);
		}
		return user;
	}

}
