package utilityProgarm;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Caption;
import com.google.api.services.youtube.model.CaptionSnippet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;

public class UploadYoutubeCaption {

	private static final String CLIENT_SECRETS= "client_secret.json";
	private static final Collection<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/youtube.force-ssl");
	
	private static final String APPLICATION_NAME = "API code samples";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	
	
	public UploadYoutubeCaption() {
		// TODO Auto-generated constructor stub
	}

	
	/**
     * Create an authorized Credential object.
     *
     * @return an authorized Credential object.
	 * @throws Exception 
     */
	public static Credential authorize(final NetHttpTransport httpTransport) throws Exception {
		 /*** Load client secrets. ***/
//		InputStream in = UploadYoutubeCaption.class.getResourceAsStream(CLIENT_SECRETS);
		InputStream in = new FileInputStream("/home/chanwit/Documents/UploadSubtitleYoutube/booming-bonito-309508-5408dbc89eef.json");
		 GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		 
		  /*** Build flow and trigger user authorization request. ***/
		 GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES).build();
		 Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		 
		 return credential;
	}
	
	/**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
	 * @throws Exception 
     */
	public static YouTube getService() throws Exception {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = authorize(httpTransport);
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build();
    }
	
	 /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
	 * @throws Exception 
     */
	public static void main(String[] args)throws Exception {
	        YouTube youtubeService = getService();
	        
	        // Define the Caption object, which will be uploaded as the request body.
	        Caption caption = new Caption();
	        
	        // Add the snippet object property to the Caption object.
	        CaptionSnippet snippet = new CaptionSnippet();
	        snippet.setIsDraft(true);
	        snippet.setLanguage("es");
	        snippet.setName("Spanish captions");
	        snippet.setVideoId("YOUR_VIDEO_ID");
	        caption.setSnippet(snippet);

	        // TODO: For this request to work, you must replace "YOUR_FILE"
	        //       with a pointer to the actual file you are uploading.
	        //       The maximum file size for this operation is 104857600.
	        File mediaFile = new File("YOUR_FILE");
	        InputStreamContent mediaContent =
	            new InputStreamContent("*/*",
	                new BufferedInputStream(new FileInputStream(mediaFile)));
	        mediaContent.setLength(mediaFile.length());

	        // Define and execute the API request
	        YouTube.Captions.Insert request = youtubeService.captions()
	            .insert("snippet", caption, mediaContent);
	        Caption response = request.execute();
	        System.out.println(response);
	    } 
	
	
	
}
