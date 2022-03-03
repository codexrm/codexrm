package io.github.codexrm.projectreference.ViewModel;

import io.github.codexrm.projectreference.Model.Controller.ReferenceLibraryManager;
import io.github.codexrm.projectreference.Model.Model.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

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
            if (valorReference instanceof BookReference) {
                referenceList.add(new BookReferenceVM((BookReference) valorReference, manager.getAuthorLibrary()));
            } else if (valorReference instanceof ArticleReference) {
                referenceList.add(new ArticleReferenceVM((ArticleReference) valorReference,manager.getAuthorLibrary()));
            } else if (valorReference instanceof BookSectionReference) {
                referenceList.add(new BookSectionReferenceVM((BookSectionReference) valorReference,manager.getAuthorLibrary()));
            } else if (valorReference instanceof BookLetReference) {
                referenceList.add(new BookLetReferenceVM((BookLetReference) valorReference,manager.getAuthorLibrary()));
            } else if (valorReference instanceof ConferenceProceedingsReference) {
                referenceList.add(new ConferenceProceedingsReferenceVM((ConferenceProceedingsReference) valorReference,manager.getAuthorLibrary()));
            } else if (valorReference instanceof ThesisReference) {
                referenceList.add(new ThesisReferenceVM((ThesisReference) valorReference,manager.getAuthorLibrary()));
            }
        }
    }


    public void addEmptyBookReference() throws IOException {

        /*Add reference to model*/
        Reference reference = manager.addEmptyBookReference();

        /*Add reference to view-model*/
        referenceList.add(new BookReferenceVM((BookReference) reference,manager.getAuthorLibrary()));
    }

    public void deleteReferences(ArrayList<ReferenceVM> referenceList) throws IOException {

        /*Get de references id to delete */
        ArrayList<Integer> referenceIdList = new ArrayList<>();
        for (ReferenceVM reference : referenceList) {
            referenceIdList.add(reference.getId());
        }

        /*Delete reference from model*/
        manager.deleteReferences(referenceIdList);

        /*Delete reference from view-model*/
        for (Integer id : referenceIdList) {
            this.referenceList.removeIf(reference -> reference.getId() == id);
        }
    }

    public void updateReference(ReferenceVM referenceVM) throws IOException {

        /*Update reference from model*/
        manager.updateReference(referenceVM.toModel());

        /* No es necesario actualizar la vista en este caso, porque se actualiza directamente
         * cuando se modifica el valor en la interfaz*/
    }

    public void replaceReferenceType(ReferenceVM referenceVM) throws IOException {

        referenceVM.setAuthorLibrary(manager.getAuthorLibrary());
        Reference reference = manager.replaceReferenceType(referenceVM.toModel());

        referenceVM.setTitle(reference.getTitle());
        referenceVM.setAuthor(reference.getAuthorIdList());
        referenceVM.setDate(reference.getDate());
        referenceVM.setNote(reference.getNote());
        referenceVM.setAuthorLibrary(getAuthorLibrary());

        /*Update reference type from view-model*/
        for (ReferenceVM ref : referenceList) {
            if (ref.getId() == referenceVM.getId()) {
                int referenceIndex = referenceList.indexOf(ref);
                referenceList.set(referenceIndex, referenceVM);
                break;
            }
        }

        referenceTypeReplaced.set(true);
    }
}
