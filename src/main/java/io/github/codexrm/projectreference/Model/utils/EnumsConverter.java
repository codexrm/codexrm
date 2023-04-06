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

    public ThesisTypeLibrary getThesisTypelibrary(ThesisType type) {

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
}
