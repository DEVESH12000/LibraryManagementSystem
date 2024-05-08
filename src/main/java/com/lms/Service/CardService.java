package com.lms.Service;

import com.lms.Dto.CardDTO;
import com.lms.Dto.UserDTO;
import com.lms.Entity.Card;
import com.lms.Entity.CardStatus;
import com.lms.Entity.User;
import com.lms.Repository.CardRepository;
import com.lms.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService implements CardServiceImpl {

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;

    private  final UserRepository userRepository;
    private final EntityManager entityManager;

    public CardService(CardRepository cardRepository, ModelMapper modelMapper, UserRepository userRepository, EntityManager entityManager) {
        this.cardRepository = cardRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Autowired

    public CardDTO createCard(UserDTO userDTO) {
        User userEntity = modelMapper.map(userDTO, User.class);
        Card cardEntity = new Card();
        userEntity.setCard(cardEntity);
        cardEntity.setUser(userEntity);
        userRepository.save(userEntity);
        return modelMapper.map(cardEntity, CardDTO.class);
    }

    public void deactivate(long user_id) {
        cardRepository.deactivateCard(user_id, CardStatus.DEACTIVATED.toString());
    }
}
