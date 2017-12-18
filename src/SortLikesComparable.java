
import java.util.*;
//This is where all the comparator are stored.
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
	public int compare(Date date1, Date date2) {
		return date1.compareTo(date2);
	}
	
}

//This suppose to sort by most liked hobbies, but it only sort hobbies by A-Z.
class SortByMostLikedHobbies implements Comparator<Member> {

	@Override
	public int compare(Member m1, Member m2) {
		return m1.getHobby().compareTo(m2.getHobby());
	}
	
	
}
