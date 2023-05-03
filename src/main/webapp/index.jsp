<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- jsp:forward page="/egovSampleList.do"/ -->

<h2>Sample</h2>
<ul>
	<li><a href="/egovSampleList.do"><spring:message code="list.sample" /></a></li>
	<li>대기오염 정보
		<ul>
			<li><a href="/arpltnInforInqireSvcList.do">대기질 예보통보</a></li>
		</ul>
	</li>
</ul>