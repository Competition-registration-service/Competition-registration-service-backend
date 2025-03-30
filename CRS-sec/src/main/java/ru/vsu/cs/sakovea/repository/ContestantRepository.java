package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sakovea.models.Contestant;

public interface ContestantRepository extends JpaRepository<Contestant, Integer> {
}
