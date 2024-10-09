package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sakovea.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(int id);
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.surename = :surname AND u.thirdname = :patronymic")
    User findByFullName(@Param("name") String name, @Param("surname") String surname, @Param("patronymic") String patronymic);
}
