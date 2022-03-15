package io.github.codexrm.projectreference.Model.Model;

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

    private int authorId(String author) {
        int id = -1;
        Enumeration<Integer> e = authorTable.keys();
        Integer keyAuthor;
        Author valorAuthor;
        while (e.hasMoreElements()) {
            keyAuthor = e.nextElement();
            valorAuthor = authorTable.get(keyAuthor);
            if (valorAuthor.getAuthor().equals(author)) {
                id = valorAuthor.getId();
                break;
            }
        }
        return id;
    }

    public ArrayList<Integer> addAuthor(ArrayList<String> authorsList) {

        ArrayList<Integer> idList = new ArrayList<>();
        for (int i = 0; i < authorsList.size(); i++) {
            int id = authorId(authorsList.get(i));
            if (id == -1) {
                int nextId = maxValueKeys() + 1;
                Author author = new Author(authorsList.get(i), nextId);
                authorTable.put(nextId, author);
                idList.add(nextId);
            } else {
                idList.add(id);
            }
        }
        return idList;
    }

    public int addAuthor(String authorLine) {
        int id = authorId(authorLine);
        if (id == -1) {
            int nextId = maxValueKeys() + 1;
            Author author = new Author(authorLine, nextId);
            authorTable.put(nextId, author);
            id = nextId;
        }
        return id;
    }

    public ArrayList<Author> readAuthorList(ArrayList<Integer> idList) {
        ArrayList<Author> authorList = new ArrayList<>();

        for (int i = 0; i < idList.size(); i++) {
            authorList.add(readAuthor(idList.get(i)));
        }
        return authorList;
    }

    public Author readAuthor(Integer id) {
        return authorTable.get(id);
    }

    public String readAuthorsViewList(ArrayList<Integer> idList) {
        String viewAuthor = "";

        for (int i = 0; i < idList.size(); i++) {
            Author author = readAuthor(idList.get(i));
            if (i != 0) {
                viewAuthor = viewAuthor + "; " + author.getAuthor();
            } else {
                viewAuthor = viewAuthor + author.getAuthor();
            }
        }

        return viewAuthor;
    }

    public ArrayList<Integer> createAuthor(String authorsLine) {

        ArrayList<Integer> authorIdList = new ArrayList<>();
        String[] authorArrays = authorsLine.split(";", 5);

        for (int i = 0; i < authorArrays.length; i++) {
            authorIdList.add(addAuthor(authorArrays[i]));
        }
        return authorIdList;
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