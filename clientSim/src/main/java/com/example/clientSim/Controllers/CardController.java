package com.example.clientSim.Controllers;

import com.example.clientSim.Service.Cardservice;
import com.example.clientSim.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private Cardservice cardservice;

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardservice.saveCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<Card> getCard(@PathVariable String cardNumber) {
        Optional<Card> card = cardservice.getCardByNumber(cardNumber);
        return card.map(ResponseEntity::ok)
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardservice.getAllCards();
        return ResponseEntity.ok(cards);
    }

    @PutMapping("/{cardNumber}")
    public ResponseEntity<Card> updateCard(@PathVariable String cardNumber, @RequestBody Card cardDetails) {
        Optional<Card> card = cardservice.getCardByNumber(cardNumber);
        if (card.isPresent()) {
            Card existingCard = card.get();
            existingCard.setCardHolder(cardDetails.getCardHolder());
            existingCard.setSecurityCode(cardDetails.getSecurityCode());
            existingCard.setExpiryDate(cardDetails.getExpiryDate());
            Card updatedCard = cardservice.saveCard(existingCard);
            return ResponseEntity.ok(updatedCard);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<Void> deleteCard(@PathVariable String cardNumber) {
        if (cardservice.cardExists(cardNumber)) {
            cardservice.deleteCard(cardNumber);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/paying")
    public ResponseEntity<Card> processCard(@RequestBody Card card) {
        Card savedCard = cardservice.saveCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.OK);
    }

}