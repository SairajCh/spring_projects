package in.mockito.test_doubles.fake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FakeTest {

	@Test
	public  void testFake() {
		
		BookRepository bookRepository = new FakeBookRepository();
		BookService bookService = new BookService(bookRepository);
		
		bookService.addBook(new Book("1","Mockito in action",100,LocalDate.now()));
		bookService.addBook(new Book("2","Junit5 in action",150,LocalDate.now()));
		assertEquals(1,2);
	
		
	}
	
	
}
