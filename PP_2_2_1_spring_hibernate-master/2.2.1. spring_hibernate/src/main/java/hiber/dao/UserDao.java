package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   List <Car> usersCars();
   List<User> getUserByCar(String model, int series);
}
