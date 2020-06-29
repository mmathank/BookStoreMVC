<html>
<head>
<title>User Form</title>
</head>
<body>
	<h4>Update Book Details</h4>
	<form action="BookControllerServlet" method="GET">
		<input type="hidden" name="action" value="UPDATE"/>
		<input type="hidden" name="bookName" value="${selectedBook.bookName}"/>
		Enter Book Name: <input type="text" name="bookName" value="${selectedBook.bookName}"> <br>
		Enter Author Name: <input type="text" name="authorName"> <br>
		Enter Book Category: <input type="text" name="bookCategory"> <br>
		<input type="submit" value="Save"/>
	</form>
	<p><a href="BookControllerServlet">Back to List</a></p>
</body>
</html>