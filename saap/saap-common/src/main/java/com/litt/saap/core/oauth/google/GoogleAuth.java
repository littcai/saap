package com.litt.saap.core.oauth.google;

import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;

/**
 * .
 * 
 * <pre><b>描述：</b>
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-9-11
 * @version 1.0
 */
public class GoogleAuth {
	
	private static final String NETWORK_NAME = "Google";
	private static final String AUTHORIZE_URL = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token=";
	private static final String PROTECTED_RESOURCE_URL = "https://docs.google.com/feeds/default/private/full/";
	private static final String SCOPE = "https://docs.google.com/feeds/"; 

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		OAuthService service = new ServiceBuilder()
        .provider(GoogleApi.class)
        .apiKey("818787327949.apps.googleusercontent.com")
        .apiSecret("076a9fddb3d21b9ee3107b84c0f70930a2a20911")
        .scope(SCOPE)
        .build();
		Scanner in = new Scanner(System.in);
		
		System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
		System.out.println();
		
		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		Token requestToken = service.getRequestToken();
		System.out.println("Got the Request Token!");
		System.out.println("(if your curious it looks like this: " + requestToken + " )");
		System.out.println();
		
		System.out.println("Now go and authorize Scribe here:");
		System.out.println(AUTHORIZE_URL + requestToken.getToken());
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		System.out.println();
		
		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		Token accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: " + accessToken + " )");
		System.out.println();
		
		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		request.addHeader("GData-Version", "3.0");
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getCode());
		System.out.println(response.getBody());
		
		System.out.println();
		System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
		
		
		
	}

}
