package in.mockito.test_doubles.fake;

public class BookService {

	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRespository) {

		this.bookRepository= bookRepository;
		
	}
	
	public void addBook(Book book) {
	
		bookRepository.save(book);
	}
	
	public int findNumberOfBooks() {
		
	return bookRepository.findAll().size();
	}
	

}
