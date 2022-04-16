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
    private LibraryFiles libraryFiles;
    private static String dbReferenceName = "testFile//referenceL.txt";
    private final ExportFactory exportFactory;
    private final ImportFactory importFactory;


    private ReferenceLibraryManager() {
        referenceLibrary = new ReferenceLibrary();
        libraryFiles = new LibraryFiles(dbReferenceName);
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

    public LibraryFiles getLibraryFiles() {
        return libraryFiles;
    }

    public void setLibraryFiles(LibraryFiles libraryFiles) {
        this.libraryFiles = libraryFiles;
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

    public void saveReferenceTable() throws IOException {
        libraryFiles.saveReferenceTable(referenceLibrary);
    }

    public void loadReferenceTable() throws IOException {
        File fileDBR = new File(dbReferenceName);
        if (fileDBR.exists() && fileDBR.isFile()) {
            setReferenceLibrary(libraryFiles.loadReferenceTable(dbReferenceName));
        } else {
            saveReferenceTable();
        }

    }

    public void deleteReferences(ArrayList<Integer> referenceIdList) throws IOException {
        for (Integer id : referenceIdList) {
            referenceLibrary.getReferenceTable().remove(id);
        }
        saveReferenceTable();
    }

    public void updateReference(Reference reference) throws IOException {


            referenceLibrary.getReferenceTable().remove(reference.getId());
            referenceLibrary.getReferenceTable().put(reference.getId(), reference);
            saveReferenceTable();
    }

    public Reference replaceReferenceType(@NotNull Reference newReference) throws IOException {
        if (referenceLibrary.getReference(newReference.getId()) != null) {

            Reference removedReference = referenceLibrary.getReferenceTable().remove(newReference.getId());
            newReference.setAuthor(removedReference.getAuthor());
            newReference.setTitle(removedReference.getTitle());
            newReference.setLocalDate(removedReference.getLocalDate());
            newReference.setNote(removedReference.getNote());
            newReference.setId(removedReference.getId());
            referenceLibrary.getReferenceTable().put(newReference.getId(), newReference);
        }
        saveReferenceTable();
        return newReference;
    }

    public void exportReference(String path, Reference reference, Format format) throws IOException {

        Export export = exportFactory.getExport(format);
        export.writeValue(reference, path);
    }

    public void exportReferenceList(File file, ArrayList<Reference> referenceList, Format format) throws IOException {

        Export export = exportFactory.getExport(format);
        export.writeValue(referenceList,file.getPath());
    }

    public void importReferences(String path, Format format)
            throws IOException, TokenMgrException, ParseException {

        Import importer = importFactory.getImport(format);
        referenceLibrary.addListReference(importer.readFile(path));

    }
}
