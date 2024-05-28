package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;

public interface BookCaseLineService {

	public ResponseEntity<?> getBookCaseLineOfBook(Long id);
}
