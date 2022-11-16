package io.github.codexrm.projectreference.model.model;

import java.util.*;

public class ReferenceLibrary {

    private final Hashtable<Integer, Reference> referenceTable;
    private  User user;

    public ReferenceLibrary() {
        referenceTable = new Hashtable<>();
        user = new User("marynes","123");
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public Reference addEmptyReference() {
        int id = maxValueKeys() + 1;
        BookReference reference = new BookReference();
        reference.setId(id);
        reference.setTitle("No Title");
        reference.setAuthor("lastName1,Name1;lastNameN,nameN...");
        reference.setLocalDate(null);
        reference.setFromServer(false);
        reference.setActive(true);
        referenceTable.put(id, reference);

        return reference;
    }

    public Reference getReference(int id) {
        return referenceTable.get(id);
    }

    public Hashtable<Integer, Reference> getReferenceTable() {
        return referenceTable;
    }

    public void addListReference(ArrayList<Reference> list) {

        for (Reference reference : list) {
            int id = maxValueKeys() + 1;
            reference.setId(id);
            reference.setFromServer(false);
            reference.setActive(true);
            referenceTable.put(reference.getId(), reference);
        }
    }

    public void addListReferenceSync(ArrayList<Reference> list) {

        referenceTable.clear();
        for (Reference reference : list) {
            referenceTable.put(reference.getId(), reference);
        }
    }

    private Integer maxValueKeys() {

        Enumeration<Integer> e = referenceTable.keys();
        ArrayList<Integer> listKeys = new ArrayList<>();
        while (e.hasMoreElements()) {
            listKeys.add(e.nextElement());
        }
        int keyMax = -1;
        for (Integer keyValue : listKeys) {
            if (keyMax < keyValue) {
                keyMax = keyValue;
            }
        }
        return keyMax;
    }
}