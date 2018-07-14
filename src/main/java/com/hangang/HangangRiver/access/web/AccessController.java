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

import com.hangang.HangangRiver.access.model.User;
import com.hangang.HangangRiver.access.service.AccessService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@RestController
@RequestMapping("/access")
public class AccessController {

	@Autowired
	AccessService accessService;

	@PostMapping("/loginValidate")
	private ResponseEntity<User> loginValidate(HttpServletRequest request, @RequestBody User user)
			throws Exception {
		return ResponseEntity.ok().body(submitFacebookLogin(user.getAccess_token(),user.getUser_id()));
	}

	private User submitFacebookLogin(String accessToken, String user_id)
			throws Exception {
		User faceUser =faceBookUserInfoValidate(accessToken, user_id);
		User userInfo = null;
		if (faceUser != null){
			userInfo = saveUserInfo(faceUser);
		}
		return userInfo;
	}

	public Boolean selectExistUser(User user){
		return accessService.getUserDetailById(user.getUser_id())!=null;
	}

	public User faceBookUserInfoValidate(String accessToken, String user_id)
			throws Exception {
		BufferedReader in = null;
		URL obj = new URL("https://graph.facebook.com/me?access_token="+accessToken);
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		con.setRequestMethod("GET");
		in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String line;
		String readLine = null;
		while((line = in.readLine()) != null) {
			if (line != null){
				readLine =line;
			}
		}
		JSONParser parser = new JSONParser();
		Object parseObj = parser.parse(readLine);
		JSONObject jsonObj = (JSONObject) parseObj;
		String face_user_id = (String) jsonObj.get("id");
		String face_name = (String) jsonObj.get("name");
		User user = new User();
		if (user_id.equals(face_user_id)){
			user.setNickname(face_name);
			user.setAccess_token(accessToken);
			user.setUser_id(face_user_id);
			return user;
		}else {
			return user;
		}
	}

	public User saveUserInfo(User user){
		Boolean existUser = selectExistUser(user);//최초로그인인지 확인
		user.setUser_id(user.getUser_id());
		String hangang_token = hashMD5(user.getAccess_token()+user.getUser_id());
		user.setHangang_token(hangang_token);
		if (existUser){
			accessService.modifyUser(user.getUser_id(), user);//아니면 한강토큰 업뎃
		}else {
			accessService.createUser(user);//최초로그인이면 insert
		}
		return accessService.getUserDetailById(user.getUser_id());
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
