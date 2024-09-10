package miniProject2;

import java.util.Comparator;

public class NameComparator implements Comparator<Contact> {
	@Override
	public int compare(Contact o1, Contact o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}

}