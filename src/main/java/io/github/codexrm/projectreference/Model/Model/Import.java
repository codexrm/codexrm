package io.github.codexrm.projectreference.model.model;

import java.io.IOException;
import java.util.ArrayList;

import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

public interface Import {

    ArrayList<Reference> readFile(String path) throws IOException, TokenMgrException, ParseException;
}