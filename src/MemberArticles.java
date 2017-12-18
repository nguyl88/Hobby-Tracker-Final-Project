
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.CheckBox;

public class MemberArticles implements Serializable{

	private static final long serialVersionUID = 1L;
	private String articles;
	private transient ArrayList<CheckBox> tags;
	private Date time;


	public MemberArticles(String a, ArrayList<CheckBox>tags, Date time) {
		this.articles = a;
		this.tags = tags;
		this.setTime(time);
	}

	public String getArticles() {
		return articles;
	}

	public void setArticles(String articles) {
		this.articles = articles;
	}

	public ArrayList<CheckBox> getTags() {
		return tags;
	}

	public void setTags(ArrayList<CheckBox> tags) {
		this.tags = tags;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "MemberArticles [articles=" + articles + ", tags=" + tags + ", time=" + time + "]";
	}

	public static boolean isChecked() {
		return true;
	}
}
