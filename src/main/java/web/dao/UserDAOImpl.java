package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserDAOImpl implements UserDAO{
    private List<User> users = new ArrayList<>();
    {
        users.add(new User("Max","Spar","qwe@bk.ru"));
        users.add(new User("Ivan","Petrov","one@pntr.io"));
        users.add(new User("Mariya","Ivanova","brv@gmail.com"));
        users.add(new User("Anna","Elina","am@yandex.ru"));
        users.add(new User("Jane","Kryshkina","5965@rambler.ru"));
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        entityManager.remove(getUser(id));
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
