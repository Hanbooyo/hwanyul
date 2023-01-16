package com.ex.hwanyul;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		try{
			//매개변수 포함하여 api 주소 만들기
			String authkey = "UdjJHStUnQUoXLeqzzL6sO8aX3SAeDzJ";
			String bDate = "20230116";
			String address = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey="
								+authkey+"&searchdate="+bDate+"&data=AP01";
			
			//객체 생성 및 url 연결
			URL url = new URL(address);
			URLConnection urlConn = url.openConnection();
	        
			//데이터 받아오기
			InputStreamReader ir = new InputStreamReader(urlConn.getInputStream());
			BufferedReader br = new BufferedReader(ir);
			
			String line;
			line = br.readLine();
			/***** 받아온 json 데이터 파싱하기 *****/
            
			//파서 객체 만들기
			JSONParser parser = new JSONParser();
			
			
			// json 데이터를 object로 만들기
			//JSONObject obj = (JSONObject)parser.parse(line);
			
			// json 데이터를 파싱하여 배열로 추출
			JSONArray arr = (JSONArray)parser.parse(line);
			//out.print(arr);	//배열로 잘 넘어왔나 확인
			
			ArrayList<String> cur_unit = new ArrayList<String>();
			ArrayList<String> cur_nm = new ArrayList<String>();
			ArrayList<String> ttb = new ArrayList<String>();
			ArrayList<String> tts = new ArrayList<String>();
			ArrayList<String> deal_bas_r = new ArrayList<String>();
			ArrayList<String> bkpr = new ArrayList<String>();
			ArrayList<String> yy_efee_r = new ArrayList<String>();
			ArrayList<String> ten_dd_efee_r = new ArrayList<String>();
			ArrayList<String> kftc_deal_bas_r = new ArrayList<String>();
			ArrayList<String> kftc_bkpr = new ArrayList<String>();
            
			// 통화별로 {} 묶여있으므로 배열 사이즈(=통화종류)만큼 반복
			for(int i=0; i<arr.size(); i++){
				
				// 배열 안에 있는 것도 json 형식이기 때문에 JSONObject로 추출
				JSONObject obj = (JSONObject)arr.get(i);
				
				String cur_unit1 = (String)obj.get("cur_unit");	//통화코드
				String cur_nm1 = (String)obj.get("cur_nm");	//국가통화명
				String ttb1 = (String)obj.get("ttb");	//송금 받으실때
				String tts1 = (String)obj.get("tts");	//송금 보내실때
				String deal_bas_r1 = (String)obj.get("deal_bas_r");	//매매 기준율
				String bkpr1 = (String)obj.get("bkpr");	//장부가격
				String yy_efee_r1 = (String)obj.get("yy_efee_r");	//년환가료율
				String ten_dd_efee_r1 = (String)obj.get("ten_dd_efee_r");	//10일환가료율
				String kftc_deal_bas_r1 = (String)obj.get("kftc_deal_bas_r");	//서울외국환중계 매매기준율
				String kftc_bkpr1 = (String)obj.get("kftc_bkpr");	//서울외국환중계 장부가격
				
				//배열에 추가
				cur_unit.add(cur_unit1);
				cur_nm.add(cur_nm1);
				ttb.add(ttb1);
				tts.add(tts1);
				deal_bas_r.add(deal_bas_r1);
				bkpr.add(bkpr1);
				yy_efee_r.add(yy_efee_r1);
				ten_dd_efee_r.add(ten_dd_efee_r1);
				kftc_deal_bas_r.add(kftc_deal_bas_r1);
				kftc_bkpr.add(kftc_bkpr1);
			}	
			br.close();
			ir.close();
			model.addAttribute("cur_unit",cur_unit);
			model.addAttribute("cur_nm",cur_nm);
			model.addAttribute("ttb",ttb);
			model.addAttribute("tts",tts);
			model.addAttribute("deal_bas_r",deal_bas_r);
			model.addAttribute("bkpr",bkpr);
			model.addAttribute("yy_efee_r",yy_efee_r);
			model.addAttribute("ten_dd_efee_r",ten_dd_efee_r);
			model.addAttribute("kftc_deal_bas_r",kftc_deal_bas_r);
			model.addAttribute("kftc_bkpr",kftc_bkpr);
		} catch(Exception e){	//위에서 URLConnection 만들때 연 try 닫는것
			e.printStackTrace();
		}
		return "home";
	}
	
}