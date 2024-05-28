package longND.fpt.home.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import longND.fpt.home.presentation.payload.request.RatingRequest;
import longND.fpt.home.service.RatingService;

@RequestMapping("/api/rating")
@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;


    @PostMapping("/rate")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER')")
    public ResponseEntity<?> rateBook(@RequestBody RatingRequest request){
            return ratingService.rateBook(request);
    }




}
