package com.lms.Service;

import com.lms.Dto.CardDTO;
import com.lms.Dto.UserDTO;

public interface CardServiceImpl {
    CardDTO createCard(UserDTO userDTO);
    void deactivate(long userId);
}
