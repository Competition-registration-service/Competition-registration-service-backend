package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sakovea.models.Competition;

import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    Competition findByParentId(int parent_id);

    Competition findById(int id);

    Competition findByIdAndParent(int id, Competition parent);

}
