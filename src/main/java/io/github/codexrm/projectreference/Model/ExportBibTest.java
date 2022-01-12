package io.github.codexrm.projectreference.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import io.github.codexrm.projectreference.Enum.ThesisType;

public class ExportBibTest implements Export {

  private AuthorLibrary authorLibrary;

  public ExportBibTest() {
    // Do nothing
  }

  public AuthorLibrary getAuthorLibrary() {
    return authorLibrary;
  }

  public void setAuthorLibrary(AuthorLibrary authorLibrary) {
    this.authorLibrary = authorLibrary;
  }

  @Override
  public void writeValue(Reference reference, String path) throws IOException {
    identifyType(reference, path);
  }

  @Override
  public void writeValue(Hashtable<Integer, Reference> referenceTable, String path)
      throws IOException {
    Enumeration<Integer> e = referenceTable.keys();
    Integer keyReference;
    Reference valorReference;

    while (e.hasMoreElements()) {
      keyReference = e.nextElement();
      valorReference = referenceTable.get(keyReference);
      identifyType(valorReference, path);
    }
  }

  private void identifyType(Reference reference, String path) throws IOException {
    FileWriter writer = new FileWriter(path, true);
    BufferedWriter bufferedWriter = new BufferedWriter(writer);

    if (reference instanceof ArticleReference) {
      writeArticleReference((ArticleReference) reference, bufferedWriter);

    } else {
      if (reference instanceof BookSectionReference) {
        writeBookSectionReference((BookSectionReference) reference, bufferedWriter);

      } else {
        if (reference instanceof BookReference) {
          writeBookReference((BookReference) reference, bufferedWriter);

        } else {
          if (reference instanceof ThesisReference) {
            writeThesisReference((ThesisReference) reference, bufferedWriter);

          } else {
            if (reference instanceof BookLetReference) {
              writeBookLetReference((BookLetReference) reference, bufferedWriter);

            } else {
              writeConferenceProceedingsReference((ConferenceProceedingsReference) reference,
                  bufferedWriter);
            }
          }
        }
      }
    }
  }

  private void writeAuthors(ArrayList<Integer> listId, BufferedWriter bufferedWriter)
      throws IOException {

    if (!listId.isEmpty()) {
      bufferedWriter.write("  author = {");
      for (int i = 0; i < listId.size(); i++) {
        Author author = authorLibrary.readAuthor(listId.get(i));
        if (i == 0) {
          bufferedWriter.write(
              author.getDataAuthor().getLastName() + "," + author.getDataAuthor().getName());
        } else {
          bufferedWriter.write(" and ");
          bufferedWriter.write(
              author.getDataAuthor().getLastName() + "," + author.getDataAuthor().getName());
        }
      }
      bufferedWriter.write("},");
      bufferedWriter.newLine();
    }
  }

  private void commonField(Reference reference, BufferedWriter bufferedWriter) throws IOException {

    bufferedWriter.newLine();
    writeAuthors(reference.getListAuthorId(), bufferedWriter);

    if (reference.getTitle() != null) {
      bufferedWriter.write("  title = {" + reference.getTitle() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getMonth() != null) {
      bufferedWriter.write("  month = " + reference.getMonth() + ",");
      bufferedWriter.newLine();
    }
    if (reference.getNote() != null) {
      bufferedWriter.write("  note = {" + reference.getNote() + "},");
      bufferedWriter.newLine();
    }
    bufferedWriter.write("  year = {" + reference.getYear() + "},");
    bufferedWriter.newLine();
  }

  private void closeReference(BufferedWriter bufferedWriter) throws IOException {

    bufferedWriter.newLine();
    bufferedWriter.write("}");
    bufferedWriter.newLine();
    bufferedWriter.write("");
    bufferedWriter.newLine();
    bufferedWriter.close();
  }

  private void writeArticleReference(ArticleReference reference, BufferedWriter bufferedWriter)
      throws IOException {

    bufferedWriter.write("@article{" + reference.getId() + ",");
    commonField(reference, bufferedWriter);

    if (reference.getJournal() != null) {
      bufferedWriter.write("  journal = {" + reference.getJournal() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getVolume() != null) {
      bufferedWriter.write("  volume = {" + reference.getVolume() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getNumber() != null) {
      bufferedWriter.write("  number = {" + reference.getNumber() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getPages() != null) {
      bufferedWriter.write("  pages = {" + reference.getPages() + "}");
    }

    closeReference(bufferedWriter);
  }

  private void writeBooks(BookReference reference, BufferedWriter bufferedWriter)
      throws IOException {

    commonField(reference, bufferedWriter);

    if (reference.getPublisher() != null) {
      bufferedWriter.write("  publisher = {" + reference.getPublisher() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getVolume() != null) {
      bufferedWriter.write("  volume = {" + reference.getVolume() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getSeries() != null) {
      bufferedWriter.write("  series = {" + reference.getSeries() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getAddress() != null) {
      bufferedWriter.write("  address = {" + reference.getAddress() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getEdition() != null) {
      bufferedWriter.write("  edition = {" + reference.getEdition() + "}");
    }
  }

  private void writeBookReference(BookReference reference, BufferedWriter bufferedWriter)
      throws IOException {

    bufferedWriter.write("@book{" + reference.getId() + ",");
    writeBooks(reference, bufferedWriter);
    closeReference(bufferedWriter);
  }

  private void writeBookSectionReference(BookSectionReference reference,
                                         BufferedWriter bufferedWriter) throws IOException {

    bufferedWriter.write("@inbook{" + reference.getId() + ",");

    if (reference.getChapter() != null) {
      bufferedWriter.newLine();
      bufferedWriter.write("  chapter = {" + reference.getChapter() + "},");
    }
    if (reference.getPages() != null) {
      bufferedWriter.newLine();
      bufferedWriter.write("  pages = {" + reference.getPages() + "},");
    }

    writeBooks(reference, bufferedWriter);
    closeReference(bufferedWriter);
  }

  private void writeBookLetReference(BookLetReference reference, BufferedWriter bufferedWriter)
      throws IOException {

    bufferedWriter.write("@booklet{" + reference.getId() + ",");
    commonField(reference, bufferedWriter);

    if (reference.getHowpublished() != null) {
      bufferedWriter.write("  howpublished = {" + reference.getHowpublished() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getAddress() != null) {
      bufferedWriter.write("  address = {" + reference.getAddress() + "}");
    }
    closeReference(bufferedWriter);
  }

  private void writeThesisReference(ThesisReference reference, BufferedWriter bufferedWriter)
      throws IOException {

    if (reference.getType().equals(ThesisType.Masters)) {
      bufferedWriter.write("@mastersthesis{" + reference.getId() + ",");
    } else {
      bufferedWriter.write("@phdthesis{" + reference.getId() + ",");
    }
    commonField(reference, bufferedWriter);

    if (reference.getSchool() != null) {
      bufferedWriter.write("  school = {" + reference.getSchool() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getType() != null) {
      bufferedWriter.write("  type = {" + reference.getType() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getAddress() != null) {
      bufferedWriter.write("  address = {" + reference.getAddress() + "}");
    }
    closeReference(bufferedWriter);
  }

  private void writeConferenceProceedingsReference(ConferenceProceedingsReference reference,
      BufferedWriter bufferedWriter) throws IOException {

    bufferedWriter.write("@inproceedings{" + reference.getId() + ",");
    commonField(reference, bufferedWriter);

    if (reference.getVolume() != null) {
      bufferedWriter.write("  volume = {" + reference.getVolume() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getSerie() != null) {
      bufferedWriter.write("  series = {" + reference.getSerie() + "},");
      bufferedWriter.newLine();
    }
    if (reference.getAddress() != null) {
      bufferedWriter.write("  address = {" + reference.getAddress() + "}");
    }
    closeReference(bufferedWriter);
  }
}
