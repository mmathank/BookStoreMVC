package book.store;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class BookControllerServlet
 */
@WebServlet("/BookControllerServlet")
public class BookControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/MyLocalDB")
	private DataSource dataSource;

	private BookDbUtil bookDbUtil;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			bookDbUtil = new BookDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookControllerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			action = "LIST";
		}

		switch (action) {
		case "LIST":
			listBooks(request, response);
			break;
		case "ADD":
			addBook(request, response);
			break;
		case "LOAD":
			loadBook(request, response);
			break;
		case "UPDATE":
			updateBook(request, response);
			break;
		case "DELETE":
			deleteBook(request,response);
			break;
		default:
			listBooks(request, response);
		}
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
		try {
			String bookName = request.getParameter("bookName");
			bookDbUtil.deleteBook(bookName);
			listBooks(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) {

		try {
			String bookName = request.getParameter("bookName");
			String authorName = request.getParameter("authorName");
			String bookCategory = request.getParameter("bookCategory");

			Book book = new Book(bookName, authorName, bookCategory);
			bookDbUtil.updateBook(book);
			listBooks(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadBook(HttpServletRequest request, HttpServletResponse response) {
		try {

			String bookName = request.getParameter("bookName");
			Book book = bookDbUtil.getBook(bookName);
			request.setAttribute("selectedBook", book);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-book-form.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) {

		try {
			String bookName = request.getParameter("bookName");
			String authorName = request.getParameter("authorName");
			String bookCategory = request.getParameter("bookCategory");

			Book book = new Book(bookName, authorName, bookCategory);
			bookDbUtil.addBook(book);

			listBooks(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<Book> bookList = bookDbUtil.getBooks();
			request.setAttribute("books", bookList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view-books.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
