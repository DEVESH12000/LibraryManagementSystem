package com.lms.Service;


import com.lms.Dto.CardDTO;
import com.lms.Dto.UserDTO;
import com.lms.Entity.Card;
import com.lms.Entity.User;
import com.lms.Exception.ResourceNotFoundException;
import com.lms.Repository.CardRepository;
import com.lms.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpl {

    Logger logger= LoggerFactory.getLogger(UserService.class);


    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;


        @Autowired
        private ModelMapper modelMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User userEntity = modelMapper.map(userDTO, User.class);
        if (userDTO.getCard() != null) {
            Card cardEntity = modelMapper.map(userDTO.getCard(), Card.class);
            Card savedCard = cardRepository.save(cardEntity);
            userEntity.setCard(savedCard);
        }
        User savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, UserDTO.class);
    }


    @Override
    public UserDTO getUserById(long id) {
        User user =userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return modelMapper.map(user, UserDTO.class);

    }


    @Override
    public UserDTO updateUser(UserDTO userDTO, long id) {
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setAge(userDTO.getAge());
        userEntity.setCountry(userDTO.getCountry());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        if (userDTO.getCard() != null) {
            CardDTO cardDTO = userDTO.getCard();
            Card cardEntity = userEntity.getCard();
            if (cardEntity == null) {
                cardEntity = new Card();
            }
          //  cardEntity.setId(cardDTO.getId());
            cardEntity.setCreatedOn(cardDTO.getCreatedOn());
            cardEntity.setUpdatedOn(cardDTO.getUpdatedOn());
            cardEntity.setCardStatus(cardDTO.getCardStatus());
            // Assuming transactions and books are also updated similarly if needed
            userEntity.setCard(cardEntity);
        }
        User save = userRepository.save(userEntity);
        return modelMapper.map(save, UserDTO.class);
    }
    public void deleteUserById(long id) {
            cardService.deactivate(id);
            userRepository.deleteCustom(id);
        }
    }
