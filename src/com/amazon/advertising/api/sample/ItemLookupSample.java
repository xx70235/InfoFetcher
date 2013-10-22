/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

package com.amazon.advertising.api.sample;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * This class shows how to make a simple authenticated ItemLookup call to the
 * Amazon Product Advertising API.
 * 
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class ItemLookupSample {
    /*
     * Your AWS Access Key ID, as taken from the AWS Your Account page.
     */
    private static final String AWS_ACCESS_KEY_ID = "AKIAILSXW3IFWY5N7YFQ";

    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    private static final String AWS_SECRET_KEY = "hbt3cEErAHz73NF7TpgrfFQ7TQUZ7DRt0cC+VBb2";

    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     * 
     *      US: ecs.amazonaws.com 
     *      CA: ecs.amazonaws.ca 
     *      UK: ecs.amazonaws.co.uk 
     *      DE: ecs.amazonaws.de 
     *      FR: ecs.amazonaws.fr 
     *      JP: ecs.amazonaws.jp
     * 
     */
    private static final String ENDPOINT = "ecs.amazonaws.com";

    /*
     * The Item ID to lookup. The value below was selected for the US locale.
     * You can choose a different value if this value does not work in the
     * locale of your choice.
     */
    private static final String ITEM_ID = "0545010226";

    public static void main(String[] args) {
        /*
         * Set up the signed requests helper 
         */
        SignedRequestsHelper helper;
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
       
        
        String requestUrl = null;
        String title = null;

        /* The helper can sign requests in two forms - map form and string form */
        
        /*
         * Here is an example in map form, where the request parameters are stored in a map.
         */
        System.out.println("Map form example:");
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2011-08-01");
        params.put("Operation", "ItemSearch");
        params.put("SearchIndex", "Books");
        params.put("Keywords", "Harry");
//        params.put("ResponseGroup", "Small");

        requestUrl = helper.sign(params);
//        System.out.println("Signed Request is \"" + requestUrl + "\"");

//        title = fetchTitle(requestUrl);
//        System.out.println("Signed Title is \"" + title + "\"");
//        System.out.println();

        /* Here is an example with string form, where the requests parameters have already been concatenated
         * into a query string. */
//        System.out.println("String form example:");
//        String queryString = "Service=AWSECommerceService&Version=2009-03-31&Operation=ItemSearch&ResponseGroup=Small&SearchIndex=Books&keywords=harry"
//                + ITEM_ID;
//        requestUrl = helper.sign(queryString);
        
        NodeList nodelist = fetchASIN(requestUrl);
        System.out.println(nodelist.getLength());
        for(int i = 0; i<nodelist.getLength();i++)
        {
            
           String asin = nodelist.item(i).getTextContent();
           println(asin);
           getItemInfo(asin, helper);
           break;
        }
      

    }
    
    private static void getItemInfo(String asin,SignedRequestsHelper helper)
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2011-08-01");
        params.put("Operation", "ItemLookup");
        params.put("ItemId", asin);
        params.put("ResponseGroup", "Images");
        String requestUrl = helper.sign(params);
        
        System.out.println("Request is \"" + requestUrl + "\"");
        HttpGet httpGet = new HttpGet(requestUrl);
        String responseBody = "";
        try
        {
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpclient.execute(httpGet, responseHandler);
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(responseBody);
    }
    
    private static void println(String str)
    {
        System.out.println(str);
    }

    /*
     * Utility function to fetch the response from the service and extract the
     * title from the XML.
     */
    private static NodeList fetchASIN(String response) {
        NodeList nodelist = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(response);
            nodelist = doc.getElementsByTagName("ASIN");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodelist;
    }

}
