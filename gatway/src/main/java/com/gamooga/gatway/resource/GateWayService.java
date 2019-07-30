package com.gamooga.gatway.resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bitly")
public class GateWayService {
	static String storages[] = { "bitly0", "bitly1", "bitly2", "bitly3" };

	/**
	 * @param id
	 *            String of the form : <key>-<encoded url with escaping>
	 * @return
	 */
	@RequestMapping("/keys/{id}")
	public String save(@PathVariable String id) {
		return serviceCall(id, true);
	}

	private String serviceCall(String id, boolean isSave) {
		char c = id.charAt(0);
		System.out.println(id);
		int serial;
		if (c >= 'A' && c <= 'P')
			serial = 0;

		else if ((c >= 'Q' && c <= 'Z') || (c >= 'a' && c <= 'f'))
			serial = 1;

		else if (c >= 'g' && c <= 'v')
			serial = 2;
		else
			serial = 3;

		try {
			return sendGet(serial, id, isSave);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error occured";
		}
	}

	/**
	 * @param id
	 *            key value used to retrieve the encoded url from data store
	 * @return
	 */
	@RequestMapping("/urls/{id}")
	public String getUrl(@PathVariable String id) {
		return serviceCall(id, false);
	}

	private String sendGet(int s, String id, boolean isStore) throws Exception {
		String port = "900";
		port += s;
		String url;
		if (isStore)
			url = "http://localhost:" + port + "/" + storages[s] + "/keys/" + id;
		else
			url = "http://localhost:" + port + "/" + storages[s] + "/url/" + id;

		System.out.println("url = " + url);

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

		return response.toString();

	}

}
