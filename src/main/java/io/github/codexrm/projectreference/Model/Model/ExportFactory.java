package io.github.codexrm.projectreference.model.model;


import io.github.codexrm.projectreference.model.Enum.Format;

public class ExportFactory {

    public ExportFactory() {
        // Do nothing
    }

    public Export getExport(Format type) {

        if (type.equals(Format.RIS)) {
            ExportRis exportRis = new ExportRis();
            return exportRis;
        } else {
            ExportBibTex exportBibtex = new ExportBibTex();
            return exportBibtex;
        }
    }
}