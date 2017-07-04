package data;

public class Books {
	private String ISBN10;
	private String ISBN13;
	private String bookTitle;
	private String author;
	private String cover;
	private String publisher;
	private int pages;

	public Books(String ISBN10, String ISBN13, String bookTitle, String author, String cover, String publisher, int pages) {
		this.ISBN10 = ISBN10;
		this.ISBN13 = ISBN13;
		this.bookTitle = bookTitle;
		this.author = author;
		this.cover = cover;
		this.publisher = publisher;
		this.pages = pages;
	}

	public String getISBN10() {
		return ISBN10;
	}

	public void setISBN10(String ISBN10) {
		this.ISBN10 = ISBN10;
	}

	public String getISBN13() {
		return ISBN13;
	}

	public void setISBN13(String ISBN13) {
		this.ISBN13 = ISBN13;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
