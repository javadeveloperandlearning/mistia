
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.seguridad.model.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javadeveloper
 */
public class WSClient {
    
    
    
    public static void main(String[] args) {
        
        try {
            WSClient  wSClient =  WSClient.getInstance();
            
            Usuario usuario =  new Usuario();
            usuario.setCodUsua("MQUISPE");
            usuario.setClave("admin");
            
            wSClient.doRequest("inciarsession.html", usuario);
        } catch (IOException ex) {
            Logger.getLogger(WSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private final String USER_AGENT = "Mozilla/5.0";
    private static WSClient INSTANCE;


    private static String  BASE_URL =  "http://localhost:8080/mistia-ws/rest/usuario/";

    //private static String  BASE_URL =  "http://10.0.2.2:8080/mistia-web/rest/ejecucion/";

    private WSClient(){

    }


    public static WSClient  getInstance(){
        if(INSTANCE == null){
            INSTANCE =  new WSClient();
        }
        return INSTANCE;
    }




    public Response doRequest(String url, Map<String,String> params) throws IOException {

        Response result= null;
        //String url = "http://www.google.com/search?q=mkyong";
        Set<String> keys =  params.keySet();

        String lsparams = "";
        for (String key :keys ) {

            if(lsparams.equals("")){
                lsparams+="?"+params.get(key);
            }else{
                lsparams+="&"+params.get(key);
            }
        }

        System.out.println(" ### PAREMTROS " +lsparams);
        //StringBuilder _url =  new StringBuilder(BASE_URL).append(url).append(params);
        StringBuilder _url =  new StringBuilder(BASE_URL).append(url);
        System.out.println(" URL : "+_url.toString());

        URL obj = new URL(_url.toString());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setConnectTimeout(5000);
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to   URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }


        in.close();

        System.out.println(response.toString());
        Gson gson  =  new Gson();

        String _response =  response.toString();

        if(response!=null){
            result = gson.fromJson( _response, Response.class);
        }
        return result;
    }
    
    
     public Response doRequest(String path, Object object ) throws IOException {

         Response response =  null;

	  try {
		URL url = new URL(BASE_URL+path);
                System.out.println("##### utl : "+BASE_URL+path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		//String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
                Gson gson =  new Gson();
                String input  =  gson.toJson(object);

		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		/*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}*/

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                StringBuilder content =  new StringBuilder("");
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
                        content.append(output);
                       
		}
		conn.disconnect();
                
               System.out.println(" content :"+content.toString());
               response =  gson.fromJson(content.toString(), Response.class);
                
                

	  } catch (MalformedURLException e) {
		e.printStackTrace();
	  } catch (IOException e) {
		e.printStackTrace();

	 }
          
          
          
          return response;
    }
    
    
    
}
