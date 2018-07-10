package com.hangang.HangangRiver.access.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangang.HangangRiver.access.model.ResFacebookLoginPojo;
import com.hangang.HangangRiver.access.model.User;
import com.hangang.HangangRiver.access.service.AccessService;
import com.hangang.HangangRiver.exceptions.LoginValidateException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@RestController
@RequestMapping("/access")
public class AccessController {

	@Autowired
	AccessService accessService;

	@PostMapping("/loginValidate")
	private ResponseEntity<User> loginValidate(HttpServletRequest request, @RequestBody User user){
		try {
			return ResponseEntity.ok().body(submitFacebookLogin(user.getAccess_token()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
			//return "유효하지 않는 사용자입니다.";
			//LoginValidateException??
			return ResponseEntity.badRequest().body(null);
		}
	}

	private User submitFacebookLogin(String accessToken) throws Exception {
		BufferedReader in = null;
		URL obj = new URL("https://graph.facebook.com/me?access_token="+accessToken);
		try {
			HttpURLConnection con = (HttpURLConnection)obj.openConnection();
			con.setRequestMethod("GET");
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String line;
				while((line = in.readLine()) != null) {
					System.out.println(line);
				}
				JSONParser parser = new JSONParser();
				Object parseObj = parser.parse( line );
				JSONObject jsonObj = (JSONObject) parseObj;
				String user_id = (String) jsonObj.get("user_id");
				String name = (String) jsonObj.get("name");
				Boolean existUser = selectExistUser(user_id);//최초로그인인지 확인
				User user = new User();
				user.setUser_id(user_id);
				user.setNickname(name);
				user.setAccess_token(accessToken);
				String hangang_token = hashMD5(accessToken+user_id);
				user.setHangang_token(hangang_token);
				if (existUser){
					accessService.createUser(user);//최초로그인이면 insert
				}else {
					accessService.modifyUser(user_id, user);//아니면 한강토큰 업뎃
				}
				return user;//아이디와 한강토큰
		}catch (Exception e) {
			System.out.println("---------------error------------------");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			//return "유효하지 않는 사용자입니다.";
			//LoginValidateException??
		}
	}

	public Boolean selectExistUser(String user_id){
		return accessService.getUserDetailById(user_id)!=null;
	}

	public String hashMD5(String str){
		String MD5 = ""; 
		try{
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(str.getBytes()); 
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			MD5 = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			MD5 = null; 
		}
		return MD5;
	}

}
