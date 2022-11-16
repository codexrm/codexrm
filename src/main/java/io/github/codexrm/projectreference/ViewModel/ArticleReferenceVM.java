package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.model.model.ArticleReference;
import javafx.beans.property.*;


public class ArticleReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty journal;
    private StringProperty volume;
    private StringProperty number;
    private StringProperty pages;

    public ArticleReferenceVM() {
        super();
        createEmptyArticleReferenceVM();
    }

    public ArticleReferenceVM(ArticleReference articleReference) {
        super(articleReference.getId(),articleReference.getAuthor(), articleReference.getTitle(), articleReference.getLocalDate(), articleReference.getNote(), articleReference.isFromServer(), articleReference.isActive(), articleReference.isModified());

        createEmptyArticleReferenceVM();

        setJournal(articleReference.getJournal());
        setVolume(articleReference.getVolume());
        setNumber(articleReference.getNumber());
        setPages(articleReference.getPages());
    }

    private void createEmptyArticleReferenceVM() {
        this.journal = new SimpleStringProperty();
        this.volume = new SimpleStringProperty();
        this.number = new SimpleStringProperty();
        this.pages = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.ARTICLE);
    }

    public String getJournal() { return journal.get(); }

    public StringProperty journalProperty() { return journal; }

    public void setJournal(String journal) { this.journal.set(journal); }

    public String getVolume() { return volume.get(); }

    public StringProperty volumeProperty() { return volume; }

    public void setVolume(String volume) { this.volume.set(volume); }

    public String getNumber() { return number.get(); }

    public StringProperty numberProperty() { return number; }

    public void setNumber(String number) { this.number.set(number); }

    public String getPages() { return pages.get(); }

    public StringProperty pagesProperty() { return pages; }

    public void setPages(String pages) { this.pages.set(pages); }

    public ReferenceType getReferenceType() { return referenceType.get(); }

    public void setReferenceType(final ReferenceType referenceType) { this.referenceType.set(referenceType); }

    public ObjectProperty<ReferenceType> referenceTypeProperty() { return referenceType; }

    @Override
    public ArticleReference toModel() {

        ArticleReference articleReference = new ArticleReference();
        articleReference.setAuthor(this.getAuthor());
        articleReference.setTitle(this.getTitle());
        articleReference.setLocalDate(this.getDate());
        articleReference.setId(this.getId());
        articleReference.setNote(this.getNote());
        articleReference.setFromServer(this.isIsFromServer());
        articleReference.setModified(this.isIsModified());
        articleReference.setActive(this.isIsActive());

        articleReference.setJournal(this.getJournal());
        articleReference.setNumber(this.getNumber());
        articleReference.setVolume(this.getVolume());
        articleReference.setPages(this.getPages());

        return articleReference;
    }
}