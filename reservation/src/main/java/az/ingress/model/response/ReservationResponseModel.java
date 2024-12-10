package az.ingress.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseModel {
    private String tableName;
    private String customer;
    private LocalDate date;
    private Long membersCount;
}
