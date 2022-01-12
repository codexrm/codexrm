package io.github.codexrm.projectreference.Model;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LibraryFiles {

  private ObjectMapper mapper;
  private File fileReference;
  private File fileAuthor;


  public LibraryFiles(String fileReferenceName, String fileAuthorName) {
    mapper = new ObjectMapper();
    fileReference = new File(fileReferenceName);
    fileAuthor = new File(fileAuthorName);
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  public void setMapper(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  public File getFileReference() {
    return fileReference;
  }

  public void setFileReference(File fileReference) {
    this.fileReference = fileReference;
  }

  public File getFileAuthor() {
    return fileAuthor;
  }

  public void setFileAuthor(File fileAuthor) {
    this.fileAuthor = fileAuthor;
  }

  public void saveTables(ReferenceLibrary referenceLibrary, AuthorLibrary authorLibrary)
      throws IOException {
    mapper.writeValue(fileReference, referenceLibrary);
    mapper.writeValue(fileAuthor, authorLibrary);
  }

  public ReferenceLibrary loadReferenceTable(String pathName) throws IOException {
   return mapper.readValue(new File(pathName), ReferenceLibrary.class);
  }

  public AuthorLibrary loadAuthorTable(String pathName) throws IOException {
    return mapper.readValue(new File(pathName), AuthorLibrary.class);
  }
}
