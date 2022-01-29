package com.example.autosell.services.impl;
import com.example.autosell.services.GoogleService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* class to demonstarte use of Drive files list API */
@Service
public class DefaultGoogleService implements GoogleService {
    /** Application name. */
    private static final String APPLICATION_NAME = "AutoSell";
    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /** Directory to store authorization tokens for this application. */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        java.io.File initialFile = new java.io.File(System.getProperty("user.dir")+CREDENTIALS_FILE_PATH);

        InputStream in = new FileInputStream(initialFile);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }
/*
    public void listDriveFiles() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        // Print the names and IDs for up to 10 files.
        Drive.Files.Get temp = service.files().get("pruebas java");
        FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s)\n", file.getName(), file.getId());
            }
        }
    }
*/
    public void appendData(List<Object> data,String spreadsheetId) throws IOException, GeneralSecurityException {
        // The ID of the spreadsheet to update.
        //String spreadsheetId = "1telZFusyzomy12ZeS6Be7xURFtnf7nsQfPJQ1Cn2svc"; // TODO: Update placeholder value.

        // The A1 notation of a range to search for a logical table of data.
        // Values will be appended after the last row of the table.
        String range = "A2:ZZ2"; // TODO: Update placeholder value.

        // How the input data should be interpreted.
        String valueInputOption = "USER_ENTERED"; // TODO: Update placeholder value.

        // How the input data should be inserted.
        String insertDataOption = "INSERT_ROWS"; // TODO: Update placeholder value.

        // TODO: Assign values to desired fields of `requestBody`:
        ValueRange requestBody = new ValueRange();

        List<List<Object>> temp = new ArrayList<>();
        temp.add(data);

        requestBody.setValues(temp);

        for (List<Object> value : requestBody.getValues()) {
            for (Object o : value) {
                System.out.println(o);
            }
        }
        Sheets sheetsService = createSheetsService();
        Sheets.Spreadsheets.Values.Append request =
                sheetsService.spreadsheets().values().append(spreadsheetId, range, requestBody);
        request.setValueInputOption(valueInputOption);
        request.setInsertDataOption(insertDataOption);

        AppendValuesResponse response = request.execute();

        // TODO: Change code below to process the `response` object:
        System.out.println(response);
    }

    public static Sheets createSheetsService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // TODO: Change placeholder below to generate authentication credentials. See
        // https://developers.google.com/sheets/quickstart/java#step_3_set_up_the_sample
        //
        // Authorize using one of the following scopes:
        //   "https://www.googleapis.com/auth/drive"
        //   "https://www.googleapis.com/auth/drive.file"
        //   "https://www.googleapis.com/auth/spreadsheets"

        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

}