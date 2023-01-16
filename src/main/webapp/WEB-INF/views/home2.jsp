<!DOCTYPE html>
<meta charset="utf-8">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.net.*, java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%
request.setCharacterEncoding("utf-8");
%>
<style>
	#weather {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
	}
</style>
<html>
	<head>
	
		<%
			try{
				//매개변수 포함하여 api 주소 만들기
				String authkey = "YsKnDJpc3D7qn2VELnGjm6Ay8yUxbs64";
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
				

		%>
		
	</head>
<body>
		
			<table border=1px cellspacing=0px>
		<tr>
			<th>통화명 (통화코드)</th>
			<th>송금 받으실 때</th>
			<th>송금 보내실 때</th>
			<th>매매기준율</th>
			<th>장부가격</th>
			<th>년환가료율</th>
			<th>10일환가료율</th>
			<th>서울외국환중계<br>매매기준율</th>
			<th>서울외국환중계<br>장부가격</th>
		</tr>
		
				<%
				
				/***** 받아온 json 데이터 파싱하기 *****/
                
				//파서 객체 만들기
				JSONParser parser = new JSONParser();
				
				
				// json 데이터를 object로 만들기
				//JSONObject obj = (JSONObject)parser.parse(line);
				
				// json 데이터를 파싱하여 배열로 추출
				JSONArray arr = (JSONArray)parser.parse(line);
				//out.print(arr);	//배열로 잘 넘어왔나 확인
				
                
				// 통화별로 {} 묶여있으므로 배열 사이즈(=통화종류)만큼 반복
				for(int i=0; i<arr.size(); i++){
					
					// 배열 안에 있는 것도 json 형식이기 때문에 JSONObject로 추출
					JSONObject obj = (JSONObject)arr.get(i);
					
					String cur_unit = (String)obj.get("cur_unit");	//통화코드
					String cur_nm = (String)obj.get("cur_nm");	//국가/통화명
					String ttb = (String)obj.get("ttb");	//송금 받으실때
					String tts = (String)obj.get("tts");	//송금 보내실때
					String deal_bas_r = (String)obj.get("deal_bas_r");	//매매 기준율
					String bkpr = (String)obj.get("bkpr");	//장부가격
					String yy_efee_r = (String)obj.get("yy_efee_r");	//년환가료율
					String ten_dd_efee_R = (String)obj.get("ten_dd_efee_r");	//10일환가료율
					String kftc_deal_bas_r = (String)obj.get("kftc_deal_bas_r");	//서울외국환중계 매매기준율
					String kftc_bkpr = (String)obj.get("kftc_bkpr");	//서울외국환중계 장부가격
					
                    
					// 출력
				%>
					<tr>
						<td><%=cur_nm%> (<%=cur_unit%>)</td>
						<td><%=ttb%></td>
						<td><%=tts%></td>
						<td><%=deal_bas_r%></td>
						<td><%=bkpr%></td>
						<td><%=yy_efee_r%></td>
						<td><%=ten_dd_efee_R%></td>
						<td><%=kftc_deal_bas_r%></td>
						<td><%=kftc_bkpr%></td>
					</tr>	
				<%
				}
				
				br.close();
				ir.close();
                
			} catch(Exception e){	//위에서 URLConnection 만들때 연 try 닫는것
				e.printStackTrace();
			}
		%>
		<div class="cal">
			<div class="in"> 
				<input type="number">
				<select>
					<option></option>
				</select>
			</div>
		</div>
		
	</body>
</html>