package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.model.model.WebPageReference;
import javafx.beans.property.*;

public class WebPageReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty author;
    private StringProperty url;

    public WebPageReferenceVM() {
        super();
        createEmptyWebPageReferenceVM();
    }

    public WebPageReferenceVM(WebPageReference webPageReference) {
        super(webPageReference.getId(), webPageReference.getTitle(), webPageReference.getYear(), webPageReference.getMonth(), webPageReference.getNote(), webPageReference.isFromServer(),
                webPageReference.isActive(), webPageReference.isModified());

        createEmptyWebPageReferenceVM();

        setAuthor(webPageReference.getAuthor());
        setUrl(webPageReference.getUrl());
    }

    private void createEmptyWebPageReferenceVM() {

        this.author = new SimpleStringProperty();
        this.url = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.WEBPAGE);
    }

    public String getAuthor() { return author.get(); }

    public StringProperty authorProperty() { return author; }

    public void setAuthor(String author) { this.author.set(author); }

    public String getUrl() { return url.get(); }

    public StringProperty urlProperty() { return url; }

    public void setUrl(String url) { this.url.set(url); }

    public ReferenceType getReferenceType() { return referenceType.get(); }

    public ObjectProperty<ReferenceType> referenceTypeProperty() { return referenceType; }

    public void setReferenceType(ReferenceType referenceType) { this.referenceType.set(referenceType); }

    @Override
    public WebPageReference toModel() {

        WebPageReference webPageReference = new WebPageReference();

        webPageReference.setTitle(this.getTitle());
        webPageReference.setYear(this.getYear());
        webPageReference.setMonth(this.getMonth());
        webPageReference.setId(this.getId());
        webPageReference.setNote(this.getNote());
        webPageReference.setFromServer(this.isIsFromServer());
        webPageReference.setModified(this.isIsModified());
        webPageReference.setActive(this.isIsActive());

        webPageReference.setAuthor(this.getAuthor());
        webPageReference.setUrl(this.getUrl());

        return webPageReference;
    }
}
