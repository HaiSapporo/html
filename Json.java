package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.jar.JarException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class xxxxxxxxxx {
	public static void main(String args[]) // static method
	{
		String url = "https://jsonplaceholder.typicode.com/todos";
		//Creating string of JSON data   
        String jsonData = "{\"languages\" : [{\"name\": \"Java\", \"description\":"  
                + " \" Java is a class-based high-level programming language that"  
                + " follows the OOPs concepts.\"},{\"name\": \"Javascript\","  
                + "\"description\": \"JavaScript is also a high-level, often "  
                + "just-in-time compiled, and multi-paradigm programming language."  
                + "\"},{\"name\": \"Python\",\"description\": \"Python is another "  
                + "high-level, interpreted and general-purpose programming language."  
                + "\"}]}";  
          
        JSONParser parser = new JSONParser();

        try  {

            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            String description = (String) jsonObject.get("description");
            System.out.println(description);

            // loop array
//            JSONArray msg = (JSONArray) jsonObject.get("messages");
//            Iterator<String> iterator = msg.iterator();
//            while (iterator.hasNext()) {
//                System.out.println(iterator.next());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } 

	}

	@SuppressWarnings("unchecked")
	public static void jsonGetRequestURL(String urlQueryString) {
		try {
			JSONParser parser = new JSONParser();

			try {
				URL oracle = new URL(urlQueryString); // URL to Parse
				URLConnection yc = oracle.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					Object objcccc = parser.parse(inputLine);
					JSONArray array = new JSONArray();
					array.add(objcccc);
					// JSONArray a = (JSONArray) parser.parse(inputLine);

					// Loop through each item
					for (Object o : array) {
						JSONObject tutorials = (JSONObject) o;

						String id = (String) tutorials.get("id");
						System.out.println("Post Title : " + id);

						System.out.println("\n");
					}
				}
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
