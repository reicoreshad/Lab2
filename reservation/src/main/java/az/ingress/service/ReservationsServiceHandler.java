package az.ingress.service;

import az.ingress.dao.entity.ReservationsEntity;
import az.ingress.dao.repository.ReservationsRepository;
import az.ingress.model.request.ReservationRequestModel;
import az.ingress.model.request.ReservationRequestModelByDate;
import az.ingress.model.response.ReservationResponseModel;
import az.ingress.utils.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationsServiceHandler implements ReservationsService{
    private final ReservationsRepository reservationsRepository;
    @Override
    public List<ReservationResponseModel> getAllReservationsByDate(ReservationRequestModelByDate reservationRequestModelByDate) {
        var reservations=reservationsRepository.findByDateBetweenAndStatus(reservationRequestModelByDate.getStartDate(), reservationRequestModelByDate.getEndDate(), StatusEnum.ACTIVE);
        return reservations.stream()
                .map(this::convertToResponseModel)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponseModel getReservationById(Long id) {
        var reservation=fetchReservation(id);
        return convertToResponseModel(reservation);
    }

    @Override
    public void saveReservation(ReservationRequestModel reservation) {
        reservationsRepository.save(
                ReservationsEntity.builder()
                        .tableName(reservation.getTableName())
                        .customer(reservation.getCustomer())
                        .date(reservation.getDate())
                        .status(StatusEnum.ACTIVE)
                        .membersCount(reservation.getMembersCount())
                        .build()
        );
    }

    @Override
    public void updateReservation(Long id, ReservationRequestModel reservationData) {
        var reservation=fetchReservation(id);
        reservation.setTableName(reservationData.getTableName());
        reservation.setCustomer(reservationData.getCustomer());
        reservation.setDate(reservationData.getDate());
        reservation.setMembersCount(reservationData.getMembersCount());
        reservationsRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        var reservation=fetchReservation(id);
        reservation.setStatus(StatusEnum.INACTIVE);
        reservationsRepository.save(reservation);
    }
    private ReservationsEntity fetchReservation(Long id) {
        return reservationsRepository.findByIdAndStatus(id, StatusEnum.ACTIVE)
                .orElseThrow(() -> new EntityNotFoundException("Cashier not found with id: " + id));

    }
    private ReservationResponseModel convertToResponseModel(ReservationsEntity reservations) {
        return new ReservationResponseModel(
                reservations.getTableName(),
                reservations.getCustomer(),
                reservations.getDate(),
                reservations.getMembersCount()
        );
    }
}
