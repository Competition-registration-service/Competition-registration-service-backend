package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sakovea.models.Field;
import ru.vsu.cs.sakovea.models.RefValue;

public interface FieldRepository extends JpaRepository<Field, Integer> {
}
