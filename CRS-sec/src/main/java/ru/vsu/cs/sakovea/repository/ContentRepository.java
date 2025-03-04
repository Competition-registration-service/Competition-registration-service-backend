package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.Content;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    Optional<Content> findById(Integer id);

    List<Content> findByCompetitionId(Integer competitionId);

    Content findByIdAndCompetition(Integer id, Competition competition);
}
