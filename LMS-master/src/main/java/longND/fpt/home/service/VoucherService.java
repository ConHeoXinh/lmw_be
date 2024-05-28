package longND.fpt.home.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.dto.VoucherDto;
import longND.fpt.home.presentation.payload.request.VoucherRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;

@Service
public interface VoucherService {

	public ResponseEntity<ObjectResponse> getAllVoucher();

	public ResponseEntity<ObjectResponse> getOneVoucher(Long id);

	public ResponseEntity<ApiResponse> insertVoucher(List<VoucherRequest> voucherRequest);

	public ResponseEntity<ObjectResponse> updateVoucher(Long id, VoucherRequest voucherRequest, Long userID);

	public ResponseEntity<ApiResponse> updateStatusVoucher(Long id, int status);

//	public ResponseEntity<JwtResponse> getAllVoucherByUserID();

	public List<VoucherDto> getAllVoucherByUserID();

	public void addVoucherByUserID(Long userID);
}
