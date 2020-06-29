<html>
<head>
<title>User Form</title>
</head>
<body>
	<h4>Enter Book Details</h4>
	<form action="BookControllerServlet" method="GET">
		<input type="hidden" name="action" value="ADD"/>
		Enter Book Name: <input type="text" name="bookName"> <br>
		Enter Author Name: <input type="text" name="authorName"> <br>
		Enter Book Category: <input type="text" name="bookCategory"> <br>
		<input type="submit" value="Save"/>
	</form>
	<p><a href="BookControllerServlet">Back to List</a></p>
</body>
</html>