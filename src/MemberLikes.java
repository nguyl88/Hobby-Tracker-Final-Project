import java.io.Serializable;
import java.util.LinkedList;

public class MemberLikes implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean liked;
	private Member m;
	private transient LinkedList<MemberLikes> likesList;
	private int numOfLikes;

	public MemberLikes(Member m, Boolean liked){
		this.m = m;
		this.liked = liked;
		numOfLikes++;
	}

	public int getNumOfLikes() {
		return numOfLikes;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public Member getM() {
		return m;
	}

	public void setM(Member m) {
		this.m = m;
	}

	public LinkedList<MemberLikes> getLikesList() {
		return likesList;
	}

	public void setLikesList(LinkedList<MemberLikes> likesList) {
		this.likesList = likesList;
	}

	public int compareTo(MemberLikes memberLikes) {
		return 0;
	}
	

}
