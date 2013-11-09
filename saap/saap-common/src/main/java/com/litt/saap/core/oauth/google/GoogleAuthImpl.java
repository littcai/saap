package com.litt.saap.core.oauth.google;

import java.io.IOException;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
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
public class GoogleAuthImpl {
	
	public static HttpResponse executeGet(
		      HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
		      throws IOException {
		    Credential credential =
		        new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
		    HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
		    return requestFactory.buildGetRequest(url).execute();
	}
	
	public static void main(String[] args) {
		
	}

}
