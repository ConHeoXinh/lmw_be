package longND.fpt.home.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.service.VoucherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/voucher")
public class VoucherController {

	@Autowired
	private VoucherService voucherService;

//	@GetMapping("")
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//	public ResponseEntity<?> getAllVoucher() {
//		return voucherService.getAllVoucher();
//	}

	@GetMapping("/get-one")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE')")
	public ResponseEntity<?> getOneVoucherByID(@RequestParam("id") Long id) {
		return voucherService.getOneVoucher(id);
	}

//	@PostMapping("/insert")
//	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
//	public ResponseEntity<?> insertVoucher(@Valid @RequestBody List<VoucherRequest> voucherRequest) {
//		return voucherService.insertVoucher(voucherRequest);
//	}

//	@GetMapping("/list-voucher-user")
//	@PreAuthorize("hasRole('ROLE_USER')")
//	public ResponseEntity<?> getAllVoucherByUserID() {
//		return voucherService.getAllVoucherByUserID();
//	}

//	@GetMapping("/add-vocher-user/{userId}")
//	@PreAuthorize("hasRole('ROLE_USER')")
//	public ResponseEntity<?> addVoucherByUserID(@PathVariable Long userId) {
//		return voucherService.addVoucherByUserID(userId);
//	}

}
