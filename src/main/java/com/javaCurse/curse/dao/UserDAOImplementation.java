package com.javaCurse.curse.dao;

import com.javaCurse.curse.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class UserDAOImplementation implements UserDAO{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUsersList() {
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void register(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByCredentials(User user) {
        String query = "FROM User WHERE email = :email";
        List<User> result = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();
        if(result.isEmpty()){
            return null;
        }

        String passwordHashed = result.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if(argon2.verify(passwordHashed, user.getPassword())) return result.get(0);
        return null;
    }


}
