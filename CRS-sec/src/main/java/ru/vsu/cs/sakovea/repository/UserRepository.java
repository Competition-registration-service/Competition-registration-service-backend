package ru.vsu.cs.sakovea.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sakovea.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    User findUserByLogin(String login);

    User findUserByActiveCode(String activeCode);

    Page<User> findAll(Pageable pageable);

    User findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.surname = :surname AND u.thirdname = :patronymic")
    User findByFullName(@Param("name") String name, @Param("surname") String surname, @Param("patronymic") String patronymic);

    User findById(int userId);
}
