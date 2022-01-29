package com.example.autosell.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface GoogleService {

    void appendData(List<Object> data,String spreadSheetId) throws IOException, GeneralSecurityException;

}
