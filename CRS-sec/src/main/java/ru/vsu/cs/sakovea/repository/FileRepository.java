package ru.vsu.cs.sakovea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.sakovea.models.File;

import java.util.List;
import java.util.Optional;

public interface FileRepository  extends JpaRepository<File,Integer> {
    List<File> findByCompetitionId(Integer competitionId);

    File findByStorageFileId(String storageFileId);

    Optional<Object> findByStorageFileIdAndCompetitionId(String storageFileId, Integer competitionId);
}
