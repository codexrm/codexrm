package io.github.codexrm.projectreference.Model.Model;

import java.io.IOException;
import java.util.Hashtable;

public interface Export {

    void writeValue(Reference reference, String path) throws IOException;

    void writeValue(Hashtable<Integer, Reference> referenceTable, String path)
            throws IOException;
}