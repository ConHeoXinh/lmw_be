package longND.fpt.home.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import longND.fpt.home.data.dto.VoucherDto;
import longND.fpt.home.data.modal.Order;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.modal.Voucher;
import longND.fpt.home.data.repository.OrderRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.data.repository.VoucherRepository;
import longND.fpt.home.exception.AuthException;
import longND.fpt.home.exception.NotFoundException;
import longND.fpt.home.exception.SaveDataException;
import longND.fpt.home.presentation.payload.request.VoucherRequest;
import longND.fpt.home.presentation.payload.response.ApiResponse;
import longND.fpt.home.presentation.payload.response.ObjectResponse;
import longND.fpt.home.service.VoucherService;
import longND.fpt.home.util.SecurityUtils;
import longND.fpt.home.util.Util;

@Service
public class VoucherServiceImpl implements VoucherService {

	@Autowired
	private VoucherRepository voucherRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public ResponseEntity<ObjectResponse> getAllVoucher() {
		List<Voucher> vouchers = voucherRepository.getVoucherByStatus(1);
		List<VoucherDto> dtoList = new ArrayList<>();

		for (Voucher v : vouchers) {
			VoucherDto dto = VoucherDto.builder().idVoucher(v.getId()).nameVoucher(v.getCode())
					.description(v.getDescription()).dueDate(v.getDueDay()).percent(v.getPercent())
					.status(v.getStatus()).build();
			dtoList.add(dto);
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("danh sách voucher", new HashMap<>() {
			{
				put("vouchers", dtoList);
			}
		}));
	}

	@Override
	public ResponseEntity<ObjectResponse> getOneVoucher(Long id) {
		Voucher voucher = voucherRepository.getVoucherById(id);

		VoucherDto dto = VoucherDto.builder().idVoucher(voucher.getId()).nameVoucher(voucher.getCode())
				.description(voucher.getDescription()).dueDate(voucher.getDueDay()).percent(voucher.getPercent())
				.status(voucher.getStatus()).build();

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ObjectResponse("tìm kiếm voucher theo Id", new HashMap<>() {
					{
						put("voucher", dto);
					}
				}));
	}

	@Override
	public ResponseEntity<ApiResponse> insertVoucher(List<VoucherRequest> voucherRequest) {
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		voucherRequest.forEach(vp -> {

			if (voucherRepository.existsByCode(vp.getName())) {
				throw new SaveDataException("trùng mã giảm giá");
			} else {
				Voucher v = Voucher.builder().code(vp.getName()).description(vp.getDescription())
						.percent(vp.getPercent()).startDay(LocalDate.now()).dueDay(vp.getDueDate()).employee(user)
						.user(user).status(1).build();
				voucherRepository.save(v);
			}
		});

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Create voucher thanh cong", 200));
	}

	@Override
	public ResponseEntity<ObjectResponse> updateVoucher(Long id, VoucherRequest voucherRequest, Long userID) {
		Voucher voucher = voucherRepository.getVoucherById(id);
		User user = userRepository.findUserById(userID);

		Voucher v = Voucher.builder().code(voucherRequest.getName()).description(voucherRequest.getDescription())
				.dueDay(voucherRequest.getDueDate()).user(user).status(voucher.getStatus()).build();

		Voucher vUpdate = voucherRepository.save(v);

		VoucherDto dto = VoucherDto.builder().nameVoucher(vUpdate.getCode()).description(vUpdate.getDescription())
				.dueDate(vUpdate.getDueDay()).percent(vUpdate.getPercent()).userId(vUpdate.getUser().getId())
				.status(vUpdate.getStatus()).build();

		return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("Sửa voucher thành công", new HashMap<>() {
			{
				put("voucher", dto);
			}
		}));
	}

	@Override
	public ResponseEntity<ApiResponse> updateStatusVoucher(Long id, int status) {
		Voucher voucher = voucherRepository.getVoucherById(id);
		voucher.setStatus(0);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse("Thay đổi trang thái mã giảm giá thành công", 200));
	}

	@Override
	public List<VoucherDto> getAllVoucherByUserID() {
		if (SecurityUtils.checkAuth().equals("anonymousUser")) {
			throw new AuthException("Hãy đăng nhập để lấy mã giảm giá");
		}
		User user = userRepository.findUserById(SecurityUtils.getPrincipal().getId());

		List<Voucher> vouchers = voucherRepository.getVoucherByUser_IdAndStatus(user.getId(), 1);

		if (Objects.isNull(vouchers)) {
			throw new NotFoundException("Không tìm thấy mã giảm giá");
		} else {

			List<VoucherDto> dtoList = new ArrayList<>();

			vouchers.forEach(v -> {
				VoucherDto dto = VoucherDto.builder().idVoucher(v.getId()).nameVoucher(v.getCode())
						.description(v.getDescription()).dueDate(v.getDueDay()).percent(v.getPercent())
						.status(v.getStatus()).build();

				LocalDate dayNow = LocalDate.now();
				long daysDifference = ChronoUnit.DAYS.between(v.getStartDay(), dayNow);
				if (daysDifference < v.getDueDay()) {
					dtoList.add(dto);
				}

			});
			return dtoList;
		}
	}

	@Override
	public void addVoucherByUserID(Long userID) {
		User user = userRepository.findUserById(userID);
		List<Order> listOrder = orderRepository.findAllByUser(user);

		LocalDate currentDate = LocalDate.now();

		// Ngày cách đây 2 tháng
		LocalDate twoMonthsAgo = currentDate.minusMonths(2);

		// calculator percent
		int countOrderItem = 0;
		for (Order order : listOrder) {
			if (order.getCreatedAt().toLocalDate().isAfter(twoMonthsAgo)) {
				countOrderItem += order.getTotalItem();
			}
		}
//		System.err.println("countOrderItem" + countOrderItem);
//		double percentDouble = 0.5 * countOrderItem;

		int percent = 0;
		if (countOrderItem >= 15 && countOrderItem < 20) {
			percent = 15;
		} else if (countOrderItem >= 20 && countOrderItem < 25) {
			percent = 20;
		} else if (countOrderItem >= 25 && countOrderItem < 30) {
			percent = 25;
		} else if (countOrderItem >= 30 && countOrderItem < 35) {
			percent = 30;
		} else if (countOrderItem >= 35 && countOrderItem < 40) {
			percent = 35;
		} else if (countOrderItem >= 40 && countOrderItem < 45) {
			percent = 40;
		} else if (countOrderItem >= 45) {
			percent = 50;
		}

		// check order exit
		Voucher voucher = voucherRepository.getVoucherByUser_Id(userID);

		if (ObjectUtils.isEmpty(voucher)) {
			voucher = new Voucher();
			if (percent != 0) {
				// add voucher
				voucher.setCode(Util.generateRandomString(6));
				voucher.setDescription("voucher tạo bởi order của user");
				voucher.setDueDay(10);
				voucher.setPercent(percent);
				voucher.setStartDay(LocalDate.now());
				voucher.setStatus(1);
				voucher.setUser(user);

				Voucher vUpdate = voucherRepository.save(voucher);
			}
		} else {
			LocalDate starDay = voucher.getStartDay();
			long daysBetween = ChronoUnit.DAYS.between(starDay, currentDate);
			if (daysBetween > 30) {
				voucher.setStatus(1);
				voucher.setStartDay(LocalDate.now());
				voucherRepository.save(voucher);
			}
		}

//		VoucherDto dto = VoucherDto.builder().nameVoucher(vUpdate.getCode()).description(vUpdate.getDescription())
//				.dueDate(vUpdate.getDueDay()).percent(vUpdate.getPercent()).userId(vUpdate.getUser().getId())
//				.status(vUpdate.getStatus()).build();

	}

}
