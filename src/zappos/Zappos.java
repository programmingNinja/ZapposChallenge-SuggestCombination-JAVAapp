/**
 * Author: Rohan D. Shah, MS in CS student at utah state university
 * 
 * Description: 
 * Zappos application searches for products based on keywords of the user, and returns back a list of products
 * matching the keyword and computes various combinations of products based on the number of items the user wants to buy
 * and the price they want to spend on those items in total.
 */


package zappos;

import java.io.*;
import java.net.*;
import java.util.*;

// used for converting JSON to ZapposItems
import com.google.gson.Gson;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Zappos 
{

    // constants used for searching with the Zappos API
    
    // limiting the responses of products.
    private static final int LIMIT = 10;
    // used in url for searching products
    private static final String PRODUCT_SEARCH = "http://api.zappos.com/Product?id=";
    // used in url for searching items
    private static final String SEARCH = "http://api.zappos.com/Search/term/";        
    private static final String LIMIT_STRING = "?limit="+LIMIT;       

    // add your Zappos API Key here to use Zappos and let the application make api calls
    private static final String API_KEY = "&key=b05dcd698e5ca2eab4a0cd1eee4117e7db2a10c4";    


    public static void main(String[] args) 
    {
        ZapposFrame a = new ZapposFrame("Zappos suggestMe App", LIMIT);
    }

    // method used to make all URL requests returns the JSON string to 
    // the caller to be interpreted there
    private static String httpGet(String urlStr) throws IOException 
    {

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (connection.getResponseCode() != 200) {
                throw new IOException(connection.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseString = new StringBuilder();

        String line;
        while ((line = rd.readLine()) != null) 
                responseString.append(line);
        rd.close();

        connection.disconnect();
        return responseString.toString();
    }

    // method to search the Zappos Search (and Product) API for the term
    // that the user entered.  Returns the first resulting item from the search term.
    public static ZapposItem[] getZapposItemForSearchTerm(String searchTerm) throws IOException
    {

        String URLRequest = SEARCH + searchTerm + LIMIT_STRING + API_KEY;
        Gson gson = new Gson();
        // collects the searched item
        ZapposItem [] item = new ZapposItem[LIMIT];

        // intializes the array that stores the item
        for(int a = 0 ; a < LIMIT ; a++)
            item[a] = new ZapposItem();

        // stores the response of the request
        String response = httpGet(URLRequest);
        // converting the json response to the string format
        ZapposSearchResponse  theResponse = gson.fromJson(response, ZapposSearchResponse.class);
        // getResults returns the array of item objects which is stored in the local item array
        item = theResponse.getResults();

        // searching the products based on the productids retrieved from response.
        for(int i=0 ; i<LIMIT ; i++)
        { 
            String productId = item[i].getProductId();
            String productResponse = httpGet(PRODUCT_SEARCH + productId + API_KEY);

            ZapposProductSearchResponse product = gson.fromJson(productResponse, ZapposProductSearchResponse.class);
            // setting the image url of each product
            item[i].setDefaultImageUrl(product.getProduct().getDefaultImageUrl());
        }
        return item;
    }
}
