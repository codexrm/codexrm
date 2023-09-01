package io.github.codexrm.projectreference.model.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidations {

    public boolean validateAuthorOrEditorRequired(String author) {
        if(author == null)
            return false;

        if(author.equals("CodexRM:Error"))
            return false;

            final Pattern pat = Pattern.compile("^[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+[;(?=[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+)[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+]*");
            final Matcher mat = pat.matcher(author);
            return mat.matches();
        }

    public boolean validateAuthorOrEditor(String author) {
        if(author == null)
            return true;

        if(author.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|^[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+[;(?=[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+)[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+]*");
        final Matcher mat = pat.matcher(author);
        return mat.matches();
    }

    public boolean validateAddress(String address) {
        if(address == null)
            return true;

        if(address.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|^[A-ZÁÉÍÓÚÜÑ][A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]*[A-ZÁÉÍÓÚÜÑa-záéíóúüñ]+,\\s[[A-Za-záéíóúüñÁÉÍÓÚÜÑ]+]*");
        final Matcher mat = pat.matcher(address);
        return mat.matches();
    }

    public boolean validateChapterRequired(final String chpater) {
        if(chpater == null)
            return false;

        if(chpater.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("[\\d]*");
        final Matcher mat = pat.matcher(chpater);
        return mat.matches();
    }

    public boolean validateChapterOrVolume(final String number) {
        if(number == null)
            return true;

        if(number.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|[\\d]*");
        final Matcher mat = pat.matcher(number);
        return mat.matches();
    }

    public boolean validateEdition(String edition) {
        if(edition == null)
            return true;

        if(edition.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ]+|\\d+\\.");
        final Matcher mat = pat.matcher(edition);
        return mat.matches();
    }

    public boolean validateIssn(String issn) {
        if(issn == null)
            return true;

        if(issn.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|\\d{4}-\\d{4}|\\d{4}-\\d{3}X");
        final Matcher mat = pat.matcher(issn);
        return mat.matches();
    }

    public boolean validateIsbn(String isbn) {
        if(isbn == null)
            return true;

        if(isbn.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$");
        final Matcher mat = pat.matcher(isbn);
        return mat.matches();
    }

    public boolean validateNumber(String number) {
        if(number == null)
            return true;

        if(number.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0-9\\s-]+");
        final Matcher mat = pat.matcher(number);
        return mat.matches();
    }

    public boolean validatePagesRequired(String pages) {
        if(pages == null)
            return false;

        if(pages.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("[IVXMLCD]+|[IVXMLCD]+,[IVXMLCD]+|[IVXMLCD]+-[IVXMLCD]+|[0-9]+|[0-9]+,[0-9]+|[0-9]+-[0-9]+");
        final Matcher mat = pat.matcher(pages);
        return mat.matches();
    }

    public boolean validatePages(String pages) {
        if(pages == null)
            return true;

        if(pages.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|[IVXMLCD]+|[IVXMLCD]+,[IVXMLCD]+|[IVXMLCD]+-[IVXMLCD]+|[0-9]+|[0-9]+,[0-9]+|[0-9]+-[0-9]+");
        final Matcher mat = pat.matcher(pages);
        return mat.matches();
    }

    public boolean validateSeries(String series) {
        if(series == null)
            return true;

        if(series.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]+");
        final Matcher mat = pat.matcher(series);
        return mat.matches();
    }

    public boolean validateUrl(String url) {
        if(url == null)
            return true;

        if(url.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|^https://.*");
        final Matcher mat = pat.matcher(url);
        return mat.matches();
    }

    public boolean validateYear(String year) {
        if(year == null)
            return true;

        if(year.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("^$|\\d{4}|\\d{4}--\\d{4}");
        final Matcher mat = pat.matcher(year);
        return mat.matches();
    }

    public boolean validateYearRequired(String year) {
        if(year == null)
            return false;

        if(year.equals("CodexRM:Error"))
            return false;

        final Pattern pat = Pattern.compile("\\d{4}|\\d{4}--\\d{4}");
        final Matcher mat = pat.matcher(year);
        return mat.matches();
    }

    public boolean validateFieldRequired(String field) {

        boolean isValidate = true;

        if (field == null){
            isValidate = false;
        }else {
            if (field.isBlank()) {
                isValidate = false;
            } else {
                if (field.equals("CodexRM:Error")) {
                    return false;
                }
            }
        }
        return isValidate;
    }
}
