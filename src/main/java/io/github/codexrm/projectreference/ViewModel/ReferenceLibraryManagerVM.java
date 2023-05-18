package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.controller.ReferenceLibraryManager;
import io.github.codexrm.projectreference.model.enums.*;
import io.github.codexrm.projectreference.model.model.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jbibtex.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.util.Comparator.comparing;

public class ReferenceLibraryManagerVM {

    private final ObservableList<ReferenceVM> referenceList;
    private final ObjectProperty<ReferenceVM> currentReference;
    private final ReferenceLibraryManager manager;
    private final BooleanProperty referenceTypeReplaced;
    private  ArrayList<ReferenceVM> referenceDeleteList;
    private UserLoginVM userLoginVM;

    public ReferenceLibraryManagerVM() throws IOException {
        referenceList = FXCollections.observableArrayList();

        currentReference = new SimpleObjectProperty<>();
        manager = ReferenceLibraryManager.getReferenceLibraryManager();
        referenceTypeReplaced = new SimpleBooleanProperty(false);
        referenceDeleteList = new ArrayList<>();
        userLoginVM = new UserLoginVM();

        loadDataFromModel();
    }

    public boolean isReferenceTypeReplaced() {
        return referenceTypeReplaced.get();
    }

    public void setReferenceTypeReplaced(final boolean referenceTypeReplaced) { this.referenceTypeReplaced.set(referenceTypeReplaced); }

    public BooleanProperty referenceTypeReplacedProperty() {
        return referenceTypeReplaced;
    }

    public ObservableList<ReferenceVM> getReferenceList() {
        return referenceList;
    }

    public ReferenceVM getCurrentReference() {
        return currentReference.get();
    }

    public void setCurrentReference(final ReferenceVM currentReference) {
        this.currentReference.set(currentReference);
    }

    public ObjectProperty<ReferenceVM> currentReferenceProperty() {
        return currentReference;
    }

    public void loadDataFromModel() throws IOException {

        manager.loadReferenceTable();
        syncViewModel();
    }

    private void syncViewModel() {

        referenceList.clear();

        Enumeration<Integer> e = manager.getReferenceTable().keys();
        Integer keyReference;
        Reference valorReference;

        while (e.hasMoreElements()) {
            keyReference = e.nextElement();
            valorReference = manager.getReferenceTable().get(keyReference);
            if(valorReference.isActive()){
            if (valorReference.getClass() == ArticleReference.class) {
                referenceList.add(new ArticleReferenceVM((ArticleReference) valorReference));
            } else if (valorReference.getClass() == BookSectionReference.class) {
                referenceList.add(new BookSectionReferenceVM((BookSectionReference) valorReference));
            } else if (valorReference.getClass() == BookReference.class) {
                referenceList.add(new BookReferenceVM((BookReference) valorReference));
            } else if (valorReference.getClass() == BookLetReference.class) {
                referenceList.add(new BookLetReferenceVM((BookLetReference) valorReference));
            } else if (valorReference.getClass() == ConferenceProceedingsReference.class) {
                referenceList.add(new ConferenceProceedingsReferenceVM((ConferenceProceedingsReference) valorReference));
            } else if (valorReference.getClass() == ThesisReference.class) {
                referenceList.add(new ThesisReferenceVM((ThesisReference) valorReference));
            } else if (valorReference.getClass() == ConferencePaperReference.class) {
                referenceList.add(new ConferencePaperReferenceVM((ConferencePaperReference) valorReference));
            } else if (valorReference.getClass() == WebPageReference.class) {
                referenceList.add(new WebPageReferenceVM((WebPageReference) valorReference));
            }
            }
        }
    }

    public void addEmptyBookReference() {

        int id = 0;
        Optional<ReferenceVM> maxReferenceId = referenceList.stream().max(comparing(ReferenceVM::getId));
        if (maxReferenceId.isPresent()) {
            id = maxReferenceId.get().getId() + 1;
        }
        BookReferenceVM reference = new BookReferenceVM();
        reference.setId(id);
        reference.setTitle("No Title");
        reference.setYear("No Year");
        reference.setIsActive(true);

        referenceList.add(reference);
    }

    public void deleteReferences(List<ReferenceVM> referenceList) {

        /*Get de references id to delete */
        List<Integer> referenceIdList = new ArrayList<>();
        for (ReferenceVM reference : referenceList) {
            if(reference.isIsFromServer()){
                reference.setIsActive(false);
                referenceDeleteList.add(reference);
            }
            referenceIdList.add(reference.getId());
        }

        /*Delete reference from view-model*/
        for (Integer id : referenceIdList) {
            this.referenceList.removeIf(reference -> reference.getId() == id);
        }
    }

    public void exportReferenceList(List<ReferenceVM> referenceVMList, Format format) throws IOException {

       ArrayList<Reference> list = new ArrayList<>();
        for (ReferenceVM referenceVM : referenceVMList) {
            list.add(referenceVM.toModel());
        }
        manager.exportReferenceList(list, format);
    }

    public void importReferences(List<File> fileList, Format format) throws IOException, ParseException {

        for (File file: fileList) {
            manager.importReferences(file.getPath(), format);
        }
        syncViewModel();
    }

    public void syncDB() throws IOException {

        saveDataToModel();
        manager.syncReferenceTable();
        syncViewModel();
    }

    public void userLogin(UserLoginVM user) { manager.userLogin(user.toModel()); }

    public boolean verificateAutentication() throws java.text.ParseException { return manager.verificateAutentication(); }

    public void replaceCurrentReferenceType(ReferenceType newReferenceType) {

        /*Update reference type from view-model*/
        for (ReferenceVM ref : referenceList) {
            if (ref.getId() == getCurrentReference().getId()) {
                ReferenceVM referenceVM = getReferenceObject(newReferenceType);
                referenceVM.setId(getCurrentReference().getId());
                referenceVM.setTitle(getCurrentReference().getTitle());
                referenceVM.setYear(getCurrentReference().getYear());
                referenceVM.setMonth(getCurrentReference().getMonth());
                referenceVM.setNote(getCurrentReference().getNote());
                referenceVM.setIsFromServer(getCurrentReference().isIsFromServer());
                referenceVM.setIsActive(getCurrentReference().isIsActive());
                referenceVM.setIsModified(getCurrentReference().isIsModified());

                int referenceIndex = referenceList.indexOf(ref);
                referenceList.set(referenceIndex, referenceVM);

                referenceTypeReplaced.set(true);
                break;
            }
        }
    }

    public void saveDataToModel() throws IOException {

        for(ReferenceVM referenceVM: referenceList){
            if (referenceVM.isIsFromServer() && referenceVM.isIsActive()) {
                Reference reference = manager.getReference(referenceVM.getId());
                if (!reference.equals(referenceVM.toModel())) {
                    referenceVM.setIsModified(true);
                }
            }
        }

        manager.getReferenceTable().clear();

        for (ReferenceVM reference : referenceList) {
            manager.getReferenceTable().put(reference.getId(),reference.toModel());
        }
        for (ReferenceVM reference : referenceDeleteList) {
            manager.getReferenceTable().put(reference.getId(),reference.toModel());
        }
        manager.saveReferenceTable();
    }

    public ReferenceVM getReferenceObject(ReferenceType referenceType) {
        switch (referenceType) { // Aqui se podria hacer un factory que devuelva un objeto dado el tipo
            case ARTICLE:
                return new ArticleReferenceVM();
            case BOOK:
                return new BookReferenceVM();
            case BOOKSECTION:
                return new BookSectionReferenceVM();
            case BOOKLET:
                return new BookLetReferenceVM();
            case CONFERENCEPROCEEDINGS:
                return new ConferenceProceedingsReferenceVM();
            case THESIS:
                return new ThesisReferenceVM();
            case CONFERENCEPAPER:
                return new ConferencePaperReferenceVM();
            case WEBPAGE:
                return new WebPageReferenceVM();
        }
        return new ReferenceVM();
    }
}
