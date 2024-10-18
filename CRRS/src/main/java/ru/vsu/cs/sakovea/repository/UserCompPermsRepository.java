package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sakovea.models.UserCompPerm;

@Repository
public interface UserCompPermsRepository extends JpaRepository<UserCompPerm, Integer> {
}
