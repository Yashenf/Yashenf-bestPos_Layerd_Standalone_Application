package com.yashen.bestPos.bo.custom;

import com.yashen.bestPos.bo.SuperBO;
import com.yashen.bestPos.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO) throws RuntimeException, SQLException,ClassNotFoundException;
    boolean updateUser(UserDTO userDTO) throws RuntimeException, SQLException,ClassNotFoundException;
    boolean deleteUser(String id) throws RuntimeException, SQLException,ClassNotFoundException;
    ArrayList<UserDTO> getAllUsers() throws RuntimeException, SQLException,ClassNotFoundException;
    UserDTO getUserFromId(String id) throws RuntimeException, SQLException,ClassNotFoundException;
}
