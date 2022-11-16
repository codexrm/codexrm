package io.github.codexrm.projectreference.model.utils;

import io.github.codexrm.projectreference.model.dto.*;
import io.github.codexrm.projectreference.model.enums.ThesisType;
import io.github.codexrm.projectreference.model.model.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DTOConverter {

    private final ModelMapper modelMapper;

    public DTOConverter() {this.modelMapper = new ModelMapper();}

    public ReferenceDTO toReferenceDTO(Reference reference, User user) {

        ReferenceDTO referenceDTO;

        if(reference.getClass() == ArticleReference.class){
            referenceDTO =  modelMapper.map(reference, ArticleReferenceDTO.class);
        }
        else if(reference.getClass() == BookSectionReference.class){
            referenceDTO =  modelMapper.map(reference, BookSectionReferenceDTO.class);
        }
        else if(reference.getClass() == BookReference.class){
            referenceDTO =  modelMapper.map(reference, BookReferenceDTO.class);
        }
        else if(reference.getClass() == BookLetReference.class){
            referenceDTO =  modelMapper.map(reference, BookLetReferenceDTO.class);
        }
        else if(reference.getClass() == ConferenceProceedingsReference.class){
            referenceDTO =  modelMapper.map(reference,ConferenceProceedingsReferenceDTO.class);
        }
        else if(reference.getClass() == ThesisReference.class){
                referenceDTO =  modelMapper.map(reference,ThesisReferenceDTO.class);
        }
        else if(reference.getClass() == ConferencePaperReference.class){
                referenceDTO =  modelMapper.map(reference,ConferencePaperReferenceDTO.class);
        }
        else{
            referenceDTO =  modelMapper.map(reference,WebPageReferenceDTO.class);
        }

        referenceDTO.setUserId( toUserDTO(user));
        return referenceDTO;
    }

    public Reference toReference(ReferenceDTO referenceDTO) {

        if(referenceDTO.getClass() == ArticleReferenceDTO.class){
            return modelMapper.map(referenceDTO,ArticleReference.class);
        }
        else if(referenceDTO.getClass() == BookSectionReferenceDTO.class){
            return modelMapper.map(referenceDTO,BookSectionReference.class);
        }
        else if(referenceDTO.getClass() == BookReferenceDTO.class){
            return  modelMapper.map(referenceDTO,BookReference.class);
        }
        else if(referenceDTO.getClass() == BookLetReferenceDTO.class){
            return  modelMapper.map(referenceDTO,BookLetReference.class);
        }
        else if(referenceDTO.getClass() == ConferenceProceedingsReferenceDTO.class){
            return  modelMapper.map(referenceDTO,ConferenceProceedingsReference.class);
        }
        else if(referenceDTO.getClass() == ThesisReferenceDTO.class){
             ThesisReference thesis = modelMapper.map(referenceDTO,ThesisReference.class);
            if (((ThesisReferenceDTO) referenceDTO).getType().equalsIgnoreCase("Masters")){
                 thesis.setType(ThesisType.MASTERS);
            }else{
                thesis.setType(ThesisType.PHD);
            }
            return thesis;
        }
        else if(referenceDTO.getClass() == ConferencePaperReferenceDTO.class){
            return modelMapper.map(referenceDTO,ConferencePaperReference.class);
        }else{
            return modelMapper.map(referenceDTO,WebPageReference.class);
        }

    }

    public List<ReferenceDTO> toReferenceDTOList(List<Reference> referenceList,User user) {
        List<ReferenceDTO> referenceDTOList = new ArrayList<>();
        referenceList.forEach(reference ->
                referenceDTOList.add(toReferenceDTO(reference, user))
        );
        return referenceDTOList;
    }

    public List<Reference> toReferenceList(List<ReferenceDTO> referenceDTOList) {
        List<Reference> referenceList = new ArrayList<>();
        for (ReferenceDTO referenceDTO: referenceDTOList){
            Reference ref = toReference(referenceDTO);
            ref.setFromServer(true);
            ref.setActive(true);
            referenceList.add(ref);

        }
        return referenceList;
    }

    private UserDTO toUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User toUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
