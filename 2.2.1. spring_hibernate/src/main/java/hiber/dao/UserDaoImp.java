package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user, Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        session.save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User").getResultList();
    }

    @Override
    public User getUserByCarModelAndSeries(String model, int series) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User users WHERE users.car.model = :model AND users.car.series = :series")
                .setParameter("model", model).setParameter("series", series)
                .setMaxResults(1)
                .getSingleResult();
    }
}
