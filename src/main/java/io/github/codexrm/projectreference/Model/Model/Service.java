package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.dto.ReferenceDTO;
import io.github.codexrm.projectreference.model.dto.ReferenceLibraryDTO;
import io.github.codexrm.projectreference.model.dto.ReferencePageDTO;
import io.github.codexrm.projectreference.model.enums.SortReference;
import io.github.codexrm.projectreference.model.utils.DTOConverter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Service {

    private ReferenceLibraryDTO libraryDTO;
    private final DTOConverter dtoConverter;
    private final RestService restService;

    public Service() {
        this.libraryDTO = new ReferenceLibraryDTO();
        this.dtoConverter = new DTOConverter();
        this.restService = new RestService();
    }

   public ReferenceLibrary syncReferences(Hashtable<Integer, Reference> referenceTable, AuthenticationData authenticationData, User user){

        ArrayList<Reference> referenceList = new ArrayList<>();
        ReferencePageDTO referencePageDTO = new ReferencePageDTO();
       Hashtable<Integer, Reference> referenceTableSync = new Hashtable<>();

       addToLibraryDTO(referenceTable,user, authenticationData);

       do {
           referencePageDTO = restService.syncReferences(libraryDTO,referencePageDTO.getPageDTO().getCurrentPage(), authenticationData.getToken());
           referenceList = (ArrayList<Reference>) dtoConverter.toReferenceList(referencePageDTO.getReferenceDTOList());
       } while (referencePageDTO.getPageDTO().getCurrentPage() + 1 != referencePageDTO.getPageDTO().getTotalPages());;

      for(Reference reference: referenceList){
          referenceTableSync.put(reference.getId(), reference);
      }
        return new ReferenceLibrary(referenceTableSync,authenticationData, referencePageDTO.getReferenceDTOList().get(0).getUser());
    }

    public AuthenticationData login(UserLogin userLogin){

        return restService.userLogin(userLogin);
    }

    private void addToLibraryDTO(Hashtable<Integer, Reference> referenceTable, User user, AuthenticationData authenticationData){

        Enumeration<Reference> e = referenceTable.elements();
        ArrayList<Reference> referenceList = new ArrayList<>();
        while (e.hasMoreElements()) {
            referenceList.add(e.nextElement());
        }
        for (Reference reference : referenceList){
            if (!reference.isFromServer && reference.isActive()){
                ReferenceDTO referenceDTO = dtoConverter.toReferenceDTO(reference,user);
                referenceDTO.setId(0);
                libraryDTO.setNewReferencesList(referenceDTO);
            }else{
                if (reference.isModified && reference.isFromServer && reference.isActive()){
                    libraryDTO.setUpdatedReferencesList(dtoConverter.toReferenceDTO(reference,user));
                }else{
                    if (reference.isFromServer && !reference.isActive()){
                        libraryDTO.setDeletedReferencesList(reference.getId());
                    }
                }
            }
        }
        libraryDTO.setUserId(authenticationData.getId());
        libraryDTO.setSort(SortReference.idAsc);
    }
}
