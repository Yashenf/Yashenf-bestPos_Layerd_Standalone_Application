package com.yashen.bestPos.bo.custom.impl;

import com.yashen.bestPos.bo.custom.UserBO;
import com.yashen.bestPos.bo.util.Converter;
import com.yashen.bestPos.dao.custom.UserDAO;
import com.yashen.bestPos.dao.util.DaoFactory;
import com.yashen.bestPos.dao.util.DaoTypes;
import com.yashen.bestPos.dto.UserDTO;
import com.yashen.bestPos.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = DaoFactory.getInstance().getDao(DaoTypes.USER);
    Converter converter = new Converter();
    @Override
    public boolean saveUser(UserDTO userDTO) throws RuntimeException, SQLException, ClassNotFoundException {
        return userDAO.save(converter.toUser(userDTO));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws RuntimeException, SQLException, ClassNotFoundException {
        return userDAO.update(converter.toUser(userDTO));
    }

    @Override
    public boolean deleteUser(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws RuntimeException, SQLException, ClassNotFoundException {
        ArrayList<User> all = userDAO.getAll();
        ArrayList<UserDTO> dtoList = new ArrayList<>();
        for (User user:all){
            dtoList.add(converter.fromUser(user));
        }
        return dtoList;
    }

    @Override
    public UserDTO getUserFromId(String id) throws RuntimeException, SQLException, ClassNotFoundException {
        return converter.fromUser(userDAO.getByPrimaryKey(id));
    }
}
