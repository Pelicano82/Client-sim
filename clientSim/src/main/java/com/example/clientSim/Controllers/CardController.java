package com.example.clientSim.Controllers;


import com.example.clientSim.entity.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController

public class CardController {

    @PostMapping("/paying")
    public ResponseEntity<Card> processCard() {};


}