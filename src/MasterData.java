
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javafx.scene.control.CheckBox;
import javafx.util.Callback;

public class MasterData implements Serializable {

	private static final long serialVersionUID = 1L;
	protected static LinkedList<Member> accountInformation = new LinkedList<>();
	protected static LinkedList<MemberArticles> articleList = new LinkedList<>();
	private static LinkedList<MemberLikes> likedList = new LinkedList<>();
	private static File articleFile = new File("userArticles.txt");
	private static File likesFile = new File("LikesFile.txt");
	private static File mainFile = new File("hypotheticalFile.txt");

	public MasterData() throws IOException, ClassNotFoundException {
	}

	public static void add(Member newUser) {
		accountInformation.addFirst(newUser);
	}
	
	public static void addLike(Member m, Boolean b) throws FileNotFoundException, IOException {
		MemberLikes likedMember = new MemberLikes(m, b);
		likedList.add(likedMember);
		
	}
	public static void saveLikes() throws FileNotFoundException, IOException {
		ObjectOutputStream saveOutput = new ObjectOutputStream(new FileOutputStream(likesFile));
		likedList.forEach(likes -> {
			try {
				saveOutput.writeObject(likes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		saveOutput.close();
	}
	
	public static LinkedList<MemberLikes>loadLikes() throws FileNotFoundException, IOException {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(likesFile));
		try {
			while (objectInputStream != null) {
				MemberLikes m = (MemberLikes) objectInputStream.readObject();
				likedList.add(m);
				System.out.println(Arrays.toString(likedList.toArray()));
			}
			objectInputStream.close();
			System.out.println(accountInformation);

		} catch (EOFException ex) {
			System.out.println("EOF Exception has occured in this method");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return likedList;
	}

	public static boolean checkMember(Member m) {
		boolean isCurrentUser = accountInformation.contains(m);
		return isCurrentUser;
	}
	
	public static void saveArticles() throws FileNotFoundException, IOException {
		ObjectOutputStream saveOutput = new ObjectOutputStream(new FileOutputStream(articleFile));
		articleList.forEach(member -> {
			try {
				saveOutput.writeObject(member);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		saveOutput.close();
	}

	//Adding the member articles to the data
	public static void addArticles(String blog, ArrayList<CheckBox> tags) {
		Date time = new GregorianCalendar().getTime(); // getting the current time
		MemberArticles items = new MemberArticles(blog, tags, time);
		articleList.addFirst(items);

	}

	@SuppressWarnings("null")
	public static LinkedList<MemberArticles> loadArticles() throws FileNotFoundException, IOException {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(articleFile));
		try {
			while (objectInputStream != null) {
				MemberArticles  articles = (MemberArticles) objectInputStream.readObject();
				articleList.add(articles);
				System.out.println(Arrays.toString(accountInformation.toArray()));
			}
			objectInputStream.close();
			System.out.println(articleFile);

		} catch (EOFException ex) {
			System.out.println("EOF Exception has occured in this method");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}

	@SuppressWarnings("null")
	public static LinkedList<Member> loadMembers() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(mainFile));
		try {
			while (objectInputStream != null) {
				Member m = (Member) objectInputStream.readObject();
				accountInformation.add(m);
				System.out.println(Arrays.toString(accountInformation.toArray()));
			}
			objectInputStream.close();
			System.out.println(accountInformation);

		} catch (EOFException ex) {
			System.out.println("EOF Exception has occured in this method");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountInformation;
	}

	public static void SaveMembers() throws FileNotFoundException, IOException {
		ObjectOutputStream saveOutput = new ObjectOutputStream(new FileOutputStream(new File("hypotheticalFile.txt")));
		accountInformation.forEach(member -> {
			try {
				saveOutput.writeObject(member);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		saveOutput.close();
	}

	@SuppressWarnings("unused")
	public static boolean getUserLogin(String userString, String passString)
			throws ClassNotFoundException, IOException {
		try {

			System.out.println(accountInformation.size());
			for (int i = 0; i < accountInformation.size(); i++) {
				// accountInformation = (LinkedList<Member>)
				// loadMembers(mainFile);
				if (userString != null && passString != null && accountInformation.get(i).getEmail().equals(userString)
						&& accountInformation.get(i).getPassword().equals(passString)) {
					System.out.println("Access accepted");
					return true;
				}
				
			}
		} catch (ClassCastException ex) {
			System.out.println("ClassCastExceptionError");
		}

		System.out.println(
				"Access denied. Your username or password is incorrect. If you haven't created an account, create one");
		return false;

	}

	// user delete the account
	public static void deleteAccount(Member m) throws FileNotFoundException, ClassNotFoundException, IOException {
		accountInformation.remove(m);
	}

	// Get the value from the checkbox if the user likes the account
	public static boolean isLiked() {
		return true;
	}

	public static boolean emailExists(String email) throws FileNotFoundException, IOException, ClassNotFoundException {
		// loadData(); //loading all the data first
		for (int i = 0; i < accountInformation.size(); i++) {
			if (accountInformation.get(i).getEmail().contains(email)) {
				return true;
			}
			;
		}
		return false;
	}

	public static void saveData(Member m) throws FileNotFoundException, IOException {
		ObjectOutputStream saveOutput = new ObjectOutputStream(new FileOutputStream(mainFile, true));
		saveOutput.writeObject(m);
		saveOutput.close();

	}

	@SuppressWarnings("unchecked")
	public static void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(mainFile));
		if (!mainFile.exists()) {
			mainFile.createNewFile();
		}

		accountInformation = (LinkedList<Member>) input.readObject();
		System.out.println(Arrays.toString(accountInformation.toArray()));
		input.close();
	}

	public static LinkedList<Member> getLinkedList() {
		return accountInformation;
	}

	public static LinkedList<MemberArticles> getArticleList(){
		//returns all the list of the userArticles
		return articleList;
	}

}
