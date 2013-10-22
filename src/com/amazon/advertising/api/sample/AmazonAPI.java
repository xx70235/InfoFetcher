package com.amazon.advertising.api.sample;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.apis.models.AmazonItems;
import com.selectyourgoods.utils.DatabaseUtils;

public class AmazonAPI
{

    private final String AWS_ACCESS_KEY_ID = "AKIAILSXW3IFWY5N7YFQ";

    private final String AWS_SECRET_KEY = "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";

    private final String ENDPOINT = "webservices.amazon.com";

    SignedRequestsHelper helper;

    public AmazonAPI()
    {
        try
        {
            helper = SignedRequestsHelper.getInstance(ENDPOINT,
                    AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public List<String> getASINbyKeyword(List<String> SearchIndex,
            String keyword)
    {
        // if(SearchIndex.isEmpty())
        // {
        // SearchIndex.add("All");
        // }
        String si = "";
        for (String str : SearchIndex)
        {
            si += str + "&";
        }
        si = si.substring(0, si.length() - 1);
        ArrayList<String> asinList = new ArrayList<String>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2011-08-01");
        params.put("Operation", "ItemSearch");
        params.put("SearchIndex", si);
        params.put("Keywords", keyword);

        String requestUrl = helper.sign(params);

        NodeList nodelist = fetchASIN(requestUrl);
        System.out.println(nodelist.getLength());
        for (int i = 0; i < nodelist.getLength(); i++)
        {

            String asin = nodelist.item(i).getTextContent();
            asinList.add(asin);
            println(asin);
            // getItemInfo(asin, helper);
        }

        return asinList;
    }

    public void getItemInfo(List<String> asinList,
            HashSet<String> atrributesList)
    {
        String si = "";
        for (String str : atrributesList)
        {
            si += str + ",";
        }
        si = si.substring(0, si.length() - 1);
        for (String asin : asinList)
        {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            Map<String, String> params = new HashMap<String, String>();
            params.put("Service", "AWSECommerceService");
            params.put("Version", "2011-08-01");
            params.put("Operation", "ItemLookup");
            params.put("ItemId", asin);
            // TODO:设置不同的response group
            params.put("ResponseGroup", si);
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

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            try
            {
                db = dbf.newDocumentBuilder();
                StringReader sr = new StringReader(responseBody);
                InputSource is = new InputSource(sr);
                Document doc = db.parse(is);
                AmazonItems ai = new AmazonItems();
                ai.setItemId(this.fetchInfo(doc, "ItemId")[0]);
                ai.setBinding(this.fetchInfo(doc, "Binding")[0]);
                ai.setAuthor(this.fetchInfo(doc, "Author")[0]);
                ai.setBrand(this.fetchInfo(doc, "Brand")[0]);
                ai.setLabel(this.fetchInfo(doc, "Label")[0]);
                ai.setFeature(this.fetchInfo(doc, "Feature"));
                ai.setCurrencyCode(this.fetchInfo(doc, "CurrencyCode")[0]);
                ai.setFormattedPrice(this.fetchInfo(doc, "FormattedPrice")[0]);
                ai.setManufacturer(this.fetchInfo(doc, "Manufacturer")[0]);
                ai.setProductTypeName(this.fetchInfo(doc, "ProductTypeName")[0]);
                ai.setStudio(this.fetchInfo(doc, "Studio")[0]);
                ai.setTitle(this.fetchInfo(doc, "Title")[0]);
                ai.setPublisher(this.fetchInfo(doc, "Publisher")[0]);

                ai.setProductGroup(this.fetchInfo(doc, "ProductGroup")[0]);
                ai.setSmallImage(this.fetchImageUrl(doc, "SmallImage"));
                ai.setMidImage(this.fetchImageUrl(doc, "MediumImage"));
                ai.setLargeImage(this.fetchImageUrl(doc, "LargeImage"));
                ai.setReviewsIFrameURL(this.fetchInfo(doc, "IFrameURL")[0]);
                ai.setEditorialReviews(this.fetchEditorialReviews(doc,
                        "EditorialReview"));
                ai.setCustomerReviews(this.fetchInfo(doc, "CustomerReviews")[0]);
                this.instock(ai);
                
                //TODO:
                //将得到的信息入库
                
            }
           
            catch (ParserConfigurationException e)
            {
                e.printStackTrace();
            }
            catch (SAXException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        System.out.println("Done");
    }

    private String[] fetchInfo(Document doc, String itemName)
    {

        NodeList itemlist = doc.getElementsByTagName(itemName);
        if (itemlist.getLength() != 0)
        {
            String[] info = new String[itemlist.getLength()];
            for (int i = 0; i < itemlist.getLength(); i++)
            {
                info[i] = itemlist.item(i).getTextContent();
            }

            return info;
        }
        else
        {
            String[] info = new String[1];
            return info;
        }
    }

    private String fetchImageUrl(Document doc, String itemName)
    {
        NodeList itemlist = doc.getElementsByTagName(itemName);
        if (itemlist.getLength() < 1)
            return "";
        NodeList childList = itemlist.item(0).getChildNodes();
        for (int i = 0; i < childList.getLength(); i++)
        {
            if (childList.item(i).getNodeName().equals("URL"))
                return childList.item(i).getTextContent();
        }
        return "";
    }

    private String fetchEditorialReviews(Document doc, String itemName)
    {
        NodeList itemlist = doc.getElementsByTagName(itemName);
        if (itemlist.getLength() < 1)
            return "";
        NodeList childList = itemlist.item(0).getChildNodes();
//        NodeList childList = childList1.item(0).getChildNodes();
        for (int i = 0; i < childList.getLength(); i++)
        {
            System.out.println(childList.item(i).getNodeName());
            if (childList.item(i).getNodeName().equals("Content"))
                return childList.item(i).getTextContent();
        }
        return "";
    }

    private void instock(AmazonItems ai)
    {
        String feature = "";
        if(ai.getFeature().length>0)
        {
            for(String tmp:ai.getFeature())
            {
                feature+=tmp+"|";
            }
            feature = (String)feature.subSequence(0, feature.length()-1);
        }
        String sql= "insert into products (ItemID,Title,Binding,Author,Brand,Label,Feature,CurrencyCode,FormattedPrice,Manufacturer," +
        		"ProductTypeName,Studio,Publisher,ProductGroup,SmallImage,MediumImage,LargeImage,EditorialReviews) values " +
        		"('"+ai.getItemId()+"','"+ai.getTitle()+"','"+ai.getBinding()+"','"+ai.getAuthor()+"','"+ai.getBrand()+"','"+ai.getLabel()+"','"
        		+feature+"','"+ai.getCurrencyCode()+"','"+ai.getFormattedPrice()+"','"+ai.getManufacturer()+"','"+ai.getProductTypeName()+"','"
        		+ai.getStudio()+"','"+ai.getPublisher()+"','"+ai.getProductGroup()+"','"+ai.getSmallImage()+"','"+ai.getMidImage()+"','"+ai.getLargeImage()+"','"
        		+ai.getEditorialReviews()+"'"
        		+")";
        System.out.println(sql);
        DatabaseUtils.sqlDatabase(sql); 
    }

    private static void println(String str)
    {
        System.out.println(str);
    }

    /*
     * Utility function to fetch the response from the service and extract the
     * title from the XML.
     */
    private NodeList fetchASIN(String response)
    {
        NodeList nodelist = null;
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(response);
            nodelist = doc.getElementsByTagName("ASIN");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return nodelist;
    }

}
