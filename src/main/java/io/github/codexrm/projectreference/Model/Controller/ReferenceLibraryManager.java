package io.github.codexrm.projectreference.model.controller;

import io.github.codexrm.projectreference.model.Rest.*;
import io.github.codexrm.projectreference.model.enums.Format;
import io.github.codexrm.projectreference.model.model.*;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

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

    //Reference
    public void setReferenceLibrary(ReferenceLibrary referenceLibrary) { this.referenceLibrary = referenceLibrary; }

    public Reference getReference(int id) { return referenceLibrary.getReference(id); }

    public Hashtable<Integer, Reference> getReferenceTable() { return referenceLibrary.getReferenceTable(); }

    public void saveReferenceTable() throws IOException { libraryFiles.saveReferenceTable(referenceLibrary); }

    public boolean syncReferenceTable() throws IOException {
        verificateExpiationDate();
        ReferenceLibrary library = service.syncReferences (referenceLibrary.getReferenceTable(), referenceLibrary.getAuthenticationData());
        if( library != null){
            referenceLibrary = library;
            saveReferenceTable();
            return true;
        }
        else{return false;}
    }

    public void loadReferenceTable() throws IOException {
        File fileDBR = new File(dbReferenceName);
        if (fileDBR.exists() && fileDBR.isFile()) {
            setReferenceLibrary(libraryFiles.loadReferenceTable(dbReferenceName));
        } else {
            saveReferenceTable();
        }
    }

    public void exportReferenceList(ArrayList<Reference> referenceList, Format format) throws IOException {
        exportR.exportReferenceList(new File( System.getProperty("user.home") + File. separator +"Downloads" + File. separator + "exported References.txt"), referenceList, format);
    }

    public void importReferences(String path, Format format) throws IOException, TokenMgrException, ParseException {
        referenceLibrary.addListReference(importR.importReferences(path,format));
    }

    //User
    public boolean userLogin(UserLogin userLogin) {
        AuthenticationData authenticationData = service.login(userLogin);
        if(authenticationData == null){
            return false;
        }else{
            referenceLibrary.setAuthenticationData(authenticationData);
            return true;
        }
    }

    public boolean userLogout() throws ExecutionException, InterruptedException, IOException {

        if(referenceLibrary.getAuthenticationData().getToken() == null){
           return true;
        }else{
            if(referenceLibrary.getAuthenticationData().getRefreshToken() != null)
                verificateExpiationDate();
            if (service.logout(referenceLibrary.getAuthenticationData().getToken())){
                referenceLibrary.getAuthenticationData().setToken(null);
                referenceLibrary.getAuthenticationData().setRefreshToken(null);
                referenceLibrary.getAuthenticationData().setTokenExpirationDate(null);
                referenceLibrary.getAuthenticationData().setRefreshTokenExpirationDate(new Date());
                saveReferenceTable();
                return true;
            }
            else{ return false; }
        }
    }

    public boolean verificateAutentication() {

        boolean isAuthentication = true;
        if(referenceLibrary.getAuthenticationData().getUsername().equals("guest")){
            isAuthentication = false;
        }
        else{
            if(referenceLibrary.getAuthenticationData().getRefreshTokenExpirationDate().before(new Date())){
                isAuthentication = false;
            }
        }
        return isAuthentication;
    }

    private void verificateExpiationDate() throws IOException {
        if(referenceLibrary.getAuthenticationData().getTokenExpirationDate().before(new Date())){
            refreshToken();
        }
    }

    private void refreshToken() throws IOException {
        TokenRefreshResponse response = service.refreshToken(new TokenRefreshRequest(referenceLibrary.getAuthenticationData().getRefreshToken()));
        referenceLibrary.getAuthenticationData().setToken(response.getTokenType()  + " " + response.getAccessToken());
        referenceLibrary.getAuthenticationData().setRefreshToken(response.getRefreshToken());
        referenceLibrary.getAuthenticationData().setTokenExpirationDate(response.getTokenExpirationDate());
        saveReferenceTable();
    }
}
