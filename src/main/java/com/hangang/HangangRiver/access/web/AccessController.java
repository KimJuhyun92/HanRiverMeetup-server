package com.hangang.HangangRiver.access.web;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.hangang.HangangRiver.exceptions.NotExistFacebookUserException;
import com.hangang.HangangRiver.exceptions.NotExistUserException;
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

	@PostMapping("/login")
	private ResponseEntity<User> login(HttpServletRequest request, @RequestBody User user)
			throws Exception {

		if(isValidFacebookUser(user.getAccess_token(), user.getUser_id())) {
			User loginedUser = userLogin(user);

			if(loginedUser != null) {
				return ResponseEntity.ok().body(loginedUser);
			}

			throw new NotExistUserException();
		}
		else {
			throw new NotExistFacebookUserException();
		}
	}

	@PostMapping("/register")
	private ResponseEntity<User> register(HttpServletRequest request, @RequestBody User user)
			throws Exception {

		if(isValidFacebookUser(user.getAccess_token(), user.getUser_id())) {
			String hangang_token = hashMD5(user.getAccess_token() + user.getUser_id());
			user.setHangang_token(hangang_token);
			return ResponseEntity.ok().body(accessService.registUser(user.getUser_id(), user));
		}

		throw new NotExistFacebookUserException();

	}

	public boolean isValidFacebookUser(String accessToken, String userID)
			throws Exception {
		URL obj = new URL("https://graph.facebook.com/me?access_token=" + accessToken);
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		con.setRequestMethod("GET");

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject)jsonParser.parse(
				new InputStreamReader(con.getInputStream(), "UTF-8"));

		String facebookUserID = (String) jsonObj.get("id");

		return userID.equals(facebookUserID);
	}

	public User userLogin(User user){
		if (isExistUser(user)){
			String hangang_token = hashMD5(user.getAccess_token() + user.getUser_id());
			user.setHangang_token(hangang_token);
			accessService.modifyUser(user.getUser_id(), user);
			return accessService.getUserDetailById(user.getUser_id());
		}else {
			return null;
		}
	}

	public Boolean isExistUser(User user){
		return accessService.getUserDetailById(user.getUser_id())!=null;
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
