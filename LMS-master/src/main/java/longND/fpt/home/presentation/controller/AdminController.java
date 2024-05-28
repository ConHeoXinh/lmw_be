package longND.fpt.home.presentation.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.presentation.payload.request.FilterDateRequest;
import longND.fpt.home.service.OrderItemService;
import longND.fpt.home.service.OrderService;
import longND.fpt.home.service.UserService;
import longND.fpt.home.util.Util;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;

	@GetMapping("/ranking-book")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getRankingBook() {
		FilterDateRequest dto = new FilterDateRequest();
		String dayNow = Util.formatLocalDate(LocalDate.now());
		String firstDay = Util.formatLocalDate(LocalDate.now().withDayOfMonth(1));
		dto.setCheckoutDate(firstDay);
		dto.setReturnDate(dayNow);
		return orderItemService.Top10BookFilter(dto);
	}

	@PostMapping("/ranking-book/filter")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getRankingBook(@RequestBody FilterDateRequest filterDateRequest) {
		return orderItemService.Top10BookFilter(filterDateRequest);
	}

	@GetMapping("/dashboard")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getDashBoard() {
		FilterDateRequest dto = new FilterDateRequest();
		String dayNow = Util.formatLocalDate(LocalDate.now());
		String firstDay = String.valueOf(LocalDate.now().getYear() + "-01-01");
		dto.setCheckoutDate(firstDay);
		dto.setReturnDate(dayNow);
		return orderService.getDashboard(dto);
	}

	@PostMapping("/dashboard/filter")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getDashBoard(@RequestBody FilterDateRequest filterDateRequest) {
		return orderService.getDashboard(filterDateRequest);
	}

	@GetMapping("/list")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> listEmployees(@RequestParam("index-page") int indexPage) {
		return userService.getAllEmployees(indexPage);
	}

	@PutMapping("/change-status/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateStatus(@PathVariable Long id) {
		return userService.changeStatus(id);
	}
	
	@GetMapping("/search")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> searchEmployeeByUsername(@RequestParam("username") String username, @RequestParam("index-page") int indexPage) {
		return userService.searchEmployeeByUseranme(indexPage, username);
	}
}
