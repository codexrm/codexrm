package io.github.codexrm.projectreference.model.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public interface Export {

    void writeValue(Reference reference, String path) throws IOException;

    void writeValue(ArrayList<Reference> referenceList, String path)
            throws IOException;
}