package longND.fpt.home.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.presentation.payload.request.SearchFilterRequest;
import longND.fpt.home.presentation.payload.request.SearchRequest;
import longND.fpt.home.service.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@PostMapping("")
	public ResponseEntity<?> getSearchByName(@RequestBody SearchRequest nameSearch,
			@RequestParam("index-page") int indexPage) {
		return searchService.searchByTitle(nameSearch, indexPage);
	}

	@PostMapping("/filter-search")
	public ResponseEntity<?> getSearchFilter(@RequestBody SearchFilterRequest SearchFilterRequest,
			@RequestParam("index-page") int indexPage) {
		return searchService.searchFilter(SearchFilterRequest, indexPage);
	}

	@PostMapping("/fill-search")
	public ResponseEntity<?> getSearch(@RequestBody SearchRequest nameSearch) {
		return searchService.getTextSearch(nameSearch);
	}

	@GetMapping("/menu")
	public ResponseEntity<?> getMenu() {
		return searchService.getFilterMenu();
	}
}
