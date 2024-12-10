package az.ingress.dao.repository;

import az.ingress.dao.entity.ReservationsEntity;
import az.ingress.utils.enums.StatusEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationsRepository extends CrudRepository<ReservationsEntity,Long> {
    Optional<ReservationsEntity> findByIdAndStatus(Long id, StatusEnum status);
    List<ReservationsEntity> findByDateBetweenAndStatus(LocalDate dateStart, LocalDate dateEnd, StatusEnum status);
}
