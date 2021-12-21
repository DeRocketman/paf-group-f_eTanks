package model.service;

import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;

import java.util.ArrayList;
import java.util.List;

public class UserDataCreator {

   private List<User> userList = new ArrayList<User>();

   public UserDataCreator(){
      UserSettings testUserSetting = new UserSettings();
      testUserSetting.setDefaultSettings();

      UserStatistic testUserStatistic = new UserStatistic();
      testUserStatistic.setDefaultStatistic();

      User testUser1 = new User(10000, "username", "KANALARBEITER",
              "../img/images/default-user-image.png", "passwort", testUserSetting, testUserStatistic);
      User testUser = new User(10001, "Maxi-Line", "Wocketwan",
              "../img/images/default-user-image.png", "passwort", testUserSetting, testUserStatistic);

      userList.add(testUser);
      userList.add(testUser1);
   }


   public List<User> getUserlist() {
      return userList;
   }


}

