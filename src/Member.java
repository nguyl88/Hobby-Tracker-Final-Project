import java.io.Serializable;
import java.util.LinkedList;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Member implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String lastName;
	private String email;
	private String nickname;
	private String password;
	private String hobby;
	private LinkedList<MemberArticles> listOfUserArticles;
	private LinkedList<MemberLikes> likedList; 
	boolean isLiked;

	public Member (String name, String nickname, String hobby, boolean isLiked, LinkedList<MemberArticles> listOfUserArticles) {
		//This is for the tableView
		this.name = name;
		this.nickname = nickname;
		this.hobby = hobby;
		this.isLiked = isLiked;
		this.listOfUserArticles = listOfUserArticles; //each member will have a linked list of their own articles
		
	}
	public boolean isLiked() {
		return isLiked;
	}
	
	public LinkedList<MemberArticles> getListOfUserArticles() {
		return listOfUserArticles;
	}
	public void setListOfUserArticles(LinkedList<MemberArticles> listOfUserArticles) {
		this.listOfUserArticles = listOfUserArticles;
	}
	public LinkedList<MemberLikes> getLikedList() {
		return likedList;
	}
	public void setLikedList(LinkedList<MemberLikes> likedList) {
		this.likedList = likedList;
	}
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	public Member(String name, String lastName, String nickname, String hobby, String email, String password, boolean b) {
		this.name = name;
		this.nickname = nickname;
		this.hobby = hobby;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isLiked = b;
	}

	public String getName() {
		return name;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Member [name=" + name + ", lastName=" + lastName + ", email=" + email + ", nickname=" + nickname
				+ ", password=" + password + ", hobby=" + hobby + ", isLiked=" + isLiked + "]";
	}


}
