package com.mycom.boardProject.controller;

import com.mycom.boardProject.dto.AttachFileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {

    @PostMapping(value = "/uploadAjaxAction", produces = "application/json;charset=utf-8")
    public ResponseEntity<List<AttachFileDTO>> uploadAjax(MultipartFile[] uploadFile) {

        List<AttachFileDTO> list = new ArrayList<>();

        String uploadFolder = "D:\\upload";
        String uploadFolderPath = getFolder();

        File uploadPath = new File(uploadFolder, uploadFolderPath);

        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : uploadFile) {
            log.info("-----------------------------");
            log.info("upload file name : " + multipartFile.getOriginalFilename());
            log.info("upload file size : " + multipartFile.getSize());

            AttachFileDTO attach = new AttachFileDTO();

            String uploadFileName = multipartFile.getOriginalFilename();
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

            attach.setFileName(uploadFileName);

            UUID uuid = UUID.randomUUID();

            uploadFileName = uuid.toString() + "_" + uploadFileName;

            File saveFile = new File(uploadPath, uploadFileName);

            try {
                multipartFile.transferTo(saveFile);
                attach.setUuid(uuid.toString());
                attach.setUploadPath(uploadFolderPath);

                if(checkImageType(saveFile)){
                    attach.setImage(true);
                }
                list.add(attach);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);

        return str.replace("-", File.separator);
    }

    private boolean checkImageType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            return contentType.startsWith("image");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @GetMapping(value = "/display")
    public ResponseEntity<byte[]> display(String fileName) {
        File file = new File("D:\\upload\\" + fileName);
        ResponseEntity<byte[]> result = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String userAgent, String fileName) {
        Resource resource = new FileSystemResource(new File("D:\\upload\\" + fileName));

        if (resource.exists() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String resourceName = resource.getFilename();
        String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") +1);

        HttpHeaders headers = new HttpHeaders();

        try {
            String downloadName = null;

            if (userAgent.contains("Trident")) {
                log.info("IE browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\", " ");
            } else if (userAgent.contains("Edge")) {
                log.info("Edge");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
            } else {
                log.info("Chrome");
                downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
            }

            headers.add("Content-Disposition", "attachment; filename=" + downloadName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/deleteFile", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> deleteFile(String fileName) {
        File file;
        log.info("fileName = " + fileName);
        try {
            file = new File("D:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }
}
