package com.kh.api.kakao.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.kh.api.kakao.model.SocialMember;


@Service
public class KakaoService {

   public String getToken(String code) throws IOException, ParseException {
      
      String tokenUrl = "https://kauth.kakao.com/oauth/token";
      URL url = new URL(tokenUrl);
      HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
      
      urlConnection.setRequestMethod("POST");
      urlConnection.setDoOutput(true); // POST 일때는 무조건 있어야함.
      
      BufferedWriter bw = 
                     new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
      
      StringBuilder sb = new StringBuilder();
      sb.append("client_id=ab97d365595a74923135c285d82ea29f");
      sb.append("&grant_type=authorization_code");
      sb.append("&redirect_uri=http://localhost/api/oauth");
      sb.append("&code=");
      sb.append(code);
      
      bw.write(sb.toString());
      bw.flush();
      
      //System.out.println(urlConnection.getResponseCode());
      
      BufferedReader br =
                      new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
      
      String line = "";
      String responseData = "";
      
      while((line = br.readLine()) != null) {
         responseData += line;
      }
      
      //System.out.println(responseData);
      
      JSONParser parser = new JSONParser();
      JSONObject element = (JSONObject)parser.parse(responseData);
      
      String accessToken = element.get("access_token").toString();
      
      br.close();
      bw.close();
      
      return accessToken;
   }

   public void logout(String accessToken) {
      
      String logoutUrl = "https://kapi.kakao.com/v1/user/logout";
      
      URL url;
      HttpURLConnection conn;
      
      try {
         url = new URL(logoutUrl);
         conn = (HttpURLConnection)url.openConnection();
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Authorization", "Bearer " + accessToken);
         
         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         
         String responseData = "";
         String line = "";
         while((line = br.readLine()) != null) {
            responseData = line;
         }
         
         System.out.println(responseData);
         
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }

   public SocialMember getUserInfo(String accessToken) {
      
      String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
      SocialMember sm = null;
      URL url;
      try {
         url = new URL(userInfoUrl);
         HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
         urlConnection.setRequestMethod("GET");
         urlConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
         
         BufferedReader br = 
                         new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
         
         String responseData = br.readLine();
         
         System.out.println(responseData);
         
         JSONObject responseObj = (JSONObject)new JSONParser().parse(responseData);
         
         JSONObject propObj = (JSONObject)responseObj.get("properties");
         
         //long id = (long)responseObj.get("id");
         
         sm = new SocialMember();
         sm.setId(responseObj.get("id").toString());
         sm.setNickName(propObj.get("nickname").toString());
         sm.setThumbnailImg(propObj.get("thumbnail_image").toString());
         
         //System.out.println(sm);
         /*
         result obj = mapper.selectById(sm);
         
         if(obj != null) {
            return obj;
         } else {
            mapper.save(obj);
         }
         */
         
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch(IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
      
      return sm;
      
   }
   
   /*
   public void login {
      mapper.login(member);
   }
   */
}
