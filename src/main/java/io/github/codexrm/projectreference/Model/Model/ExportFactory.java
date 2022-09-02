package io.github.codexrm.projectreference.model.model;


import io.github.codexrm.projectreference.model.enums.Format;

public class ExportFactory {

    public ExportFactory() {
        // Do nothing
    }

    public Export getExport(Format type) {

        if (type.equals(Format.RIS)) {
            return new ExportRis();
        } else {
            return new ExportBibTex();
        }
    }
}