package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.Enum.Format;
import io.github.codexrm.projectreference.model.controller.ReferenceLibraryManager;
import io.github.codexrm.projectreference.model.model.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jbibtex.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

public class ReferenceLibraryManagerVM {

    private final ObservableList<ReferenceVM> referenceList;
    private final ObjectProperty<ReferenceVM> currentReference;
    private final ReferenceLibraryManager manager;
    private final BooleanProperty referenceTypeReplaced;

    public ReferenceLibraryManagerVM() throws IOException {
        referenceList = FXCollections.observableArrayList();

        currentReference = new SimpleObjectProperty<>();
        manager = ReferenceLibraryManager.getReferenceLibraryManager();
        referenceTypeReplaced = new SimpleBooleanProperty(false);

        loadDataFromModel();
    }

    public boolean isReferenceTypeReplaced() {
        return referenceTypeReplaced.get();
    }

    public AuthorLibrary getAuthorLibrary() {
        return manager.getAuthorLibrary();
    }

    public void setReferenceTypeReplaced(final boolean referenceTypeReplaced) {
        this.referenceTypeReplaced.set(referenceTypeReplaced);
    }

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
        manager.loadTables();
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
            if (valorReference.getClass() == ArticleReference.class) {
                referenceList.add(new ArticleReferenceVM((ArticleReference) valorReference, manager.getAuthorLibrary()));
            } else if (valorReference.getClass() == BookSectionReference.class) {
                referenceList.add(new BookSectionReferenceVM((BookSectionReference) valorReference,manager.getAuthorLibrary()));
            } else if (valorReference.getClass() == BookReference.class) {
                referenceList.add(new BookReferenceVM((BookReference) valorReference,manager.getAuthorLibrary()));
            } else if (valorReference.getClass() == BookLetReference.class) {
                referenceList.add(new BookLetReferenceVM((BookLetReference) valorReference,manager.getAuthorLibrary()));
            } else if (valorReference.getClass() == ConferenceProceedingsReference.class) {
                referenceList.add(new ConferenceProceedingsReferenceVM((ConferenceProceedingsReference) valorReference,manager.getAuthorLibrary()));
            } else if (valorReference.getClass() == ThesisReference.class) {
                referenceList.add(new ThesisReferenceVM((ThesisReference) valorReference,manager.getAuthorLibrary()));
            }
        }
    }
    public void addEmptyBookReference() throws IOException {

        int id = 0;
        Optional<ReferenceVM> maxReferenceId = referenceList.stream().max(comparing(ReferenceVM::getId));
        if (maxReferenceId.isPresent()) {
            id = maxReferenceId.get().getId() + 1;
        }

        BookReferenceVM reference = new BookReferenceVM();
        reference.setId(id);
        reference.setTitle("No Title");
        reference.setAuthor("lastName1,Name1;lastNameN,nameN...");
        referenceList.add(reference);
    }

    public void deleteReferences(List<ReferenceVM> referenceList) throws IOException {

        /*Get de references id to delete */
        List<Integer> referenceIdList = new ArrayList<>();
        for (ReferenceVM reference : referenceList) {
            referenceIdList.add(reference.getId());
        }

        /*Delete reference from view-model*/
        for (Integer id : referenceIdList) {
            this.referenceList.removeIf(reference -> reference.getId() == id);
        }
    }
    public void exportReferenceList(File file, List<ReferenceVM> referenceVMList, Format format) throws IOException {
        ArrayList<Reference> referenceList = new ArrayList<>();
        for (ReferenceVM referenceVM : referenceVMList) {
            referenceList.add(referenceVM.toModel());
        }
        manager.exportReferenceList(file, referenceList,format);
    }
    public void importReferences(List<File> fileList, Format format) throws IOException, ParseException {
        for (File file: fileList) {
            manager.importReferences(file.getPath(), format);
        }
        syncViewModel();
    }

    public void replaceCurrentReferenceType(ReferenceType newReferenceType) {

        /*Update reference type from view-model*/
        for (ReferenceVM ref : referenceList) {
            if (ref.getId() == getCurrentReference().getId()) {
                ReferenceVM referenceVM = getReferenceObject(newReferenceType);
                referenceVM.setId(getCurrentReference().getId());
                referenceVM.setTitle(getCurrentReference().getTitle());
                referenceVM.setAuthor(getCurrentReference().getAuthor());
                referenceVM.setDate(getCurrentReference().getDate());
                referenceVM.setNote(getCurrentReference().getNote());

                int referenceIndex = referenceList.indexOf(ref);
                referenceList.set(referenceIndex, referenceVM);

                referenceTypeReplaced.set(true);
                break;
            }
        }
    }

    public void saveDataToModel() throws IOException {
        manager.getReferenceTable().clear();

        for (ReferenceVM reference : referenceList) {
            reference.setAuthorLibrary(manager.getAuthorLibrary());
            manager.getReferenceTable().put(reference.getId(),reference.toModel());
        }
        manager.saveTables();
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
        }
        return new ReferenceVM();
    }
}
