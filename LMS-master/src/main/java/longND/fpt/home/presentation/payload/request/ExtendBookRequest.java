package longND.fpt.home.presentation.payload.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExtendBookRequest {
    private Long orderId;
    private LocalDateTime returnDate;
}
