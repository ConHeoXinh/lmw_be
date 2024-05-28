package longND.fpt.home.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import longND.fpt.home.data.modal.Book;



@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book getBookById(Long id);

	boolean existsByTitle(String title);

	@Query(value = "SELECT * FROM book ORDER BY id DESC LIMIT 10", nativeQuery = true)
	List<Book> getTop10BookNew();

	@Query(value = "SELECT * FROM book where copies > 0 order by copies desc limit 10", nativeQuery = true)
	List<Book> getTop10BookBorrowed();

	@Query(value = "SELECT * From book where book.title Like %:query%", nativeQuery = true)
	List<Book> listSearchBookByTitle(@Param("query") String query);

	@Query(value = "SELECT *  FROM book where LOWER(book.title) Like %:title%", countQuery = "SELECT COUNT(id) FROM book where LOWER(book.title) Like %:title%", nativeQuery = true)
	Page<Book> searchBookByTitle(@Param("title") String title, PageRequest pageRequest);

	@Query(value = "SELECT book.* FROM book\r\n" + "LEFT JOIN book_department ON book.id = book_id\r\n"
			+ "LEFT JOIN departmment ON department_id = departmment.id\r\n"
			+ "WHERE departmment.id = :departmentId LIMIT 6", nativeQuery = true)
	List<Book> relatedBook(@Param("departmentId") Long departmentId);

	@Query(value = "SELECT book.* FROM book\r\n" + "LEFT JOIN book_department bd ON book.id = bd.book_id\r\n"
			+ "LEFT JOIN departmment ON bd.department_id = departmment.id\r\n"
			+ "LEFT JOIN book_author ba ON book.id = ba.book_id\r\n"
			+ "LEFT JOIN author ON ba.author_id = author.id\r\n"
			+ "LEFT JOIN publisher ON book.publisher_id = publisher.id\r\n" + "WHERE (book.price >0)\r\n"
			+ "AND (book.copies_available >= 0)\r\n" + "AND (:bookTitle IS NULL OR book.title LIKE %:bookTitle%)"
			+ "AND (:authorName IS NULL OR author.name LIKE %:authorName%)\r\n"
			+ "AND (:departName IS NULL OR departmment.name LIKE %:departName%)\r\n"
			+ "AND (:publisherName IS NULL OR publisher.name LIKE %:publisherName%)\r\n"
			+ "AND (:langStr IS NULL OR language LIKE %:langStr%)"
			+ "GROUP BY book.id", countQuery = "SELECT COUNT(book.id) FROM book\r\n"
					+ "LEFT JOIN book_department bd ON book.id = bd.book_id\r\n"
					+ "LEFT JOIN departmment ON bd.department_id = departmment.id\r\n"
					+ "LEFT JOIN book_author ba ON book.id = ba.book_id\r\n"
					+ "LEFT JOIN author ON ba.author_id = author.id\r\n"
					+ "LEFT JOIN publisher ON book.publisher_id = publisher.id\r\n" + "WHERE (book.price >0)\r\n"
					+ "AND (book.copies_available >= 0)\r\n" + "AND (:bookTitle IS NULL OR book.title LIKE %:bookTitle%)"
					+ "AND (:authorName IS NULL OR author.name LIKE %:authorName%)\r\n"
					+ "AND (:departName IS NULL OR departmment.name LIKE %:departName%)\r\n"
					+ "AND (:publisherName IS NULL OR publisher.name LIKE %:publisherName%)\r\n"
					+ "AND (:langStr IS NULL OR language LIKE %:langStr%)"
					+ "GROUP BY book.id", nativeQuery = true)
	Page<Book> listSearchFilter(@Param("authorName") String authorName, @Param("departName") String departName,
			@Param("publisherName") String publisherName, @Param("bookTitle") String bookTitle, @Param("langStr") String langStr, Pageable pageable);

	@Query(value = "SELECT book.* FROM organica.book LEFT JOIN book_author ba ON book.id =ba.book_id LEFT JOIN author ON ba.author_id = author.id WHERE author.id = :idAuthor", countQuery = "SELECT COUNT(book.id) FROM organica.book LEFT JOIN book_author ba ON book.id =ba.book_id LEFT JOIN author ON ba.author_id = author.id WHERE author.id = :idAuthor", nativeQuery = true)
	Page<Book> listBookByAuthorId(@Param("idAuthor") Long idAuthor, Pageable pageable);
	
	@Query(value = "SELECT * FROM organica.book where publisher_id = :idPublisher", countQuery = "SELECT COUNT(id) FROM organica.book where publisher_id =:idPublisher", nativeQuery = true)
	Page<Book> listBookByPublisherId(@Param("idPublisher") Long idPublisher, Pageable pageable);

	@Query(value = "SELECT book.* FROM organica.book LEFT JOIN book_department bd ON book.id =bd.book_id LEFT JOIN departmment ON bd.department_id = departmment.id WHERE departmment.id = :idDepartment", countQuery = "SELECT COUNT(book.id) FROM organica.book LEFT JOIN book_department bd ON book.id =bd.book_id LEFT JOIN departmment ON bd.department_id = departmment.id WHERE departmment.id = :idDepartment", nativeQuery = true)
	Page<Book> listBookByDepartmentId(@Param("idDepartment") Long idDepartment, Pageable pageable);

	@Query(value = "SELECT * FROM book", countQuery = "SELECT COUNT(id) FROM book", nativeQuery = true)
	Page<Book> getAllBook(Pageable pageable);

	@Query(value = "SELECT book.*, favorite.is_favorite FROM organica.book \n"
			+ "LEFT JOIN book_author ba ON book.id = ba.book_id\n"
			+ "LEFT JOIN book_department bd ON book.id = bd.book_id\n"
			+ "LEFT JOIN publisher ON book.publisher_id = publisher.id\n"
			+ "LEFT JOIN favorite ON book.id = favorite.book_id\n"
			+ "WHERE favorite.user_id = :userId AND book.id= :bookId", nativeQuery = true)
	Book getBookByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
	
	@Query(value = "SELECT COUNT(id) FROM book", nativeQuery = true)
	int totalBook();
}
