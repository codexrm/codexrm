package io.github.codexrm.projectreference.Model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class AuthorLibrary {

  private Hashtable<Integer, Author> authorTable;

  public AuthorLibrary() {
    super();
    authorTable = new Hashtable<>();
  }

  public Hashtable<Integer, Author> getAuthorTable() {
    return authorTable;
  }

  public void setAuthorTable(Hashtable<Integer, Author> authorTable) {
    this.authorTable = authorTable;
  }

  private int idAuthor(DataAuthor data) {
    int id = -1;
    Enumeration<Integer> e = authorTable.keys();
    Integer keyAuthor;
    Author valorAuthor;
    while (e.hasMoreElements()) {
      keyAuthor = e.nextElement();
      valorAuthor = authorTable.get(keyAuthor);
      if ((valorAuthor.getDataAuthor().getName().equals(data.getName()))
          && (valorAuthor.getDataAuthor().getLastName().equals(data.getLastName()))) {
        id = valorAuthor.getId();
        break;
      }
    }
    return id;
  }

  public ArrayList<Integer> addAuthor(ArrayList<DataAuthor> listDataAuthor) {
    ArrayList<Integer> listId = new ArrayList<>();
    for (int i = 0; i < listDataAuthor.size(); i++) {
      int id = idAuthor(listDataAuthor.get(i));
      if (id == -1) {
        int nextId = maxValueKeys() + 1;
        Author author = new Author(listDataAuthor.get(i), nextId);
        authorTable.put(nextId, author);
        listId.add(nextId);
      } else {
        listId.add(id);
      }
    }
    return listId;
  }

  public int addAuthor(DataAuthor dataAuthor) {
    int id = idAuthor(dataAuthor);
    if (id == -1) {
      int nextId = maxValueKeys() + 1;
      Author author = new Author(dataAuthor, nextId);
      authorTable.put(nextId, author);
      id = nextId;
    }
    return id;
  }

  public ArrayList<Author> readAuthorList(ArrayList<Integer> listId) {
    ArrayList<Author> listAuthor = new ArrayList<>();

    for (int i = 0; i < listId.size(); i++) {
      listAuthor.add(readAuthor(listId.get(i)));
    }
    return listAuthor;
  }

  public Author readAuthor(Integer id) {
    return authorTable.get(id);
  }

  public String readAuthorsViewList(ArrayList<Integer> listId) {
    String authorsView = "";

    for (int i = 0; i < listId.size(); i++) {
      Author author = readAuthor(listId.get(i));
      if (i != 0) {
        authorsView = authorsView + "; " + author.getDataAuthor().getLastName() + ", "
            + author.getDataAuthor().getName();
      } else {
        authorsView = authorsView + author.getDataAuthor().getLastName() + ", "
            + author.getDataAuthor().getName();
      }
    }

    return authorsView;
  }

  public ArrayList<Integer> createAuthor(String authorsLine) {

    ArrayList<Integer> listIdAuthor = new ArrayList<>();
    String[] arrayAuthors = authorsLine.split(";", 5);

    for (int i = 0; i < arrayAuthors.length; i++) {
      String[] author = arrayAuthors[i].split(",", 2);
      DataAuthor data = new DataAuthor();
      data.setName(author[1].trim());
      data.setLastName(author[0].trim());
      listIdAuthor.add(addAuthor(data));
    }
    return listIdAuthor;
  }

  private Integer maxValueKeys() {
    Enumeration<Integer> e = authorTable.keys();
    ArrayList<Integer> listkeys = new ArrayList<>();
    while (e.hasMoreElements()) {
      listkeys.add(e.nextElement());
    }
    int maxKey = -1;
    for (int i = 0; i < listkeys.size(); i++) {
      if (maxKey < listkeys.get(i)) {
        maxKey = listkeys.get(i);
      }
    }
    return maxKey;
  }
}
