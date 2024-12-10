package az.ingress.controller;

import az.ingress.model.request.ReservationRequestModel;
import az.ingress.model.request.ReservationRequestModelByDate;
import az.ingress.model.response.ReservationResponseModel;
import az.ingress.service.ReservationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1") // Base URL for sales
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationsService reservationsService;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseModel>> getAllReservations(@RequestBody ReservationRequestModelByDate reservationRequestModelByDate) {
        return ResponseEntity.ok(reservationsService.getAllReservationsByDate(reservationRequestModelByDate));
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationResponseModel> getReservationsById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationsService.getReservationById(id));
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity updateReservation(@PathVariable Long id, @RequestBody ReservationRequestModel reservationRequestModel) {
        reservationsService.updateReservation(id, reservationRequestModel);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reservations")
    public ResponseEntity addReservation(@RequestBody ReservationRequestModel reservationRequestModel) {
        reservationsService.saveReservation(reservationRequestModel);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity deleteReservation(@PathVariable Long id) {
        reservationsService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
