package longND.fpt.home.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.presentation.payload.request.SearchFilterRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.presentation.payload.response.ObjectResponse;

@Service
public interface SearchService {

	public ResponseEntity<ObjectResponse> searchByTitle(SearchRequest searchRequest, int indexPage);
	
	public ResponseEntity<ObjectResponse> searchFilter(SearchFilterRequest searchFilterRequest, int indexPage);

	public ResponseEntity<ObjectResponse> getTextSearch(SearchRequest searchRequest);

	public ResponseEntity<ObjectResponse> getFilterMenu();
}
