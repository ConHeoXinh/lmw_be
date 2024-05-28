package longND.fpt.home.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import longND.fpt.home.data.modal.OrderItem;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.repository.OrderItemRepository;
import longND.fpt.home.data.repository.UserRepository;
import longND.fpt.home.util.JavaMail;

@Component
public class ReturnBookEventListener {

	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private JavaMail javaMail;
	@Autowired
	private UserRepository userRepository;

	private final long DAY_NEED_RETURN = 3;

	private final String SUBJECT_EMAIL = "LMS - Hạn trả sách sắp tới!!!!!";
//	private final String BODY_EMAIL = "Please return book like return date your return date is: ";

	@Scheduled(fixedRate = 10800000) // 5 milliseconds can change to hours
	public void checkReturnDate() {
		LocalDateTime currentDate = LocalDateTime.now();
		List<OrderItem> orders = orderItemRepository.findAll();
		for (OrderItem o : orders) {
			if (o.isReturn() == false && !Objects.isNull(o.getEmployee()) && o.isSendDeadline() == false) {
				long daysDifference = ChronoUnit.DAYS.between(currentDate, o.getReturnDate());
				if (daysDifference <= DAY_NEED_RETURN) {
					User user = o.getUser();
					String text = "Xin chào " + user.getFirstName() + " " + user.getLastName() + "! \n\n"
							+ "Lms gửi mail này nhằm nhắc bạn tránh trả muộn sách đã mượn. \n" + "Hiện tại cuốn sách "
							+ o.getBook().getTitle()
							+ " sắp đến hạn trả, bạn phải trả lại sách cho Lms. Mail này được gửi để nhắc bạn trước 3 ngày hết hạn trả sách.\n\n"
							+ "Lms cảm ơn bạn đã dùng dịch vụ của hệ thống. Mọi thắc mắc bạn có thể liên hệ đến : longndhe140970@fpt.edu.vn. \n";
					javaMail.sentEmail(user.getEmail(), SUBJECT_EMAIL, text);
					o.setSendDeadline(true);
					orderItemRepository.save(o);
//					System.out.println("Send notification one day before return date.");
				}
			}
			if (o.isReturn() == false && !Objects.isNull(o.getEmployee())) {
				long daysDifference = ChronoUnit.DAYS.between(currentDate, o.getReturnDate());
				// check return book late
				if (daysDifference < 0) {
					double price = o.getBook().getPrice();
					double resultPrice = price * (-(daysDifference));
					o.setPrice(resultPrice);
					o.setPayed(false);
					orderItemRepository.save(o);
				}
			}
		}

		// send mail when register
		List<User> users = userRepository.findAll();
		for (User user : users) {
			LocalDateTime ceateUser = user.getCreateAt();

			long daysDifference = ChronoUnit.DAYS.between(ceateUser, currentDate);
			if (daysDifference >= 0 && daysDifference <= 1 && user.isSentEmail() == false) {
				String text = "Chào mừng bạn đã tham gia làm thành viên của Lms! \n"
						+ "Với việc trở thành thành viên của Lms, bạn sẽ được hưởng một số dịch vụ sau:\n"
						+ "\n - Có thể tạo tối đa 3 đơn mượn sách trong ngày"
						+ "\n - Có thể gia hạn sách mượn tối đa 3 lần"
						+ "\n - Và các tính năng thuận tiện mà Lms mang đến cho bạn."
						+ "\n\nMail này là mail tự động. Vui lòng không trả lời lại mail này!";
				user.setSentEmail(true);
				userRepository.save(user);
				javaMail.sentEmail(user.getEmail(), "LMS - Chào mừng thành viên mới!!!!!", text);
			}
		}

	}

}
