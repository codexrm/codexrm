package io.github.codexrm.projectreference.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {


    public String validateYear(String year) {

        String[] years = year.split("--", 2);
        String yearValidated = null;
        if (years.length == 1) {
            if (correctYear(year) != null) {
                yearValidated = correctYear(year);

            }
        } else {
            if (years.length == 2) {
                if (correctYear(years[0]) != null && correctYear(years[1]) != null) {
                    yearValidated = correctYear(years[0]) + "--" + correctYear(years[1]);
                }
            }
        }
        return yearValidated;
    }

    public boolean validateAuthorOrEditor(String author) {

        final Pattern pat = Pattern.compile("^[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+[;(?=[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+)[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+]*");
        final Matcher mat = pat.matcher(author);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean validateJournalOrPublihser(String field) {

        final Pattern pat = Pattern.compile("[A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s\\.]+");
        final Matcher mat = pat.matcher(field);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateSchool(String school) {

        final Pattern pat = Pattern.compile("[A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s,]+");
        final Matcher mat = pat.matcher(school);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateUrl(String url) {

        final Pattern pat = Pattern.compile("^https://.*");
        final Matcher mat = pat.matcher(url);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateOrganizationOrSeries(String field) {

        final Pattern pat = Pattern.compile("[A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]+");
        final Matcher mat = pat.matcher(field);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateAddress(String address) {

        final Pattern pat = Pattern.compile("^[A-ZÁÉÍÓÚÜÑ][A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]+[,\\s*[A-Za-záéíóúüñÁÉÍÓÚÜÑ]+]*");
        final Matcher mat = pat.matcher(address);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateEdition(String edition) {

        final Pattern pat = Pattern.compile("[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0-9\\s\\.]+");
        final Matcher mat = pat.matcher(edition);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateNumber(String number) {

        final Pattern pat = Pattern.compile("[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0-9\\s-]+");
        final Matcher mat = pat.matcher(number);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePages(String pages) {

        final Pattern pat = Pattern.compile("[IVXMLCD0-9-,]+");
        final Matcher mat = pat.matcher(pages);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateIssnOrIsbn(String code) {

        final Pattern pat = Pattern.compile("\\d{4}-\\d{4}");
        final Matcher mat = pat.matcher(code);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNumber(final String number) {

        try {
            Long.valueOf(number);
            return true;

        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private String correctYear(final String year) {

        if (isNumber(year)) {
            final char[] charYear = year.toCharArray();
            switch (charYear.length) {
                case 1:
                    return "000" + year;
                case 2:
                    return "00" + year;
                case 3:
                    return "0" + year;
                case 4:
                    return year;
                default:
                    return null;
            }
        } else return null;
    }
}
