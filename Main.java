package miniProject2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ContactService contactService = new ContactService(10); // Initial size of 10 contacts
		GroupService groupService = new GroupService(5); // Initial size of 5 groups

		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("\nChoose an operation:");
			System.out.println("1. Create a contact");
			System.out.println("2. Create a group");
			System.out.println("3. Add a contact to a group");
			System.out.println("4. Remove a contact from a group");
			System.out.println("5. Update a contact");
			System.out.println("6. Delete a contact");
			System.out.println("7. Delete a group");
			System.out.println("8. View contacts in a group");
			System.out.println("9. View all contacts");
			System.out.println("10. View all groups");
			System.out.println("11. View all the contacts sorted by phone number");
			System.out.println("12. View all the contacts sorted by Name");
			System.out.println("13. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline

			switch (choice) {
			case 1:
				System.out.print("Enter contact name: ");
				String name = scanner.nextLine();
				System.out.print("Enter phone number: ");
				String phoneNumber = scanner.nextLine();
				System.out.print("Enter country code: ");
				String countryCode = scanner.nextLine();
				try {
					contactService.createContact(name, phoneNumber, countryCode);
					System.out.println("Contact created successfully.");

				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO Auto-generated catch block

					System.out.println(e.getMessage());
				} catch (PhoneNumberNotValid e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (DuplicateContactException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case 2:
				System.out.print("Enter group name: ");
				String groupName = scanner.nextLine();
				System.out.print("Enter group description: ");
				String groupDescription = scanner.nextLine();

				try {
					groupService.createGroup(groupName, groupDescription);
				} catch (ArrayIndexOutOfBoundsException | DuplicateGroupException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				System.out.println("Group created successfully.");
				break;

			case 3:
				System.out.print("Enter the contact's phone number: ");
				phoneNumber = scanner.nextLine();
				System.out.print("Enter the group name to add the contact to: ");
				groupName = scanner.nextLine();
				Group groupToAdd = groupService.getGroup(groupName);
				if (groupToAdd != null) {
				
						try {
							contactService.addContactToGroup(phoneNumber, groupToAdd);
						} catch (ArrayIndexOutOfBoundsException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							break;
						} catch (GroupLimitException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						} catch (DuplicateContactException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
					System.out.println("Contact added to group successfully.");
				} else {
					System.out.println("Group not found.");
				}
				break;

			case 4:
				System.out.print("Enter the contact's phone number: ");
				phoneNumber = scanner.nextLine();
				System.out.print("Enter the group name to remove the contact from: ");
				groupName = scanner.nextLine();
				Group groupToRemove = groupService.getGroup(groupName);
				if (groupToRemove != null) {
					try {
						contactService.removeContactFromGroup(phoneNumber, groupToRemove);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Contact removed from group successfully.");
				} else {
					System.out.println("Group not found.");
				}
				break;

			case 5:
				System.out.print("Enter the phone number of the contact to update: ");
				phoneNumber = scanner.nextLine();
				System.out.print("Enter new name: ");
				String newName = scanner.nextLine();
				System.out.print("Enter new country code: ");
				String newCountryCode = scanner.nextLine();
				try {
					contactService.updateContact(phoneNumber, newName, newCountryCode);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				System.out.println("Contact updated successfully.");
				break;

			case 6:
				System.out.print("Enter the phone number of the contact to delete: ");
				phoneNumber = scanner.nextLine();
				try {
					contactService.deleteContact(phoneNumber);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					break;
				}
				System.out.println("Contact deleted successfully.");
				break;

			case 7:
				System.out.print("Enter the name of the group to delete: ");
				groupName = scanner.nextLine();
				groupService.deleteGroup(groupName);
				System.out.println("Group deleted successfully.");
				break;

			case 8:
				System.out.print("Enter the group name to view contacts: ");
				groupName = scanner.nextLine();
				Group groupToView = groupService.getGroup(groupName);
				if (groupToView != null) {
					System.out.println("Contacts in group " + groupName + ":");
					for (Contact contact : groupToView.getContacts()) {
						if (contact != null) {
							System.out.println(contact.getName() + " (" + contact.getPhoneNumber() + ")");
						}
					}
				} else {
					System.out.println("Group not found.");
				}
				break;

			case 9:
				System.out.println("All contacts:");
				for (Contact contact : contactService.getAllContacts()) {
					if (contact != null) {
						System.out.println(contact.getName() + " (" + contact.getPhoneNumber() + ")");
					}
				}
				break;

			case 10:
				System.out.println("All groups:");
				for (Group group : groupService.getAllGroups()) {
					if (group != null) {
						System.out.println(group.getGroupName() + " (" + group.getGroupDescription() + ")");
					}
				}
				break;

			case 11:
				System.out.println("All contacts sorted by phone number:");
				Contact[] sortedContacts = contactService.getAllContactSortedByPhoneNumber();
				for (Contact contact : sortedContacts) {
					if (contact != null) {
						System.out.println(contact.getName() + " (" + contact.getPhoneNumber() + ")");
					}
				}
				break;

			case 12:
				System.out.println("All contacts sorted by Name:");
				Contact[] sortedContacts1 = contactService.getAllContactSortedByName();
				for (Contact contact : sortedContacts1) {
					if (contact != null) {
						System.out.println(contact.getName() + " (" + contact.getPhoneNumber() + ")");
					}
				}
				break;

			case 13:
				exit = true;
				break;

			default:
				System.out.println("Invalid choice! Please try again.");
			}
		}
		scanner.close();
		System.out.println("Exiting...");

	}

}