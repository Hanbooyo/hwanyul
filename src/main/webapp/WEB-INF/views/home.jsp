<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">


</script>
<title>Insert title here</title>
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
		<c:forEach var="i" begin="0" end="22">
			<tr>
				<td>${cur_nm[i]} (${cur_unit[i]})</td>
				<td>${ttb[i]}</td>
				<td>${tts[i]}</td>
				<td>${deal_bas_r[i]}</td>
				<td>${bkpr[i]}</td>
				<td>${yy_efee_r[i]}</td>
				<td>${ten_dd_efee_r[i]}</td>
				<td>${kftc_deal_bas_r[i]}</td>
				<td>${kftc_bkpr[i]}</td>
			</tr>
		</c:forEach>
		
			
	</body>
</html>