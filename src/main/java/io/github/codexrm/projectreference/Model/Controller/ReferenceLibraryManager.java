package io.github.codexrm.projectreference.model.controller;

import io.github.codexrm.projectreference.model.enums.Format;
import io.github.codexrm.projectreference.model.model.*;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class ReferenceLibraryManager {

    private static ReferenceLibraryManager manager;
    private ReferenceLibrary referenceLibrary;
    private final LibraryFiles libraryFiles;
    private static final String dbReferenceName = "testFile//referenceL.txt";
    private final Service service;
    private final ExportR exportR;
    private final ImportR importR;

    private ReferenceLibraryManager() {
        referenceLibrary = new ReferenceLibrary();
        libraryFiles = new LibraryFiles(dbReferenceName);
        service = new Service();
        exportR = new ExportR();
        importR = new ImportR();
    }

    public static ReferenceLibraryManager getReferenceLibraryManager() {
        if (manager == null) {
            manager = new ReferenceLibraryManager();
        }
        return manager;
    }

    public void setReferenceLibrary(ReferenceLibrary referenceLibrary) { this.referenceLibrary = referenceLibrary; }

    public Reference getReference(int id) { return referenceLibrary.getReference(id); }

    public Hashtable<Integer, Reference> getReferenceTable() { return referenceLibrary.getReferenceTable(); }

    public void saveReferenceTable() throws IOException { libraryFiles.saveReferenceTable(referenceLibrary); }

    public void syncReferenceTable() throws IOException {

       referenceLibrary = service.syncReferences (referenceLibrary.getReferenceTable(), referenceLibrary.getAuthenticationData(), referenceLibrary.getUser());
        saveReferenceTable();
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
        reference.setModified(true);
        reference.setFromServer(true);
        reference.setActive(true);
        referenceLibrary.getReferenceTable().put(reference.getId(), reference);
        saveReferenceTable();
    }

    public Reference replaceReferenceType(@NotNull Reference newReference) throws IOException {

        if (referenceLibrary.getReference(newReference.getId()) != null) {
            Reference removedReference = referenceLibrary.getReferenceTable().remove(newReference.getId());
            newReference.setYear(removedReference.getYear());
            newReference.setTitle(removedReference.getTitle());
            newReference.setMonth(removedReference.getMonth());
            newReference.setNote(removedReference.getNote());
            newReference.setId(removedReference.getId());
            referenceLibrary.getReferenceTable().put(newReference.getId(), newReference);
        }
        saveReferenceTable();
        return newReference;
    }

    public void exportReferenceList(ArrayList<Reference> referenceList, Format format) throws IOException {

        exportR.exportReferenceList(new File( System.getProperty("user.home") + File. separator +"Desktop" + File. separator + "exported References.txt"), referenceList, format);
    }

    public void importReferences(String path, Format format) throws IOException, TokenMgrException, ParseException {

        referenceLibrary.addListReference(importR.importReferences(path,format));
    }

    public void userLogin(UserLogin userLogin) {
        referenceLibrary.setAuthenticationData(service.login(userLogin));
    }

    public boolean verificateAutentication() throws java.text.ParseException {
        if(referenceLibrary.getAuthenticationData().getUsername().equals("guest")){
            return false;
        }else{
            DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZ");
            Date date = format.parse(referenceLibrary.getAuthenticationData().getExpiredTime());
           Date now = new Date();

            if(date.compareTo(now) != -1){
                return false;
            }else{
                return true;
            }
        }
    }
}
