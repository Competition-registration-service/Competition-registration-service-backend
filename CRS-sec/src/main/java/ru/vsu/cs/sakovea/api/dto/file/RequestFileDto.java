package ru.vsu.cs.sakovea.api.dto.file;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RequestFileDto {

    private String storageFileId;

}
