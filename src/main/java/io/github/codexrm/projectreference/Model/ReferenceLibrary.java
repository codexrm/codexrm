package io.github.codexrm.projectreference.Model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class ReferenceLibrary {

  private final Hashtable<Integer, Reference> referenceTable;

  public ReferenceLibrary() {
    referenceTable = new Hashtable<>();
  }
  public Reference addEmptyReference(AuthorLibrary authorLibrary) {
    int id = maxValueKeys() + 1;
    BookReference reference = new BookReference();
    reference.setAuthorLibrary(authorLibrary);
    reference.setId(id);
    reference.setTitle("No Title");
    reference.setAuthor("lastName,Name;lastName2,Name2");
    reference.setYear("0");
    referenceTable.put(id, reference);

    return reference;
  }
  
  public Reference getReference(int id) {
    return referenceTable.get(id);
}
  
  public Hashtable<Integer,Reference> getReferenceTable() {
    return referenceTable;
}

  public void  addListReference(ArrayList<Reference> list) {
    for (Reference reference : list) {
      referenceTable.put(reference.getId(),reference);
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
      if (keyMax < keyValue){
        keyMax = keyValue;
      }
    }
    return keyMax;
  }
  public void addAuthorLibraryToReference(AuthorLibrary authorLibrary) {
    Enumeration<Integer> e = referenceTable.keys();
    Integer keyReference;
    Reference valorReference;

    while (e.hasMoreElements()) {
      keyReference = e.nextElement();
      valorReference = referenceTable.get(keyReference);
      valorReference.setAuthorLibrary(authorLibrary);
    }
}
   
}
