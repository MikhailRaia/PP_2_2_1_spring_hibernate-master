package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        //Пользователи по умолчанию
//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        //Создание новых пользователей
        User user1 = new User("Vlad", "Petrov", "vl_p@mail.ru");
        User user2 = new User("Oleg", "Smirnov", "os_p@mail.ru");
        User user3 = new User("Vitya", "Popov", "vp_p@mail.ru");
        User user4 = new User("Kostya", "Sidorov", "ks_p@mail.ru");

        //Создание новых автомобилей
        Car car1 = new Car("TESLA", 25);
        Car car2 = new Car("BMW", 35);
        Car car3 = new Car("MB", 40);
        Car car4 = new Car("FORD", 22);

        //Присвоение пользователю автомобиля
        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        //Добавление связанной таблицы пользователь-автомобиль в БД
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        //Вывод пользователей с автомобилем
        System.out.println(userService.listUsers());




        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        List<Car> cars = userService.usersCars();
        for (Car car : cars) {
            System.out.println("Id = " + car.getId());
            System.out.println("Model = " + car.getModel());
            System.out.println("Series = " + car.getSeries());
            System.out.println();
        }

        //Извлечение данных о пользователе ver1
        System.out.println("Id = " + userService.getUserByCar("MB", 40).get(0).getId());
        System.out.println("First Name = " + userService.getUserByCar("MB", 40).get(0).getFirstName());
        System.out.println("Last Name = " + userService.getUserByCar("MB", 40).get(0).getLastName());
        System.out.println("Email = " + userService.getUserByCar("MB", 40).get(0).getEmail());

        //Извлечение данных о пользователе ver2
        User u2 = userService.getUserByCar("MB", 40).get(0);
        System.out.println("Id = " + u2.getId());
        System.out.println("First Name = " + u2.getFirstName());
        System.out.println("Last Name = " + u2.getLastName());
        System.out.println("Email = " + u2.getEmail());
        System.out.println();

        context.close();
    }
}
