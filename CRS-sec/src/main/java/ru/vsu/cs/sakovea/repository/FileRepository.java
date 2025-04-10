package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sakovea.models.File;

public interface FileRepository  extends JpaRepository<File,Integer> {
}
