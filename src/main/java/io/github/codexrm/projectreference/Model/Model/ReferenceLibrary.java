package io.github.codexrm.projectreference.Model.Model;

import java.util.*;

public class ReferenceLibrary {

    private final Hashtable<Integer, Reference> referenceTable;

    public ReferenceLibrary() {
        referenceTable = new Hashtable<>();
    }

    public Reference addEmptyReference() {
        int id = maxValueKeys() + 1;
        BookReference reference = new BookReference();
        reference.setId(id);
        reference.setTitle("No Title");
        reference.setAuthorIdList(null);
        reference.setDate(null);
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