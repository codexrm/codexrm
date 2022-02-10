package io.github.codexrm.projectreference.Model.Model;

import io.github.codexrm.projectreference.Model.Enum.Format;

public class ExportFactory {

    public ExportFactory() {
        // Do nothing
    }

    public Export getExport(Format type, AuthorLibrary authorLibrary) {

        if (type.equals(Format.RIS)) {
            ExportRis exportRis = new ExportRis();
            exportRis.setAuthorLibrary(authorLibrary);
            return exportRis;
        } else {
            ExportBibTest exportBibtex = new ExportBibTest();
            exportBibtex.setAuthorLibrary(authorLibrary);
            return exportBibtex;
        }
    }
}