<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>out标签的使用</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>标签</td>
			<td>值</td>
		</tr>
		<tr>
			<td>订单编号</td>
			<td><c:out value="${order.id}"></c:out></td>
		</tr>
		<tr>
			<td>订单年份</td>
			<td><c:out value="${order.orderYear}"></c:out></td>
		</tr>
		<tr>
			<td>订单价格</td>
			<td><c:out value="${order.orderPrice}"></c:out></td>
		</tr>
		<tr>
			<td>顾客</td>
			<td><c:out value="${order.customer}"></c:out></td>
		</tr>
	</table>
</body>
</html>