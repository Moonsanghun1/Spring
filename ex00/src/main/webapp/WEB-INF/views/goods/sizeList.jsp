<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<c:forEach items="${sizeList}" var="vo">
		<div class="form-check mb-2 mr-sm-2">
			<label><input class="form-control" type="checkbox" name="size_no" value="${vo.size_no }">
				${vo.size_name }
			</label>
		</div>
	</c:forEach>
