package com.example.autosell.controllers;

import com.example.autosell.Setup;
import com.example.autosell.entities.*;
import com.example.autosell.services.*;
import com.example.autosell.utils.errors.ResponseService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = {Setup.local/*,Setup.route*/}, allowedHeaders = "*")
public class    AdminController {

    @Autowired
    UsersService usersService;

    @Autowired
    AutoSemiNuevoService autoSemiNuevoService;

    @DeleteMapping("/reported")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> remove(@RequestParam("id") Long idAuto){
        try{
            autoSemiNuevoService.borrarAuto(idAuto);
            return ResponseService.genSuccess(null);
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
