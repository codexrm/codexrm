package io.github.codexrm.projectreference.Model.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import io.github.codexrm.projectreference.Model.Enum.Format;
import io.github.codexrm.projectreference.Model.Model.*;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

public class ReferenceLibraryManager {

    private static ReferenceLibraryManager manager;
    private ReferenceLibrary referenceLibrary;
    private AuthorLibrary authorLibrary;
    private LibraryFiles libraryFiles;
    private static String dbAuthorName = "testFile//authorL.txt";
    private static String dbReferenceName = "testFile//referenceL.txt";
    private final ExportFactory exportFactory;
    private final ImportFactory importFactory;


    private ReferenceLibraryManager() {
        referenceLibrary = new ReferenceLibrary();
        authorLibrary = new AuthorLibrary();
        libraryFiles = new LibraryFiles(dbReferenceName, dbAuthorName);
        exportFactory = new ExportFactory();
        importFactory = new ImportFactory();
    }

    public static ReferenceLibraryManager getReferenceLibraryManager() {
        if (manager == null) {
            manager = new ReferenceLibraryManager();
        }
        return manager;
    }

    public ReferenceLibrary getReferenceLibrary() {
        return referenceLibrary;
    }

    public void setReferenceLibrary(ReferenceLibrary referenceLibrary) {
        this.referenceLibrary = referenceLibrary;
    }

    public AuthorLibrary getAuthorLibrary() {
        return authorLibrary;
    }

    public void setAuthorLibrary(AuthorLibrary authorLibrary) {
        this.authorLibrary = authorLibrary;
    }

    public LibraryFiles getLibraryFiles() {
        return libraryFiles;
    }

    public void setLibraryFiles(LibraryFiles libraryFiles) {
        this.libraryFiles = libraryFiles;
    }

    public ArrayList<Integer> addAuthor(String lineAuthor) {
        return authorLibrary.createAuthor(lineAuthor);
    }


    public Author readAuthor(Integer id) {
        return authorLibrary.readAuthor(id);
    }

    public Reference addEmptyBookReference() {
        return referenceLibrary.addEmptyReference(authorLibrary);
    }

    public Reference getReference(int id) {
        return referenceLibrary.getReference(id);
    }

    public Hashtable<Integer, Reference> getReferenceTable() {
        return referenceLibrary.getReferenceTable();
    }

    public void saveTables() throws IOException {
        libraryFiles.saveTables(referenceLibrary, authorLibrary);
    }

    public void loadTables() throws IOException {
        File fileDBA = new File(dbAuthorName);
        if (fileDBA.exists() && fileDBA.isFile()) {
            setAuthorLibrary(libraryFiles.loadAuthorTable(dbAuthorName));
        } else {
            saveTables();
        }
        File fileDBR = new File(dbReferenceName);
        if (fileDBR.exists() && fileDBR.isFile()) {
            setReferenceLibrary(libraryFiles.loadReferenceTable(dbReferenceName));
            referenceLibrary.addAuthorLibraryToReference(authorLibrary);
        } else {
            saveTables();
        }

    }

    public void deleteReferences(ArrayList<Integer> referenceIdList) throws IOException {
        for (Integer id : referenceIdList) {
            referenceLibrary.getReferenceTable().remove(id);
        }
        saveTables();
    }

    public void updateReference(Reference reference) throws IOException {

        if (referenceLibrary.getReferenceTable().equals(reference.getId())) {
            referenceLibrary.getReferenceTable().remove(reference.getId());
            referenceLibrary.getReferenceTable().put(reference.getId(), reference);
            saveTables();
        }
    }

    public Reference replaceReferenceType(Reference newReference) throws IOException {


        if (referenceLibrary.getReference(newReference.getId()) != null) {

            Reference removedReference = referenceLibrary.getReferenceTable().remove(newReference.getId());
            newReference.setAuthorLibrary(authorLibrary);
            newReference.setAuthor(removedReference.getAuthor());
            newReference.setTitle(removedReference.getTitle());
            newReference.setDate(removedReference.getDate());
            newReference.setNote(removedReference.getNote());
            newReference.setId(removedReference.getId());
            referenceLibrary.getReferenceTable().put(newReference.getId(), newReference);
        }
        saveTables();
        return newReference;
    }

    public void exportReference(String path, Reference reference, Format format) throws IOException {

        Export export = exportFactory.getExport(format, authorLibrary);
        export.writeValue(reference, path);
    }

    public void exportReferenceTable(String path, Format format) throws IOException {

        Export export = exportFactory.getExport(format, authorLibrary);
        export.writeValue(referenceLibrary.getReferenceTable(), path);
    }

    public void importReferences(String path, Format format)
            throws IOException, TokenMgrException, ParseException {

        Import importer = importFactory.getImport(format, authorLibrary);
        referenceLibrary.addListReference(importer.readFile(path));

    }
}
