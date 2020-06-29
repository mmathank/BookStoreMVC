package book.store;

public class Book {

	String bookName;
	String authorName;
	String bookCategory;

	public Book(String bookName, String authorName, String bookCategory) {
		super();
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookCategory = bookCategory;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", authorName=" + authorName + ", bookCategory=" + bookCategory + "]";
	}
}
