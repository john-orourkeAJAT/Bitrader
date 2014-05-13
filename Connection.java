/*
* Bitrader Alpha CLI Tests
* ----- Authors: Tyler McCabe && John O'Rourke-----
* Last Edited 5-12-2014 -T
* Connection to Coinbase account to execute API commands via REST
* See README for more information
*
*/

package bitrader;


 

import java.io.IOException; //Whole lotta HTTP related libraries
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Connection{

    static String API_KEY = "cfRLryi3kiOvgh71"; //Will send Privatly

    static String API_SECRET = "oXcjTzlaZaD4IEXFnNIMpQkPYLQIKNAp"; //Will send Privatly
    
    public static String getHttp(String url, String body) //getHTTP method called in Main() for Coinbase connections
            throws InvalidKeyException, NoSuchAlgorithmException, // Error handling 
            ClientProtocolException, IOException {
        
        String nonce = String.valueOf(System.currentTimeMillis()); // Nonce creation
        String message = nonce + url + (body != null ? body : null);
        
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(API_SECRET.getBytes(), "HmacSHA256"));
        String signature = new String(Hex.encodeHex(mac.doFinal(message.getBytes()))); //General security for POST 
        
        HttpRequestBase request;    
        if (body == null || body.length() == 0)
            request = new HttpGet(url);
        else {
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(body)); //Sets POST body (ie: JSON info)
            request = post;
        }
        System.out.println("Message:" + message);   //For Debugging, can remove at later date
        System.out.println("Signature:" + signature);
        System.out.println("Nonce:" + nonce);
        
        request.setHeader("ACCESS_KEY", API_KEY); //Sets POST header
        request.setHeader("ACCESS_SIGNATURE", signature);
        request.setHeader("ACCESS_NONCE", nonce);
        request.setHeader("Content-Type", "application/json"); //Had to add to the vanilla script for buy/sell 
        
        HttpClient httpClient = HttpClientBuilder.create().build(); // Sends final POST request
        HttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity(); //Grabs response from Coinbase server
        if (entity != null)
            return EntityUtils.toString(entity);
        return null;
    }
}