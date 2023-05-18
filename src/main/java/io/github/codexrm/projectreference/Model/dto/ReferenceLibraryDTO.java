package io.github.codexrm.projectreference.model.dto;

import io.github.codexrm.projectreference.model.enums.SortReference;

import java.util.ArrayList;
import java.util.List;

public class ReferenceLibraryDTO {

    private List<ReferenceDTO> newReferencesList;
    private List<ReferenceDTO> updatedReferencesList;
    private List<Integer> deletedReferencesList;
    private Integer userId;
    private SortReference sort;

    public ReferenceLibraryDTO() {
        this.newReferencesList = new ArrayList<>();
        this.updatedReferencesList = new ArrayList<>();
        this.deletedReferencesList = new ArrayList<>();
    }

    public ReferenceLibraryDTO(List<ReferenceDTO> newReferencesList, List<ReferenceDTO> updatedReferencesList, List<Integer> deletedReferencesList, Integer userId, SortReference sort) {
        this.newReferencesList = newReferencesList;
        this.updatedReferencesList = updatedReferencesList;
        this.deletedReferencesList = deletedReferencesList;
        this.userId = userId;
        this.sort = sort;
    }

    public List<ReferenceDTO> getNewReferencesList() { return newReferencesList; }

    public void setNewReferencesList(ReferenceDTO newReference) { this.newReferencesList.add(newReference); }

    public List<ReferenceDTO> getUpdatedReferencesList() { return updatedReferencesList; }

    public void setUpdatedReferencesList(ReferenceDTO updatedReference) { this.updatedReferencesList.add(updatedReference); }

    public List<Integer> getDeletedReferencesList() { return deletedReferencesList; }

    public void setDeletedReferencesList(Integer referenceID) { this.deletedReferencesList.add(referenceID); }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public SortReference getSort() { return sort; }

    public void setSort(SortReference sort) { this.sort = sort; }
}