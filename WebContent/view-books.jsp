<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>BookStoreMVC-List of Books</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
}
</style>
<body>
	<h3 align="center">MVC CRUD example using Servlet, JSP & Oracle DB
	</h3>
	<div>
		<input type="button" value="Add Book"
			onclick="window.location.href='add-book-form.jsp'" />
	</div>
	<hr>
	<table style="width: 80%">
		<tr>
			<th>Book Name</th>
			<th>Author Name</th>
			<th>Category</th>
			<th>Action</th>
		</tr>

		<c:forEach var="tempBook" items="${books}">
			<c:url var="updateLink" value="BookControllerServlet">
				<c:param name="action" value="LOAD"/>
				<c:param name="bookName" value="${tempBook.bookName}"/>
			</c:url>
			
			<c:url var="deleteLink" value="BookControllerServlet">
				<c:param name="action" value="DELETE"/>
				<c:param name="bookName" value="${tempBook.bookName}"/>
			</c:url>
			<tr>
				<td>${tempBook.bookName}</td>
				<td>${tempBook.authorName}</td>
				<td>${tempBook.bookCategory}</td>
				<td><a href="${updateLink}"> Update </a> | 
					<a href="${deleteLink}"
						onclick="if(!(confirm('Are you sure to delete this book?'))) return false">
						Delete </a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</head>
</html>