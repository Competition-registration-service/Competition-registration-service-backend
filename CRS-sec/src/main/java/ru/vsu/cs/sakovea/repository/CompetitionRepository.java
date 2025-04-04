package ru.vsu.cs.sakovea.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.sakovea.models.Competition;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    Competition findByParentId(int parent_id);

    Competition findById(int id);

    Competition findByIdAndParent(int id, Competition parent);

    Page<Competition> findByEndDateBefore(Timestamp currentDate, Pageable pageable);
}
