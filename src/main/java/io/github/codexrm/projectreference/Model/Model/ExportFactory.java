package io.github.codexrm.projectreference.model.model;


import io.github.codexrm.projectreference.model.Enum.Format;

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
            ExportBibTex exportBibtex = new ExportBibTex();
            exportBibtex.setAuthorLibrary(authorLibrary);
            return exportBibtex;
        }
    }
}