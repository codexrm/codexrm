package io.github.codexrm.projectreference.ViewModel;

import io.github.codexrm.projectreference.Model.Model.ArticleReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Objects;

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

    public ArticleReferenceVM(final int id, final String author, final String title, LocalDate date, String note,
                              final String journal, final String volume, final String number, final String pages) {
        super(id, author, title, date, note);

        createEmptyArticleReferenceVM();

        setJournal(journal);
        setVolume(volume);
        setNumber(number);
        setPages(pages);
    }

    public ArticleReferenceVM(ArticleReference articleReference) {
        super(articleReference.getId(), articleReference.getAuthor(), articleReference.getTitle(), articleReference.getDate(), articleReference.getNote());

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

    public String getJournal() {
        return journal.get();
    }

    public StringProperty journalProperty() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal.set(journal);
    }

    public String getVolume() {
        return volume.get();
    }

    public StringProperty volumeProperty() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume.set(volume);
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getPages() {
        return pages.get();
    }

    public StringProperty pagesProperty() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages.set(pages);
    }

    public ReferenceType getReferenceType() {
        return referenceType.get();
    }

    public void setReferenceType(final ReferenceType referenceType) {
        this.referenceType.set(referenceType);
    }

    public ObjectProperty<ReferenceType> referenceTypeProperty() {
        return referenceType;
    }

    @Override
    public ArticleReference toModel() {
        ArticleReference journalReference = new ArticleReference(this.getId(), this.getAuthor(), this.getTitle(), this.getDate(), this.getNote());
        journalReference.setJournal(this.getJournal());
        journalReference.setNumber(this.getNumber());
        journalReference.setVolume(this.getVolume());

        return journalReference;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleReferenceVM)) return false;
        if (!super.equals(o)) return false;
        final ArticleReferenceVM that = (ArticleReferenceVM) o;
        return getReferenceType().equals(that.getReferenceType()) &&
                Objects.equals(getJournal(), that.getJournal()) &&
                Objects.equals(getVolume(), that.getVolume()) &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getPages(), that.getPages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getReferenceType(), getJournal(), getVolume(), getNumber(), getPages());
    }
}