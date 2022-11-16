package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.model.model.WebPageReference;
import javafx.beans.property.*;

import java.time.LocalDate;

public class WebPageReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private ObjectProperty<LocalDate> accessDate;
    private StringProperty url;

    public WebPageReferenceVM() {
        super();
        createEmptyWebPageReferenceVM();
    }

    public WebPageReferenceVM(WebPageReference webPageReference) {
        super(webPageReference.getId(),webPageReference.getAuthor(), webPageReference.getTitle(), webPageReference.getLocalDate(), webPageReference.getNote(), webPageReference.isFromServer(), webPageReference.isActive(), webPageReference.isModified());

        createEmptyWebPageReferenceVM();

        setAccessDate(webPageReference.getAccessDateLocal());
        setUrl(webPageReference.getUrl());
    }

    private void createEmptyWebPageReferenceVM() {

        this.accessDate = new SimpleObjectProperty();
        this.url = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.WEBPAGE);
    }

    public ReferenceType getReferenceType() { return referenceType.get(); }

    public ObjectProperty<ReferenceType> referenceTypeProperty() { return referenceType; }

    public void setReferenceType(ReferenceType referenceType) { this.referenceType.set(referenceType); }

    public LocalDate getAccessDate() { return accessDate.get(); }

    public ObjectProperty<LocalDate> accessDateProperty() { return accessDate; }

    public void setAccessDate(LocalDate accessDate) { this.accessDate.set(accessDate); }

    public String getUrl() { return url.get(); }

    public StringProperty urlProperty() { return url; }

    public void setUrl(String url) { this.url.set(url); }

    @Override
    public WebPageReference toModel() {

        WebPageReference webPageReference = new WebPageReference();
        webPageReference.setAuthor(this.getAuthor());
        webPageReference.setTitle(this.getTitle());
        webPageReference.setLocalDate(this.getDate());
        webPageReference.setId(this.getId());
        webPageReference.setNote(this.getNote());
        webPageReference.setFromServer(this.isIsFromServer());
        webPageReference.setModified(this.isIsModified());
        webPageReference.setActive(this.isIsActive());

        webPageReference.setAccessDateLocal(this.getAccessDate());
        webPageReference.setUrl(this.getUrl());

        return webPageReference;
    }
}
