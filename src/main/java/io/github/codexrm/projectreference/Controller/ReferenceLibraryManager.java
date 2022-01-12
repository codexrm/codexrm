package io.github.codexrm.projectreference.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import io.github.codexrm.projectreference.Enum.Format;
import io.github.codexrm.projectreference.Model.Author;
import io.github.codexrm.projectreference.Model.AuthorLibrary;
import io.github.codexrm.projectreference.Model.DataAuthor;
import io.github.codexrm.projectreference.Model.Export;
import io.github.codexrm.projectreference.Model.ExportFactory;
import io.github.codexrm.projectreference.Model.Import;
import io.github.codexrm.projectreference.Model.ImportFactory;
import io.github.codexrm.projectreference.Model.LibraryFiles;
import io.github.codexrm.projectreference.Model.Reference;
import io.github.codexrm.projectreference.Model.ReferenceLibrary;

public class ReferenceLibraryManager {

  private static ReferenceLibraryManager manager;
  private ReferenceLibrary referenceLibrary;
  private AuthorLibrary authorLibrary;
  private LibraryFiles libraryFiles;
  private static final String dbAuthorName = "test//authorL.txt";
  private static final String dbReferenceName = "test//referenceL.txt";
  private final ExportFactory exportFactory;
  private final ImportFactory importFactory;


  private ReferenceLibraryManager() {
    referenceLibrary = new ReferenceLibrary();
    authorLibrary = new AuthorLibrary();
    libraryFiles = new LibraryFiles(dbReferenceName, dbAuthorName);
    exportFactory = new ExportFactory();
    importFactory = new ImportFactory();
  }
public static ReferenceLibraryManager getReferenceLibraryManager() {
  if (manager == null) {
    manager = new ReferenceLibraryManager();
  }
  return manager;
}
  public ReferenceLibrary getReferenceLibrary() {
    return referenceLibrary;
  }

  public void setReferenceLibrary(ReferenceLibrary referenceLibrary) {
    this.referenceLibrary = referenceLibrary;
  }

  public AuthorLibrary getAuthorLibrary() {
    return authorLibrary;
  }

  public void setAuthorLibrary(AuthorLibrary authorLibrary) {
    this.authorLibrary = authorLibrary;
  }

  public LibraryFiles getLibraryFiles() {
    return libraryFiles;
  }

  public void setLibraryFiles(LibraryFiles libraryFiles) {
    this.libraryFiles = libraryFiles;
  }

  public ArrayList<Integer> addAuthor(ArrayList<DataAuthor> listDataAuthor) {
    return authorLibrary.addAuthor(listDataAuthor);
  }

  public Author readAuthor(Integer id) {
    return authorLibrary.readAuthor(id);
  }
  public Reference addEmptyBookReference() {
    return referenceLibrary.addEmptyReference(authorLibrary);
}

public Reference getReference(int id) {
    return referenceLibrary.getReference(id);
}

public Hashtable<Integer, Reference> getReferenceTable() {
    return referenceLibrary.getReferenceTable();
}

public void saveTables() throws IOException {
     libraryFiles.saveTables(referenceLibrary, authorLibrary);
}

public void loadTables() throws IOException {
  File fileDBA = new File(dbReferenceName);
  if (fileDBA.exists() && fileDBA.isFile()) {
   setAuthorLibrary(libraryFiles.loadAuthorTable(dbAuthorName));
  } else {
    saveTables();
  }
  File fileDBR = new File(dbReferenceName);
  if (fileDBR.exists() && fileDBR.isFile()) {
   setReferenceLibrary(libraryFiles.loadReferenceTable(dbReferenceName));
referenceLibrary.addAuthorLibraryToReference(authorLibrary);
  } else {
    saveTables();
  }
 
}

public void replaceReferenceType(Reference newReference) {
  
    if (referenceLibrary.getReference(newReference.getId()) != null) {
      
      Reference removedReference = referenceLibrary.getReferenceTable().remove(newReference.getId());
      newReference.setAuthorLibrary(authorLibrary);
      newReference.setAuthor(removedReference.getAuthor());
      newReference.setTitle(removedReference.getTitle());
      newReference.setYear(removedReference.getYear());
      newReference.setMonth(removedReference.getMonth());
      newReference.setNote(removedReference.getNote());
      newReference.setId(removedReference.getId());
      referenceLibrary.getReferenceTable().put(newReference.getId(), newReference);
    }
}

  public void exportReference(String path, Reference reference, Format format) throws IOException {

    Export export = exportFactory.getExport(format, authorLibrary);
    export.writeValue(reference, path);
  }

  public void exportReferenceTable(String path, Format format) throws IOException {

    Export export = exportFactory.getExport(format, authorLibrary);
    export.writeValue(referenceLibrary.getReferenceTable(), path);
  }

  public void importReferences(String path, Format format)
      throws IOException, TokenMgrException, ParseException {

    Import importer = importFactory.getImport(format, authorLibrary);
    referenceLibrary.addListReference(importer.readFile(path));
    
  }
}
