package az.ingress.service;

import az.ingress.model.request.ReservationRequestModel;
import az.ingress.model.request.ReservationRequestModelByDate;
import az.ingress.model.response.ReservationResponseModel;

import java.time.LocalDate;
import java.util.List;

public interface ReservationsService {
    List<ReservationResponseModel> getAllReservationsByDate(ReservationRequestModelByDate reservationRequestModelByDate);
    ReservationResponseModel getReservationById(Long id);
    void saveReservation(ReservationRequestModel reservation);
    void updateReservation(Long id,ReservationRequestModel reservationData);
    void deleteReservation(Long id);
}
