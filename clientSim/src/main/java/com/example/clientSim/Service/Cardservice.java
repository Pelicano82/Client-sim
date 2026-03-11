package com.example.clientSim.Service;

import com.example.clientSim.Repos.Cardrepo;
import com.example.clientSim.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Cardservice {

    @Autowired
    private Cardrepo cardrepo;

    public Card saveCard(Card card) {
        return cardrepo.save(card);
    }

    public Optional<Card> getCardByNumber(String cardNumber) {
        return cardrepo.findById(cardNumber);
    }

    public List<Card> getAllCards() {
        return cardrepo.findAll();
    }

    public void deleteCard(String cardNumber) {
        cardrepo.deleteById(cardNumber);
    }

    public boolean cardExists(String cardNumber) {
        return cardrepo.existsById(cardNumber);
    }
}
