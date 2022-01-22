package com.example.autosell.controllers;

import com.example.autosell.Setup;
import com.example.autosell.entities.AutoSemiNuevo;
import com.example.autosell.services.AmazonService;
import com.example.autosell.utils.errors.ResponseService;
import com.sendgrid.Response;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(value = "/test")
@CrossOrigin(origins = {Setup.local/*,Setup.route*/}, allowedHeaders = "*")
public class TestController {

    @Autowired
    AmazonService amazonService;

    @PostMapping("/bucket")
    @ResponseBody
    public ResponseEntity<Object> testBucket(@RequestPart("files")MultipartFile[] multipartFiles,@RequestPart("nombre")String string){
        try{
            List<String> fileNames = new ArrayList<>();
            Arrays.stream(multipartFiles).forEach(file -> {
                fileNames.add(amazonService.uploadFile(file,"cesar","fotos"));
            });
            return ResponseService.genSuccess(fileNames);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/bucket")
    @ResponseBody
    public ResponseEntity<Object> deleteImage(@RequestParam("image")String string){
        try{
            return ResponseService.genSuccess(amazonService.deleteFileFromS3Bucket(string));
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> getPost(){
        return ResponseService.genSuccess(new AutoSemiNuevo());
    }




}
