package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sakovea.models.FieldValue;

public interface FieldValueRepository extends JpaRepository<FieldValue, Integer> {
}
