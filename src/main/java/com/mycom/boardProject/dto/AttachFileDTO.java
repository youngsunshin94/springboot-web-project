package com.mycom.boardProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachFileDTO {

    private String uploadPath;
    private String fileName;
    private String uuid;
    private boolean image;

    public AttachFileDTO() {
    }

    public AttachFileDTO(String uploadPath, String fileName, String uuid, boolean image) {
        this.uploadPath = uploadPath;
        this.fileName = fileName;
        this.uuid = uuid;
        this.image = image;
    }
}
