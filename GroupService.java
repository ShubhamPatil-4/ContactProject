package miniProject2;

public class GroupService {
		
		private Group[] groups;
	    private int groupCount;

	    public GroupService(int initialSize) {
	        this.groups = new Group[initialSize];
	        this.groupCount = 0;
	    }

	    public void createGroup(String groupName, String groupDescription) throws ArrayIndexOutOfBoundsException, DuplicateGroupException {
	    	if (groupCount >= groups.length) {
	            throw new ArrayIndexOutOfBoundsException(" Array is full.");
	        }
	    	 for (int i = 0; i < groupCount; i++) {
	             if (groups[i].getGroupName().equals(groupName)) {
	                 throw new DuplicateGroupException("Group with name " + groupName + " already exists.");
	             }
	         }

	         // If no issues, create the group
	         groups[groupCount++] = new Group(groupName, groupDescription);
	     }

	    public Group getGroup(String groupName) {
	        for (int i = 0; i < groupCount; i++) {
	            if (groups[i].getGroupName().equals(groupName)) {
	                return groups[i];
	            }
	        }
	        return null;
	    }

	    public void updateGroup(String groupName, String newDescription) {
	        Group group = getGroup(groupName);
	        if (group != null) {
	            group.setGroupDescription(newDescription);
	        }
	    }

	    public void deleteGroup(String groupName) {
	        for (int i = 0; i < groupCount; i++) {
	            if (groups[i].getGroupName().equals(groupName)) {
	                groups[i] = groups[groupCount - 1]; // Replace with the last group
	                groups[groupCount - 1] = null;
	                groupCount--;
	                break;
	            }
	        }
	    }

	    public Group[] getAllGroups() {
	        return groups;
	    }
		
	}