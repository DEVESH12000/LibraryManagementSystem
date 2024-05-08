package com.lms.Service;

import com.lms.Dto.UserDTO;

public interface UserServiceImpl {

    public UserDTO createUser(UserDTO userDTO);

    public  UserDTO updateUser(UserDTO userDTO ,long id);

    void deleteUserById(long id);

    UserDTO getUserById(long id);
}
