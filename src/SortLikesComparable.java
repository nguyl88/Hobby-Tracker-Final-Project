
import java.util.*;
//This is where all the comparator are stored.
public class SortLikesComparable implements Comparator<MemberLikes >{
	@Override
	public int compare(MemberLikes list1, MemberLikes list2) {
		return Integer.compare(list1.getNumOfLikes(),list2.getNumOfLikes());
	}
}

//sort recent articles 
class SortByDate implements Comparator<MemberArticles> {

	@Override
	public int compare(MemberArticles date1, MemberArticles date2) {
		return date1.getTime().compareTo(date2.getTime());
	}
	
}

//This suppose to sort by most liked hobbies, but it only sort hobbies by A-Z.
class SortByMostLikedHobbies implements Comparator<Member> {

	@Override
	public int compare(Member m1, Member m2) {
		return m1.getHobby().compareTo(m2.getHobby());
	}
	
	
}
