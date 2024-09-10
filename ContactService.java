package miniProject2;

import java.util.Comparator;
import miniProject2.ContactService;

public class ContactService implements Comparable<ContactService> {

	private Contact[] contacts;
	private int contactCount;

	public ContactService(int initialSize) {
		this.contacts = new Contact[initialSize];
		this.contactCount = 0;
	}

	public void createContact(String name, String phoneNumber, String countryCode)
			throws DuplicateContactException, ArrayIndexOutOfBoundsException, PhoneNumberNotValid {

		if (contactCount != 10) {
			throw new PhoneNumberNotValid("Enter only 10 number Digit phone Number!");
		}

		if (contactCount >= contacts.length) {
			throw new ArrayIndexOutOfBoundsException("No more contacts can be added.");
		}
		for (int i = 0; i < contactCount; i++) {
			if (contacts[i].getPhoneNumber().equals(phoneNumber)) {
				throw new DuplicateContactException("phone number already exists!");
			}
		}
		contacts[contactCount++] = new Contact(name, phoneNumber, countryCode);
	}

	public Contact getContact(String phoneNumber) {
		for (int i = 0; i < contactCount; i++) {
			if (contacts[i].getPhoneNumber().equals(phoneNumber)) {
				return contacts[i];
			}
		}
		return null;
	}

	public void updateContact(String phoneNumber, String newName, String newCountryCode)
			throws IllegalArgumentException {

		Contact contact = getContact(phoneNumber);
		if (contact != null) {
			contact.setName(newName);
			contact.setCountryCode(newCountryCode);
		} else {
			throw new IllegalArgumentException(
					"Cannot update contact. Contact with phone number " + phoneNumber + " not found.");
		}

	}

	public void deleteContact(String phoneNumber) throws IllegalArgumentException {

		for (int i = 0; i < contactCount; i++) {
			if (contacts[i].getPhoneNumber().equals(phoneNumber)) {
				contacts[i] = contacts[contactCount - 1]; // Replace with the last contact
				contacts[contactCount - 1] = null;
				contactCount--;
				return;
			}
		}
		throw new IllegalArgumentException("Contact with phone number " + phoneNumber + " not found.");
	}

	public void addContactToGroup(String phoneNumber, Group group) throws ArrayIndexOutOfBoundsException, GroupLimitException, DuplicateContactException {
		Contact contact = getContact(phoneNumber);
		if (contact != null) {
			group.addContact(contact);
			contact.addGroup(group);
		}
	}

	public void removeContactFromGroup(String phoneNumber, Group group) throws Exception {
		Contact contact = getContact(phoneNumber);
		if (contact != null) {
			group.removeContact(contact);
			contact.removeGroup(group);
		}
	}

	public Contact[] getAllContacts() {
		return contacts;
	}

	// Sorting with exception handling
	private Contact[] sort(Comparator<Contact> comp) {
		Contact[] sortedContacts = new Contact[contactCount];
		// Copying
		for (int i = 0; i < contactCount; i++) {
			sortedContacts[i] = contacts[i];
		}
		boolean swapped;
		for (int i = 0; i < contactCount - 1; i++) {
			swapped = false;
			for (int j = 0; j < contactCount - i - 1; j++) {
				if (comp.compare(sortedContacts[j], sortedContacts[j + 1]) > 0) {
					// Swap sortedContacts[j] and sortedContacts[j + 1]
					Contact temp = sortedContacts[j];
					sortedContacts[j] = sortedContacts[j + 1];
					sortedContacts[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped)
				break;
		}

		return sortedContacts;

	}

	public Contact[] getAllContactSortedByPhoneNumber() {
		PhoneNumberComparator phoneNumberComparator = new PhoneNumberComparator();
		return sort(phoneNumberComparator);
	}

	public Contact[] getAllContactSortedByName() {
		NameComparator nameComparator = new NameComparator();
		return sort(nameComparator);
	}

	@Override
	public int compareTo(ContactService o) {
		return 0;
	}
}
