package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

/**
 * Created by Станислав on 14.05.2017.
 */
public class MainModel implements Model{
    private UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    private List<User> getAllUsers(){
        modelData.setDisplayDeletedUserList(false);
        return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1,100));
    }

    @Override
    public void loadUsers() {
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllUsers());
    }

    @Override
    public void loadDeletedUsers(){
        modelData.setDisplayDeletedUserList(true);
        modelData.setUsers(userService.getAllDeletedUsers());
    }

    @Override
    public void loadUserById(long id) {
        modelData.setDisplayDeletedUserList(false);
        modelData.setActiveUser(userService.getUsersById(id));
    }

    @Override
    public void deleteUserById(long id) {
        userService.deleteUser(id);
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllUsers());
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name,id,level);
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllUsers());
    }
}
