package com.qa.client;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import com.qa.listeners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * Date :- 13/07/2019
 * This is API Automation Framework for Finkey API which contains all the http methods to send request and get response from the server.
 * @author Roshan Choudhary
 * @version 1.0
 *
 */
public class RestClient {

	// 1. GET Method without Headers:
	/**
	 * @param url - This will access Finkey API
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred. 
	 */
	public CloseableHttpResponse getWithoutHeaders(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();// create default http connection

		HttpGet httpget = new HttpGet(url); // http get request
		httpget.setHeader("Content-Type", "application/json");
		
		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget); // hit the GET URL
		double elapsedTime = System.currentTimeMillis() - startTime; // To capture response time from API
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API

		return closebaleHttpResponse;

	}

	// 2. GET Method with Headers:
	
	/**
	 * @param url - This will access Finkey API
	 * @param headerMap - It represent the meta-data associated with the API request and response.
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
	public CloseableHttpResponse getWithHeaders(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();// create default http connection

		HttpGet httpget = new HttpGet(url); // http get request

		// for headers
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget); // hit the GET URL
		double elapsedTime = System.currentTimeMillis() - startTime; // To capture response time from API
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;

	}

	// 3. GET Method with Query Params:
	/**
	 * @param url - This will access Finkey API
	 * @param queryMap - Query Parameter is used to sort/filter specific resources.
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
	public CloseableHttpResponse getWithQueryParams(String url, HashMap<String, String> queryMap)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();// create default http connection

		URIBuilder builder = new URIBuilder(url);
		for (Map.Entry<String, String> entry : queryMap.entrySet()) {
			builder.setParameter(entry.getKey(), entry.getValue());
		}
		HttpGet httpget = new HttpGet(builder.build());

		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget); // hit the GET URL
		double elapsedTime = System.currentTimeMillis() - startTime; // To capture response time from API
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;

	}

	// 4. GET Method with Query Params And Headers:
	/**
	 * @param url - This will access Finkey API
	 * @param headerMap - It represent the meta-data associated with the API request and response.
	 * @param queryMap - Query Parameter is used to sort/filter specific resources.
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
		public CloseableHttpResponse getWithQueryParamsAndHeaders(String url, HashMap<String, String> headerMap,
			HashMap<String, String> queryMap) throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();// create default http connection

		URIBuilder builder = new URIBuilder(url);
		for (Map.Entry<String, String> entry : queryMap.entrySet()) {
			builder.setParameter(entry.getKey(), entry.getValue());
		}
		HttpGet httpget = new HttpGet(builder.build());

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.setHeader(entry.getKey(), entry.getValue());
		}

		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget); // hit the GET URL
		double elapsedTime = System.currentTimeMillis() - startTime; // To capture response time from API
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;

	}

	// 5. GET Method with Path params:
	/**
	 * @param url - This will access Finkey API
	 * @param headerMap - It represent the meta-data associated with the API request and response.
	 * @param userId - This is attached with url path to get the specific value
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
	public CloseableHttpResponse getWithPathParams(String url, HashMap<String, String> headerMap, String id)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create default http connection

		URIBuilder builder = new URIBuilder(url);
		String uri = builder.setPath(builder.getPath()) + id;
		System.out.println("Get post url "+uri);

		HttpGet httpget = new HttpGet(uri);
		// for headers:
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget); // hit the GET URL
		double elapsedTime = System.currentTimeMillis() - startTime; // To capture response time from API
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;

	}

	// 6. POST Method without Headers:
	/**
	 * @param url - This will access Finkey API
	 * @param entityString - It creates a StringEntity(JSON Body) with the specified content to be post.
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 */
	public CloseableHttpResponse postWithoutHeaders(String url, String entityString)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create default http connection

		HttpPost httppost = new HttpPost(url); // http post request
		httppost.setHeader("Content-Type", "application/json");
		httppost.setEntity(new StringEntity(entityString)); // for payload

		httpClient = HttpClientBuilder.create().disableRedirectHandling().build(); // To resolve redirect issue of 303 or 3xx
		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
		double elapsedTime = System.currentTimeMillis() - startTime;
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;

	}

	// 7. POST Method with Headers and Body:
	/**
	 * @param url - This will access Finkey API
	 * @param entityString - It creates a StringEntity(JSON Body) with the specified content to be post.
	 * @param headerMap - It represent the meta-data associated with the API request and response.
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
	public CloseableHttpResponse postWithHeaders(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create default http connection

		HttpPost httppost = new HttpPost(url); // http post request
		httppost.setHeader("Content-Type", "application/json");
		httppost.setEntity(new StringEntity(entityString)); // for payload

		// for headers:
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
		double elapsedTime = System.currentTimeMillis() - startTime;
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;
	}

	// 8. POST Method with Headers Only:
	/**
	 * @param url - This will access Finkey API
	 * @param headerMap - It represent the meta-data associated with the API request and response.
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
	public CloseableHttpResponse postWithHeadersOnly(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create default http connection

		HttpPost httppost = new HttpPost(url); // http post request to post the data
		httppost.setHeader("Content-Type", "application/json");

		// for headers:
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}

		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
		double elapsedTime = System.currentTimeMillis() - startTime;
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;
	}

	// 9. POST Method with Form Data:
	/**
	 * @param url - This will access Finkey API
	 * @param headerMap - It represent the meta-data associated with the API request and response.
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
	public CloseableHttpResponse postWithFormData(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create default http connection

		HttpPost httppost = new HttpPost(url);

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}

		File file = new File(
				"C:\\Users\\Roshan\\eclipse-workspace\\FinkeyAPITestingUsingHTTPClient\\src\\main\\java\\com\\qa\\data\\6000PlusContact.json");
		FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create(); // To upload file to the post method 
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("contactJson", fileBody);
		builder.addTextBody("deviceId", "1");
		HttpEntity entity = builder.build();
		httppost.setEntity(entity);

		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
		double elapsedTime = System.currentTimeMillis() - startTime;
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;

	}

	// 10. POST Method with Path params:
	/**
	 * @param url - This will access Finkey API
	 * @param headerMap - It represent the meta-data associated with the API request and response.
	 * @param userId - This is attached with url path to get the specific value
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
	public CloseableHttpResponse postWithPathParams(String url, HashMap<String, String> headerMap, String userId)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create default http connection

		URIBuilder builder = new URIBuilder(url);
		String uri = builder.setPath(builder.getPath()) + userId;

		HttpPost httppost = new HttpPost(uri);
		// for headers:
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}

		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
		double elapsedTime = System.currentTimeMillis() - startTime;
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;

	}

	// 11. PUT Method with Headers:
	/**
	 * @param url - This will access Finkey API
	 * @param entityString - It creates a StringEntity(JSON Body) with the specified content to be post.
	 * @param headerMap - It represent the meta-data associated with the API request and response.
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
	public CloseableHttpResponse putWithHeaders(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create default http connection

		HttpPut httpPut = new HttpPut(url); // http put request to update the data
		httpPut.setHeader("Content-Type", "application/json");
		httpPut.setEntity(new StringEntity(entityString)); // for payload

		// for headers:
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}

		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpPut);
		double elapsedTime = System.currentTimeMillis() - startTime;
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;

	}
	
	// 12. PATCH Method with Headers:
	/**
	 * @param url - This will access Finkey API
	 * @param headerMap - It represent the meta-data associated with the API request and response.
	 * @return - It will return the API response invoked by Get Method
	 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred.
	 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
	 */
		public CloseableHttpResponse patchWithHeaders(String url, HashMap<String, String> headerMap)
				throws ClientProtocolException, IOException, URISyntaxException {
			CloseableHttpClient httpClient = HttpClients.createDefault(); // create default http connection
			HttpPatch httpPatch = new HttpPatch(url); // http patch request to update the specific value
			
			httpPatch.setHeader("Content-Type", "application/json");

			// for headers:
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpPatch.addHeader(entry.getKey(), entry.getValue());
			}
			System.out.println();

			double startTime = System.currentTimeMillis();
			CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpPatch);
			double elapsedTime = System.currentTimeMillis() - startTime;
			ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
			
			return closebaleHttpResponse;

		}

	// 13. DELETE Method with Path params:
		/**
		 * @param url - This will access Finkey API
		 * @param headerMap - It represent the meta-data associated with the API request and response.
		 * @param postId - This is attached with url path to delete the specific value
		 * @return - It will return the API response invoked by Get Method
		 * @throws ClientProtocolException - It will throws an exception if Signals an error in the HTTP protocol.
		 * @throws IOException - Signals that an I/O exception of some sort has occurred.
		 * @throws URISyntaxException - Checked exception thrown to indicate that a string could not be parsed as aURI reference.
		 */
	public CloseableHttpResponse deleteWithPathParams(String url, HashMap<String, String> headerMap, String postId)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create default http connection

		URIBuilder builder = new URIBuilder(url);
		String uri = builder.setPath(builder.getPath()) + postId;

		HttpDelete httpDelete = new HttpDelete(uri); // http delete request to delete the data
		// for headers:
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpDelete.addHeader(entry.getKey(), entry.getValue());
		}

		double startTime = System.currentTimeMillis();
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpDelete);
		double elapsedTime = System.currentTimeMillis() - startTime;
		ExtentTestManager.getTest().log(LogStatus.INFO, "The API response time is : " + elapsedTime + "ms");// To get response time from API
		
		return closebaleHttpResponse;

	}

}
