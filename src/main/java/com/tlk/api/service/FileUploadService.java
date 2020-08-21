package com.tlk.api.service;

import com.tlk.api.utils.DateUtils;
import com.tlk.api.utils.FileUtil;
import com.tlk.api.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Service
public class FileUploadService {

    protected static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    @Value("${file.upload.root}")
    private String fileUploadRoot;

    public String uploadSingleFile(MultipartHttpServletRequest request) {
        String fileName = "";
        Iterator<String>it = request.getFileNames();

        try {
            while (it.hasNext()) {
                String uploadFileName = it.next();
                if (!"".equals(StringUtils.isNullString(uploadFileName))) {
                    MultipartFile multipartFile = request.getFile(uploadFileName);

                    String originalFileName = multipartFile.getOriginalFilename();
                    //파일명 변경
                    String makeFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
                    int filePos = originalFileName.lastIndexOf(".");
                    String fileExtension = originalFileName.substring(filePos+1);

                    String finalFileName = "tlk_image" + "_" + DateUtils.returnNowDateByYyyymmddhhmmss() + "." + fileExtension;

                    //년도 디렉토리 존재 확인
                    File yearDirectory = new File(FileUtil.concatPath(fileUploadRoot, DateUtils.getNowYear()));
                    if (!yearDirectory.isDirectory()) {
                        yearDirectory.mkdirs();
                    }
                    //월일 디렉토리가 존재 확인
                    String mmdd = DateUtils.plusDay(DateUtils.returnToDate("yyyy-MM-dd"), "MMDD", 0);
                    //디렉토리 존재 확인
                    File todayDirectory = new File(FileUtil.concatPath(yearDirectory.toString(), mmdd));
                    if (!todayDirectory.isDirectory()) {
                        todayDirectory.mkdirs();
                    }
                    if (originalFileName != null || !"".equals(originalFileName)) {
                        File serverFile = new File(StringUtils.concatPath(todayDirectory.toString(), finalFileName));
                        logger.info("serverFile ---------------> " + serverFile);
                        multipartFile.transferTo(serverFile);
                        //root경로 파일 삭제
                        FileUtil.fileDelete(finalFileName);
                        FileUtil.fileDelete(originalFileName);
                        logger.info("originalFileName ---------------> " + originalFileName);

                        fileName = FileUtil.concatPath(todayDirectory.toString(), serverFile.getName());
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return fileName;
    }
}
