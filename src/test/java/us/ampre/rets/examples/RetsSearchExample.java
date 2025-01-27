package us.ampre.rets.examples;

import java.net.MalformedURLException;

import org.apache.commons.lang3.StringUtils;
import us.ampre.rets.client.CommonsHttpClient;
import us.ampre.rets.client.RetsException;
import us.ampre.rets.client.RetsHttpClient;
import us.ampre.rets.client.RetsSession;
import us.ampre.rets.client.RetsVersion;
import us.ampre.rets.client.SearchRequest;
import us.ampre.rets.client.SearchResultImpl;

/**
 * Simple Example performing a search and iterating over the results
 */
public class RetsSearchExample {

    public static void main(String[] args) throws MalformedURLException {

        //Create a RetsHttpClient (other constructors provide configuration i.e. timeout, gzip capability)
        RetsHttpClient httpClient = new CommonsHttpClient();
        RetsVersion retsVersion = RetsVersion.RETS_1_7_2;
        String loginUrl = "http://theurloftheretsserver.com";

        //Create a RetesSession with RetsHttpClient
        RetsSession session = new RetsSession(loginUrl, httpClient, retsVersion);

        String username = "username";
        String password = "password";

        //Set method as GET or POST
        session.setMethod("POST");
        try {
            //Login
            session.login(username, password);
        } catch (RetsException e) {
            e.printStackTrace();
        }

        String sQuery = "(Member_num=.ANY.)";
        String sResource = "Property";
        String sClass = "Residential";

        //Create a SearchRequest
        SearchRequest request = new SearchRequest(sResource, sClass, sQuery);

        //Select only available fields
        String select = "field1,field2,field3,field4,field5";
        request.setSelect(select);

        //Set request to retrive count if desired
        request.setCountFirst();

        SearchResultImpl response;
        try {
            //Execute the search
            response = (SearchResultImpl) session.search(request);

            //Print out count and columns
            int count = response.getCount();
            System.out.println("COUNT: " + count);
            System.out.println("COLUMNS: " + StringUtils.join(response.getColumns(), "\t"));

            //Iterate over, print records
            for (int row = 0; row < response.getRowCount(); row++) {
                System.out.println("ROW" + row + ": " + StringUtils.join(response.getRow(row), "\t"));
            }
        } catch (RetsException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.logout();
                } catch (RetsException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}