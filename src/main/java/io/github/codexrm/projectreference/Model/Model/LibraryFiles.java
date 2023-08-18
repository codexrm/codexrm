package io.github.codexrm.projectreference.model.model;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LibraryFiles {

    private ObjectMapper mapper;
    private File fileReference;

    public LibraryFiles(String fileReferenceName) {
        mapper = new ObjectMapper();
        fileReference = new File(fileReferenceName);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public File getFileReference() {
        return fileReference;
    }

    public void setFileReference(File fileReference) {
        this.fileReference = fileReference;
    }

    public void saveReferenceTable(ReferenceLibrary referenceLibrary) throws IOException { mapper.writeValue(fileReference, referenceLibrary); }

    public ReferenceLibrary loadReferenceTable(String pathName) throws IOException { return mapper.readValue(new File(pathName), ReferenceLibrary.class); }
}
