package io.github.codexrm.projectreference.model.utils;

import io.github.codexrm.EILibrary.enums.BookSectionTypeLibrary;
import io.github.codexrm.EILibrary.enums.FormatLibrary;
import io.github.codexrm.EILibrary.enums.MonthsLibrary;
import io.github.codexrm.EILibrary.enums.ThesisTypeLibrary;
import io.github.codexrm.projectreference.model.enums.BookSectionType;
import io.github.codexrm.projectreference.model.enums.Format;
import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.enums.ThesisType;

public class EnumsConverter {

    public EnumsConverter() {
    }

    public MonthsLibrary getMonthLibrary(Months months) {

        if (months != null) {
            switch (months) {
                case jan:
                    return MonthsLibrary.jan;
                case feb:
                    return MonthsLibrary.feb;
                case mar:
                    return MonthsLibrary.mar;
                case apr:
                    return MonthsLibrary.apr;
                case may:
                    return MonthsLibrary.may;
                case jun:
                    return MonthsLibrary.jun;
                case jul:
                    return MonthsLibrary.jul;
                case aug:
                    return MonthsLibrary.aug;
                case sep:
                    return MonthsLibrary.sep;
                case oct:
                    return MonthsLibrary.oct;
                case nov:
                    return MonthsLibrary.nov;
                case dec:
                    return MonthsLibrary.dec;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public Months getMonth(MonthsLibrary months) {

        if (months != null) {
            switch (months) {
                case jan:
                    return Months.jan;
                case feb:
                    return Months.feb;
                case mar:
                    return Months.mar;
                case apr:
                    return Months.apr;
                case may:
                    return Months.may;
                case jun:
                    return Months.jun;
                case jul:
                    return Months.jul;
                case aug:
                    return Months.aug;
                case sep:
                    return Months.sep;
                case oct:
                    return Months.oct;
                case nov:
                    return Months.nov;
                case dec:
                    return Months.dec;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public Months getMonth(String months) {

        if (months != null) {
            switch (months) {
                case "jan":
                    return Months.jan;
                case "feb":
                    return Months.feb;
                case "mar":
                    return Months.mar;
                case "apr":
                    return Months.apr;
                case "may":
                    return Months.may;
                case "jun":
                    return Months.jun;
                case "jul":
                    return Months.jul;
                case "aug":
                    return Months.aug;
                case "sep":
                    return Months.sep;
                case "oct":
                    return Months.oct;
                case "nov":
                    return Months.nov;
                case "dec":
                    return Months.dec;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public String getMonthString(Months months) {

        if (months != null) {
            switch (months) {
                case jan:
                    return "jan";
                case feb:
                    return "feb";
                case mar:
                    return "mar";
                case apr:
                    return "apr";
                case may:
                    return "may";
                case jun:
                    return "jun";
                case jul:
                    return "jul";
                case aug:
                    return "aug";
                case sep:
                    return "sep";
                case oct:
                    return "oct";
                case nov:
                    return "nov";
                case dec:
                    return "dec";
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public FormatLibrary getFormat(Format format) {

        if (format != null) {
            switch (format) {
                case RIS:
                    return FormatLibrary.RIS;
                case BIBTEX:
                    return FormatLibrary.BIBTEX;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public BookSectionTypeLibrary getBookSectionTypeLibrary(BookSectionType type) {

        if (type != null) {
            switch (type) {
                case AUDIOCD:
                    return BookSectionTypeLibrary.AUDIOCD;
                case CANDTHESIS:
                    return BookSectionTypeLibrary.CANDTHESIS;
                case DataCD:
                    return BookSectionTypeLibrary.DataCD;
                case MATHESIS:
                    return BookSectionTypeLibrary.MATHESIS;
                case PHDTHESIS:
                    return BookSectionTypeLibrary.PHDTHESIS;
                case RESREPORT:
                    return BookSectionTypeLibrary.RESREPORT;
                case SOFTWARE:
                    return BookSectionTypeLibrary.SOFTWARE;
                case TECHREPORT:
                    return BookSectionTypeLibrary.TECHREPORT;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public BookSectionType getBookSectionType(BookSectionTypeLibrary type) {

        if (type != null) {
            switch (type) {
                case AUDIOCD:
                    return BookSectionType.AUDIOCD;
                case CANDTHESIS:
                    return BookSectionType.CANDTHESIS;
                case DataCD:
                    return BookSectionType.DataCD;
                case MATHESIS:
                    return BookSectionType.MATHESIS;
                case PHDTHESIS:
                    return BookSectionType.PHDTHESIS;
                case RESREPORT:
                    return BookSectionType.RESREPORT;
                case SOFTWARE:
                    return BookSectionType.SOFTWARE;
                case TECHREPORT:
                    return BookSectionType.TECHREPORT;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public BookSectionType getBookSectionType(String type) {

        if (type != null) {
            switch (type) {
                case "Audio CD":
                    return BookSectionType.AUDIOCD;
                case "Candidate thesis":
                    return BookSectionType.CANDTHESIS;
                case "Data CD":
                    return BookSectionType.DataCD;
                case "Master´s thesis":
                    return BookSectionType.MATHESIS;
                case "PhD thesis":
                    return BookSectionType.PHDTHESIS;
                case "Research report":
                    return BookSectionType.RESREPORT;
                case "Software":
                    return BookSectionType.SOFTWARE;
                case "Technical report":
                    return BookSectionType.TECHREPORT;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public String getBookSectionTypeString(BookSectionType type) {

        if (type != null) {
            switch (type) {
                case AUDIOCD:
                    return "Audio CD";
                case CANDTHESIS:
                    return "Candidate thesis";
                case DataCD:
                    return "Data CD";
                case MATHESIS:
                    return "Master´s thesis" ;
                case PHDTHESIS:
                    return "PhD thesis";
                case RESREPORT:
                    return "Research report";
                case SOFTWARE:
                    return "Software";
                case TECHREPORT:
                    return "Technical report";
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public ThesisTypeLibrary getThesisTypeLibrary(ThesisType type) {

        if (type != null) {
            switch (type) {
                case MASTERS:
                    return ThesisTypeLibrary.MASTERS;
                case PHD:
                    return ThesisTypeLibrary.PHD;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public ThesisType getThesisType(ThesisTypeLibrary type) {

        if (type != null) {
            switch (type) {
                case MASTERS:
                    return ThesisType.MASTERS;
                case PHD:
                    return ThesisType.PHD;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public ThesisType getThesisType(String type) {

        if (type != null) {
            switch (type) {
                case "Masters":
                    return ThesisType.MASTERS;
                case "phd":
                    return ThesisType.PHD;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    public String getThesisTypeString(ThesisType type) {

        if (type != null) {
            switch (type) {
                case MASTERS:
                    return "Masters";
                case PHD:
                    return "phd";
                default:
                    return null;
            }
        } else {
            return null;
        }
    }
}
