package az.ingress.dao.entity;

import az.ingress.utils.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "reservations")
@Builder
public class ReservationsEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String tableName;

    private String customer;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    @Column(nullable = false)
    private Long membersCount;

}
