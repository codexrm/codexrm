package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.Enum.Format;

public class ImportFactory {

    public ImportFactory() {
        // Do nothing
    }

    public Import getImport(Format type) {
        if (type.equals(Format.RIS)) {
            ImportRis importRis = new ImportRis();
            return importRis;
        } else {
            ImportBibTex importBibTex = new ImportBibTex();
            return importBibTex;
        }
    }
}
