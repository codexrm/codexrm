package io.github.codexrm.projectreference.model.model;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LibraryFiles {

    private final ObjectMapper mapper;
    private final File fileReference;

    public LibraryFiles(String fileReferenceName) {
        mapper = new ObjectMapper();
        fileReference = new File(fileReferenceName);
    }

    public void saveReferenceTable(ReferenceLibrary referenceLibrary) throws IOException { mapper.writeValue(fileReference, referenceLibrary); }

    public ReferenceLibrary loadReferenceTable(String pathName) throws IOException { return mapper.readValue(new File(pathName), ReferenceLibrary.class); }
}
