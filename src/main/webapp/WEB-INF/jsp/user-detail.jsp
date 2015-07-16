<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<h3>Logged In User is : ${user.name}</h3>




 <%-- <table class="table table-bodered table-hover table-striped">
	<thead>
		<tr>
			<th>Role Name</th>
		</tr>
	</thead>
	<c:forEach items="${user.roles}" var="role">
		<tbody>
			<tr>
				<td>${role.name}</td>
			</tr>
		</tbody>
	</c:forEach>
</table>  --%>


<%--for blog to get the details of each item --%>
 <c:forEach items="${user.blogs}" var="blog">
	<h1>${blog.name}</h1>
	<p>${blog.url}</p>
	
	<table class="table table-bodered table-hover table-striped">
		<thead>
			<tr>
				<th>Title</th>
				<th>Link</th>
			</tr>		
		</thead>
		
		<tbody>
			<c:forEach items="${blog.items}" var="item">
				<tr>
					<td>${item.title}</td>
					<td>${item.link}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:forEach> 
