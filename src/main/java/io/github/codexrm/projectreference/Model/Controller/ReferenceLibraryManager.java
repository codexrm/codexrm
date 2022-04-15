package io.github.codexrm.projectreference.model.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import io.github.codexrm.projectreference.model.Enum.Format;
import io.github.codexrm.projectreference.model.model.*;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import org.jetbrains.annotations.NotNull;

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
        return referenceLibrary.addEmptyReference();
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


            referenceLibrary.getReferenceTable().remove(reference.getId());
            referenceLibrary.getReferenceTable().put(reference.getId(), reference);
            saveTables();
    }

    public Reference replaceReferenceType(@NotNull Reference newReference) throws IOException {
        if (referenceLibrary.getReference(newReference.getId()) != null) {

            Reference removedReference = referenceLibrary.getReferenceTable().remove(newReference.getId());
            newReference.setAuthorIdList(removedReference.getAuthorIdList());
            newReference.setTitle(removedReference.getTitle());
            newReference.setLocalDate(removedReference.getLocalDate());
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

    public void exportReferenceList(File file, ArrayList<Reference> referenceList, Format format) throws IOException {

        Export export = exportFactory.getExport(format, authorLibrary);
        export.writeValue(referenceList,file.getPath());
    }

    public void importReferences(String path, Format format)
            throws IOException, TokenMgrException, ParseException {

        Import importer = importFactory.getImport(format, authorLibrary);
        referenceLibrary.addListReference(importer.readFile(path));

    }
}
