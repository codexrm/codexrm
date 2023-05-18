package io.github.codexrm.projectreference.model.utils;

import io.github.codexrm.projectreference.model.dto.*;
import io.github.codexrm.projectreference.model.enums.BookSectionType;
import io.github.codexrm.projectreference.model.enums.ThesisType;
import io.github.codexrm.projectreference.model.model.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DTOConverter {

    private final ModelMapper modelMapper;
    private EnumsConverter enumsConverter;

    public DTOConverter() {
        this.modelMapper = new ModelMapper();
        this.enumsConverter = new EnumsConverter();
    }

    public ReferenceDTO toReferenceDTO(Reference reference, User user) {

        ReferenceDTO referenceDTO;

        if(reference.getClass() == ArticleReference.class){
            referenceDTO =  modelMapper.map(reference, ArticleReferenceDTO.class);
            referenceDTO.setMonth(enumsConverter.getMonthString((reference).getMonth()));
        }
        else if(reference.getClass() == BookSectionReference.class){
            BookSectionReferenceDTO sectionDTO =  modelMapper.map(reference, BookSectionReferenceDTO.class);
            sectionDTO.setMonth(enumsConverter.getMonthString((reference).getMonth()));
            sectionDTO.setType(enumsConverter.getBookSectionTypeString(((BookSectionReference) reference).getType()));
            referenceDTO = sectionDTO;
        }
        else if(reference.getClass() == BookReference.class){
            referenceDTO =  modelMapper.map(reference, BookReferenceDTO.class);
            referenceDTO.setMonth(enumsConverter.getMonthString((reference).getMonth()));
        }
        else if(reference.getClass() == BookLetReference.class){
            referenceDTO =  modelMapper.map(reference, BookLetReferenceDTO.class);
            referenceDTO.setMonth(enumsConverter.getMonthString((reference).getMonth()));
        }
        else if(reference.getClass() == ConferenceProceedingsReference.class){
            referenceDTO =  modelMapper.map(reference,ConferenceProceedingsReferenceDTO.class);
            referenceDTO.setMonth(enumsConverter.getMonthString((reference).getMonth()));
        }
        else if(reference.getClass() == ThesisReference.class){
            ThesisReferenceDTO thesisDTO =  modelMapper.map(reference, ThesisReferenceDTO.class);
            thesisDTO.setMonth(enumsConverter.getMonthString((reference).getMonth()));
            thesisDTO.setType(enumsConverter.getThesisTypeString(((ThesisReference) reference).getType()));
            referenceDTO = thesisDTO;
        }
        else if(reference.getClass() == ConferencePaperReference.class){
                referenceDTO =  modelMapper.map(reference,ConferencePaperReferenceDTO.class);
            referenceDTO.setMonth(enumsConverter.getMonthString((reference).getMonth()));
        }
        else{
            referenceDTO =  modelMapper.map(reference,WebPageReferenceDTO.class);
            referenceDTO.setMonth(enumsConverter.getMonthString((reference).getMonth()));
        }

        referenceDTO.setUser(user);
        return referenceDTO;
    }

    public Reference toReference(ReferenceDTO referenceDTO) {

        Reference reference;

        if(referenceDTO.getClass() == ArticleReferenceDTO.class){
            reference = modelMapper.map(referenceDTO,ArticleReference.class);
            reference.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
        }
        else if(referenceDTO.getClass() == BookSectionReferenceDTO.class){
            BookSectionReference section = modelMapper.map(referenceDTO,BookSectionReference.class);
            section.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
            section.setType(enumsConverter.getBookSectionType(((BookSectionReferenceDTO) referenceDTO).getType()));
            reference = section;

        }
        else if(referenceDTO.getClass() == BookReferenceDTO.class){
            reference =  modelMapper.map(referenceDTO,BookReference.class);
            reference.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
        }
        else if(referenceDTO.getClass() == BookLetReferenceDTO.class){
            reference =  modelMapper.map(referenceDTO,BookLetReference.class);
            reference.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
        }
        else if(referenceDTO.getClass() == ConferenceProceedingsReferenceDTO.class){
            reference =  modelMapper.map(referenceDTO,ConferenceProceedingsReference.class);
            reference.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
        }
        else if(referenceDTO.getClass() == ThesisReferenceDTO.class){
             ThesisReference thesis = modelMapper.map(referenceDTO,ThesisReference.class);
             thesis.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
             thesis.setType(enumsConverter.getThesisType(((ThesisReferenceDTO) referenceDTO).getType()));
            reference = thesis;
        }
        else if(referenceDTO.getClass() == ConferencePaperReferenceDTO.class){
            reference = modelMapper.map(referenceDTO,ConferencePaperReference.class);
            reference.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
        }else{
            reference = modelMapper.map(referenceDTO,WebPageReference.class);
        }

        return reference;
    }

    public List<ReferenceDTO> toReferenceDTOList(List<Reference> referenceList, User user) {

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

    private UserDTO toUserDTO(User user) { return modelMapper.map(user, UserDTO.class); }

    public User toUser(UserDTO userDTO) { return modelMapper.map(userDTO, User.class); }
}
