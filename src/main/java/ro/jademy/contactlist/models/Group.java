package ro.jademy.contactlist.models;

public enum Group {

    FAMILY("FAMILY"),
    FRIENDS("FRIENDS"),
    WORK("WORK"),
    FAVORITE("FAVORITE"),
    MY_CONTACTS("MY CONTACTS");
    private final String groupName;

    Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    static public Group lookup(String string, Group defaultValue) {
        try {
            return Group.valueOf(string);
        } catch (IllegalArgumentException ex) {
            return defaultValue;
        }
    }
}
