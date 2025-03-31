package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.Contestant;
import ru.vsu.cs.sakovea.models.User;

public interface ContestantRepository extends JpaRepository<Contestant, Integer> {
    Contestant findByUser(User user);

    Contestant findByUserAndCompetition(User user, Competition competition);
}
