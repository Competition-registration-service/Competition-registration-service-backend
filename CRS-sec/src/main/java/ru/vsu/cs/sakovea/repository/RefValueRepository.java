package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sakovea.models.RefValue;
import ru.vsu.cs.sakovea.models.UserCompPerm;

public interface RefValueRepository  extends JpaRepository<RefValue, Integer> {

    RefValue findRefValueByValueCid(String shortValue);
}
