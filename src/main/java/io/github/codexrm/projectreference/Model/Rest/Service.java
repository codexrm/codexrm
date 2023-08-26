package io.github.codexrm.projectreference.model.Rest;

import io.github.codexrm.projectreference.model.dto.ReferenceDTO;
import io.github.codexrm.projectreference.model.dto.ReferenceLibraryDTO;
import io.github.codexrm.projectreference.model.dto.ReferencePageDTO;
import io.github.codexrm.projectreference.model.enums.SortReference;
import io.github.codexrm.projectreference.model.model.Reference;
import io.github.codexrm.projectreference.model.model.ReferenceLibrary;
import io.github.codexrm.projectreference.model.utils.DTOConverter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

public class Service {

    private final DTOConverter dtoConverter;
    private final RestService restService;
    private final ReferenceLibraryDTO libraryDTO;

    public Service() {
        this.libraryDTO = new ReferenceLibraryDTO();
        this.dtoConverter = new DTOConverter();
        this.restService = new RestService();
    }

    //User
    public AuthenticationData login(UserLogin userLogin) { return restService.userLogin(userLogin); }

    public boolean logout(String token) throws ExecutionException, InterruptedException { return restService.userLogout(token); }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest refreshToken) { return restService.refreshToken(refreshToken); }

    //Reference
    public ReferenceLibrary syncReferences(Hashtable<Integer, Reference> referenceTable, AuthenticationData authenticationData) {

        ArrayList<Reference> temporalList;
        ArrayList<Reference> referenceList = new ArrayList<>();
        ReferencePageDTO referencePageDTO = new ReferencePageDTO();
        Hashtable<Integer, Reference> referenceTableSync = new Hashtable<>();

        addToLibraryDTO(referenceTable, authenticationData);

        do {
            referencePageDTO = restService.syncReferences(libraryDTO, referencePageDTO.getPageDTO().getCurrentPage(), authenticationData.getToken());
            if (referencePageDTO == null) {
                break;
            }
            temporalList = (ArrayList<Reference>) dtoConverter.toReferenceList(referencePageDTO.getReferenceDTOList());
            for (Reference reference : temporalList) {
                referenceList.add(reference);
            }
            referencePageDTO.getPageDTO().setCurrentPage(referencePageDTO.getPageDTO().getCurrentPage() + 1);
        } while (referenceList.size() != referencePageDTO.getPageDTO().getTotalElement());
        for (Reference reference : referenceList) {
            referenceTableSync.put(reference.getId(), reference);
        }
        return new ReferenceLibrary(referenceTableSync, authenticationData);
    }

    private void addToLibraryDTO(Hashtable<Integer, Reference> referenceTable, AuthenticationData authenticationData) {

        Enumeration<Reference> e = referenceTable.elements();
        ArrayList<Reference> referenceList = new ArrayList<>();
        while (e.hasMoreElements()) {
            referenceList.add(e.nextElement());
        }
        for (Reference reference : referenceList) {
            if (!reference.isFromServer() && reference.isActive()) {
                ReferenceDTO referenceDTO = dtoConverter.toReferenceDTO(reference);
                referenceDTO.setId(0);
                libraryDTO.setNewReferencesList(referenceDTO);
            } else {
                if (reference.isModified() && reference.isFromServer() && reference.isActive()) {
                    libraryDTO.setUpdatedReferencesList(dtoConverter.toReferenceDTO(reference));
                } else {
                    if (reference.isFromServer() && !reference.isActive()) {
                        libraryDTO.setDeletedReferencesList(reference.getId());
                    }
                }
            }
        }
        libraryDTO.setUserId(authenticationData.getId());
        libraryDTO.setSort(SortReference.idAsc);
    }
}
