package com.example.autosell.controllers.utils;


import com.example.autosell.Setup;
import com.example.autosell.utils.entities.Testimonios;
import com.example.autosell.utils.services.TestimoniosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/testimonios")
@CrossOrigin(origins = {Setup.local/*,Setup.route*/}, allowedHeaders = "*")
public class TestimoniosController {
    @Autowired
    TestimoniosService testimoniosService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> getAll(){
        try{
            return new ResponseEntity<>(testimoniosService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> save(@RequestBody Testimonios testimonios){
        try{
            testimoniosService.save(testimonios);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
