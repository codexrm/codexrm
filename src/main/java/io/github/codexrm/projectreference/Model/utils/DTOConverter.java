package io.github.codexrm.projectreference.model.utils;

import io.github.codexrm.projectreference.model.dto.*;
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

    public ReferenceDTO toReferenceDTO(Reference reference) {

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
        return referenceDTO;
    }

    public Reference toReference(ReferenceDTO referenceDTO) {

        Reference reference;

        if(referenceDTO.getClass() == ArticleReferenceDTO.class){
            ArticleReference article = modelMapper.map(referenceDTO,ArticleReference.class);
            article.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
            if (article.getAuthor() == null || article.getTitle() == null || article.getJournal() == null || article.getYear() == null) {
                reference = null;
            } else {
                reference = article;
            }
        }
        else if(referenceDTO.getClass() == BookSectionReferenceDTO.class){
            BookSectionReference section = modelMapper.map(referenceDTO,BookSectionReference.class);
            section.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
            section.setType(enumsConverter.getBookSectionType(((BookSectionReferenceDTO) referenceDTO).getType()));
            if (section.getChapter() == null || section.getPages() == null || section.getAuthor() == null || section.getEditor() == null || section.getTitle() == null || section.getPublisher() == null || section.getYear() == null) {
                reference = null;
            } else {
                reference = section;
            }
        }
        else if(referenceDTO.getClass() == BookReferenceDTO.class){
            BookReference book = modelMapper.map(referenceDTO,BookReference.class);
            book.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
            if (book.getAuthor() == null || book.getEditor() == null || book.getTitle() == null || book.getPublisher() == null || book.getYear() == null) {
                reference = null;
            } else {
                reference = book;
            }
        }
        else if(referenceDTO.getClass() == BookLetReferenceDTO.class){
            BookLetReference let = modelMapper.map(referenceDTO,BookLetReference.class);
            let.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
            if (let.getTitle() == null || let.getAuthor() == null) {
                reference = null;
            } else {
                reference = let;
            }
        }
        else if(referenceDTO.getClass() == ConferenceProceedingsReferenceDTO.class){
            ConferenceProceedingsReference proceedings = modelMapper.map(referenceDTO,ConferenceProceedingsReference.class);
            proceedings.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
            if (proceedings.getTitle() == null || proceedings.getYear() == null) {
                reference = null;
            } else {
                reference = proceedings;
            }
        }
        else if(referenceDTO.getClass() == ThesisReferenceDTO.class){
             ThesisReference thesis = modelMapper.map(referenceDTO,ThesisReference.class);
             thesis.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
             thesis.setType(enumsConverter.getThesisType(((ThesisReferenceDTO) referenceDTO).getType()));
            if (thesis.getAuthor() == null || thesis.getTitle() == null || thesis.getSchool() == null || thesis.getYear() == null) {
                reference = null;
            } else {
                reference = thesis;
            }
        }
        else if(referenceDTO.getClass() == ConferencePaperReferenceDTO.class){
            ConferencePaperReference paper = modelMapper.map(referenceDTO,ConferencePaperReference.class);
            paper.setMonth(enumsConverter.getMonth(referenceDTO.getMonth()));
            if (paper.getAuthor() == null || paper.getTitle() == null  || paper.getBookTitle() == null  || paper.getYear() == null) {
                reference = null;
            } else {
                reference = paper;
            }
        }else{
            reference = modelMapper.map(referenceDTO,WebPageReference.class);
        }

        return reference;
    }

    public List<Reference> toReferenceList(List<ReferenceDTO> referenceDTOList) {

        List<Reference> referenceList = new ArrayList<>();
        for (ReferenceDTO referenceDTO: referenceDTOList){
            Reference ref = toReference(referenceDTO);
            if(ref != null){
                ref.setFromServer(true);
                ref.setActive(true);
                referenceList.add(ref);
            }
        }
        return referenceList;
    }
}
