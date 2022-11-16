package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.dto.ReferenceDTO;
import io.github.codexrm.projectreference.model.dto.ReferenceLibraryDTO;
import io.github.codexrm.projectreference.model.utils.DTOConverter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Sync {

    private final ReferenceLibraryDTO libraryDTO;
    private final DTOConverter dtoConverter;
    private final RestSync restSync;

    public Sync() {
        this.libraryDTO = new ReferenceLibraryDTO();
        this.dtoConverter = new DTOConverter();
        this.restSync = new RestSync();
    }

    public List<Reference> syncReferences(Hashtable<Integer, Reference> referenceTable, User user){

        addToLibraryDTO(referenceTable,user);
        List<ReferenceDTO> list = restSync.syncReferences(libraryDTO);

        return dtoConverter.toReferenceList(list);
    }

    private void addToLibraryDTO(Hashtable<Integer, Reference> referenceTable, User user){

        Enumeration<Reference> e = referenceTable.elements();
        ArrayList<Reference> referenceList = new ArrayList<>();
        while (e.hasMoreElements()) {
            referenceList.add(e.nextElement());
        }
        for (Reference reference : referenceList){
            if (!reference.isFromServer && reference.isActive()){
                ReferenceDTO referenceDTO = dtoConverter.toReferenceDTO(reference,user);
                referenceDTO.setId(0);
                libraryDTO.setNewReference(referenceDTO);
            }else{
                if (reference.isModified && reference.isFromServer && reference.isActive()){
                    libraryDTO.setUpdatedReference(dtoConverter.toReferenceDTO(reference,user));
                }else{
                    if (reference.isFromServer && !reference.isActive()){
                        libraryDTO.setDeletedReference(reference.getId());
                    }
                }
            }
        }
        libraryDTO.setUserId(user.getId());
    }
}
