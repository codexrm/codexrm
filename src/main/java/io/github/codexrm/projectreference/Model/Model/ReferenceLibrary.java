package io.github.codexrm.projectreference.model.model;

import java.util.*;

public class ReferenceLibrary {

    private final Hashtable<Integer, Reference> referenceTable;
    private AuthenticationData authenticationData;
    private User user;

    public ReferenceLibrary() {
        referenceTable = new Hashtable<>();
        authenticationData = new AuthenticationData( 0, "guest", "guest", "guest", "guest", false,  null, null);
        user = new User();
    }

    public ReferenceLibrary(Hashtable<Integer, Reference> referenceTable, AuthenticationData authenticationData, User user) {
        this.referenceTable = referenceTable;
        this.authenticationData = authenticationData;
        this.user = user;
    }

    public AuthenticationData getAuthenticationData() { return authenticationData; }

    public void setAuthenticationData(AuthenticationData authenticationData) { this.authenticationData = authenticationData; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Reference addEmptyReference() {
        int id = maxValueKeys() + 1;
        BookReference reference = new BookReference();
        reference.setId(id);
        reference.setTitle("No Title");
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