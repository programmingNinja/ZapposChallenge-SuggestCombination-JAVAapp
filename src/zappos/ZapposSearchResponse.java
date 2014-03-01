/**
 * Author: Rohan D. Shah, MS in CS student at utah state university
 * 
 * Description: 
 * ZapposSearchResponse is a class that is used when processing the JSON response from the Zappos Search API.
 * Gson converts the response into a ZapposSearchResponse object from which you can access the individual ZapposItem 
 * objects
 */

package zappos;

public class ZapposSearchResponse 
{
    private String statusCode;
    // stores the results of all the product responses
    private ZapposItem[] results;

    public ZapposSearchResponse(String statusCode, ZapposItem[] results) 
    {
        this.statusCode = statusCode;
        this.results = results;
    }

    public String getStatusCode() 
    {
        return statusCode;
    }

    public ZapposItem[] getResults() 
    {
        // return the result array else
        // return null if there aren't
        // any results from the search
        if (results.length > 0)
                return results;
        else
                return null;
    }
}