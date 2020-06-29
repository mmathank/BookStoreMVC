package book.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BookDbUtil {

	private DataSource dataSource;

	public BookDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<Book> getBooks() throws Exception {

		List<Book> bookList = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "select * from b01_books";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			String name;
			String author;
			String category;

			while (rs.next()) {
				name = rs.getString("book_name");
				author = rs.getString("author_name");
				category = rs.getString("book_category");

				Book book = new Book(name, author, category);
				bookList.add(book);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return bookList;
	}

	public void addBook(Book book) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "insert into b01_books (book_name, author_name, book_category) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getAuthorName());
			pstmt.setString(3, book.getBookCategory());
			pstmt.execute();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Book getBook(String bookName) throws Exception {

		Book book = null;
		String name = null;
		String author = null;
		String category = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "select * from b01_books where book_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("book_name");
				author = rs.getString("author_name");
				category = rs.getString("book_category");
				book = new Book(name, author, category);
			} else {
				throw new Exception("Book Not Found");
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return book;
	}

	public void updateBook(Book book) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = dataSource.getConnection();
			String sql = "update b01_books set book_name=?,author_name=?,book_category=? where book_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getAuthorName());
			pstmt.setString(3, book.getBookCategory());
			pstmt.setString(4, book.getBookName());
			pstmt.execute();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteBook(String bookName) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "delete from b01_books where book_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, bookName);
			pstmt.execute();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
