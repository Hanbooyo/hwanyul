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
			//�Ű����� �����Ͽ� api �ּ� �����
			String authkey = "UdjJHStUnQUoXLeqzzL6sO8aX3SAeDzJ";
			String bDate = "20230116";
			String address = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey="
								+authkey+"&searchdate="+bDate+"&data=AP01";
			
			//��ü ���� �� url ����
			URL url = new URL(address);
			URLConnection urlConn = url.openConnection();
	        
			//������ �޾ƿ���
			InputStreamReader ir = new InputStreamReader(urlConn.getInputStream());
			BufferedReader br = new BufferedReader(ir);
			
			String line;
			line = br.readLine();
			/***** �޾ƿ� json ������ �Ľ��ϱ� *****/
            
			//�ļ� ��ü �����
			JSONParser parser = new JSONParser();
			
			
			// json �����͸� object�� �����
			//JSONObject obj = (JSONObject)parser.parse(line);
			
			// json �����͸� �Ľ��Ͽ� �迭�� ����
			JSONArray arr = (JSONArray)parser.parse(line);
			//out.print(arr);	//�迭�� �� �Ѿ�Գ� Ȯ��
			
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
            
			// ��ȭ���� {} ���������Ƿ� �迭 ������(=��ȭ����)��ŭ �ݺ�
			for(int i=0; i<arr.size(); i++){
				
				// �迭 �ȿ� �ִ� �͵� json �����̱� ������ JSONObject�� ����
				JSONObject obj = (JSONObject)arr.get(i);
				
				String cur_unit1 = (String)obj.get("cur_unit");	//��ȭ�ڵ�
				String cur_nm1 = (String)obj.get("cur_nm");	//������ȭ��
				String ttb1 = (String)obj.get("ttb");	//�۱� �����Ƕ�
				String tts1 = (String)obj.get("tts");	//�۱� �����Ƕ�
				String deal_bas_r1 = (String)obj.get("deal_bas_r");	//�Ÿ� ������
				String bkpr1 = (String)obj.get("bkpr");	//��ΰ���
				String yy_efee_r1 = (String)obj.get("yy_efee_r");	//��ȯ������
				String ten_dd_efee_r1 = (String)obj.get("ten_dd_efee_r");	//10��ȯ������
				String kftc_deal_bas_r1 = (String)obj.get("kftc_deal_bas_r");	//����ܱ�ȯ�߰� �Ÿű�����
				String kftc_bkpr1 = (String)obj.get("kftc_bkpr");	//����ܱ�ȯ�߰� ��ΰ���
				
				//�迭�� �߰�
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
		} catch(Exception e){	//������ URLConnection ���鶧 �� try �ݴ°�
			e.printStackTrace();
		}
		return "home";
	}
	
}