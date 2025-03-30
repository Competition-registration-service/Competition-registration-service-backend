package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sakovea.models.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findTeamByAccessCode(String teamCode);
}
