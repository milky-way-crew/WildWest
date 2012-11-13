package com.web.app.worldgames.domain;

import java.sql.Timestamp;

public class User {
	private int id;
	private String login;
	private String password;
	private String nickname;
	private String email;
	private int userStat;
	private String avatar;
	private String role;
	private Timestamp userDate;

	public User() {
		login = new String();
		password = new String();
		nickname = new String();
		email = new String();
		avatar = new String();
		role = new String();
	}

	public User(int id, String login, String password, String nickname,
			String email, int userStat, String avatar, String role,
			Timestamp userDate) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.userStat = userStat;
		this.avatar = avatar;
		this.role = role;
		this.userDate = userDate;
	}
	public User(int id, String login, String password, String nickname,
			String email, int userStat, String avatar, String role) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.userStat = userStat;
		this.avatar = avatar;
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", nickname=" + nickname + ", email=" + email + ", userStat="
				+ userStat + ", avatar=" + avatar + ", role=" + role
				+ ", userDate=" + userDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUserDate(Timestamp userDate) {
		this.userDate = userDate;
	}

	public Timestamp getUserDate() {
		return userDate;
	}

	public int getUserStat() {
		return userStat;
	}

	public void setUserStat(int userStat) {
		this.userStat = userStat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result
				+ ((userDate == null) ? 0 : userDate.hashCode());
		result = prime * result + userStat;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userDate == null) {
			if (other.userDate != null)
				return false;
		} else if (!userDate.equals(other.userDate))
			return false;
		if (userStat != other.userStat)
			return false;
		return true;
	}

}
