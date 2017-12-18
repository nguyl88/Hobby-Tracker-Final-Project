
import java.util.*;

public class SortLikesComparable implements Comparator<LinkedList<MemberLikes> >{
	@Override
	public int compare(LinkedList<MemberLikes> list1, LinkedList<MemberLikes> list2) {
		return Integer.compare(list1.size(),list2.size());
	}
}

//sort recent articles 
class SortByDate implements Comparator<Date> {

	@SuppressWarnings("deprecation")
	@Override
	public int compare(Date list1, Date list2) {
		return Integer.compare(list1.getDate(), list2.getDate());
	}
	
}

//This suppose to sort by most liked hobbies, but it only sort hobbies by A-Z.
class SortByMostLikedHobbies implements Comparator<Member> {

	@Override
	public int compare(Member m1, Member m2) {
		return m1.getHobby().compareTo(m2.getHobby());
	}
	
	
}
