package miniProject2;

public class Group {
	
		private static  int contactArraySize=25;
		 private String groupName;
		    private String groupDescription;
		    private Contact[] contacts=new Contact[contactArraySize];
		    private int contactCount;

		    public Group(String groupName, String groupDescription) {
		        this.groupName = groupName;
		        this.groupDescription = groupDescription;
		        this.contactCount = 0;
		    }

		    // Getters and setters
		    public String getGroupName() { return groupName; }
		    public void setGroupName(String groupName) { this.groupName = groupName; }

		    public String getGroupDescription() { return groupDescription; }
		    public void setGroupDescription(String groupDescription) { this.groupDescription = groupDescription; }

		    public Contact[] getContacts() { return contacts; }

		    public void addContact(Contact contact) throws GroupLimitException, DuplicateContactException {
		    	 if (contactCount >= contacts.length) {
		             throw new GroupLimitException("Group cannot contain more contacts.");
		         }
		         for (int i = 0; i < contactCount; i++) {
		             if (contacts[i] == contact) {
		                 throw new DuplicateContactException("Contact is already in the group.");
		             }
		         }
		         contacts[contactCount++] = contact;
		    }

		    public void removeContact(Contact contact) throws ContactNotFoundException {
		    	 boolean found = false;
		         for (int i = 0; i < contactCount; i++) {
		             if (contacts[i].equals(contact)) {
		                 contacts[i] = contacts[contactCount - 1]; // Replace with the last contact
		                 contacts[contactCount - 1] = null;
		                 contactCount--;
		                 found = true;
		                 break;
		             }
		         }
		         if (!found) {
		             throw new ContactNotFoundException("Contact not found in the group.");
		         }
		    }

	}