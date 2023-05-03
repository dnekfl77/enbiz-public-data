<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>

<h2>결과: ${result.response.body.totalCount}</h2>
<div>
	pageNo:	${result.response.body.pageNo}, numOfRows: ${result.response.body.numOfRows}
</div>

<ul>
	<c:forEach items="${result.response.body.items}" var="item" varStatus="vs">
		<li>${vs.count}: ${item}</li>
	</c:forEach>
</ul>