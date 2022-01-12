package io.github.codexrm.projectreference.Model;

import io.github.codexrm.projectreference.Enum.Format;

public class ImportFactory {

  public ImportFactory() {
// Do nothing
  }

  public Import getImport(Format type, AuthorLibrary authorLibrary) {
    if (type.equals(Format.RIS)) {
      ImportRis importRis = new ImportRis();
      importRis.setAuthorLibrary(authorLibrary);
      return importRis;
    } else {
      ImportBibTest importBibTex = new ImportBibTest();
      importBibTex.setAuthorLibrary(authorLibrary);
      return importBibTex;
    }
  }
}
