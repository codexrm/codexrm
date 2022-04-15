package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.Enum.Format;

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
            ImportBibTex importBibTex = new ImportBibTex();
            importBibTex.setAuthorLibrary(authorLibrary);
            return importBibTex;
        }
    }
}
