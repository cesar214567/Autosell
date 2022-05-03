package com.example.autosell.services.impl;

import com.example.autosell.controllers.beans.AutoSemiNuevoBean;
import com.example.autosell.entities.AutoSemiNuevo;
import com.example.autosell.repositories.AutoSemiNuevoRepository;
import com.example.autosell.services.AutoSemiNuevoService;
import com.example.autosell.utils.parser.Parser;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class DefaultAutoSemiNuevoService implements AutoSemiNuevoService {
    @Autowired
    AutoSemiNuevoRepository autoSemiNuevoRepository;

    @Override
    public AutoSemiNuevo save(AutoSemiNuevo autoSemiNuevo) {
        return autoSemiNuevoRepository.save(autoSemiNuevo);
    }

    @Override
    public void delete(AutoSemiNuevo autoSemiNuevo) {

    }

    @Override
    public AutoSemiNuevo getById(Long id) {
        return autoSemiNuevoRepository.findById(id).orElse(null);
    }

    @Override
    public List<AutoSemiNuevo> getAll() {
        return (List<AutoSemiNuevo>) autoSemiNuevoRepository.findAll();
    }

    @Override
    public List<AutoSemiNuevo> getAllEnabled(Boolean enabled, Boolean comprado, Boolean validado, Pageable pageable) {
        return autoSemiNuevoRepository.findByEnabledAndValidadoAndComprado(enabled,comprado,validado,pageable).getContent();
    }

    @Override
    public Long getPages(Boolean enabled, Boolean validado, Boolean comprado) {
        return autoSemiNuevoRepository.countAllByEnabledAndValidadoAndComprado(enabled,validado,comprado);
    }

    @Override
    public List<AutoSemiNuevoBean> getAllEnabled(Boolean enabled, Boolean validado, Boolean comprado){
        List<JSONObject> autosQueried = autoSemiNuevoRepository.findAllByEnabledAndValidadoAndComprado(enabled,validado,comprado);
        List<AutoSemiNuevoBean> autosBeans = new ArrayList<>();
        for(JSONObject jsonObject: autosQueried){
            autosBeans.add(new AutoSemiNuevoBean(jsonObject));
        }
        return autosBeans;
    }

    @Override
    public List<AutoSemiNuevo> getAllFromIdList(List<Long> ids) {
        return (List<AutoSemiNuevo>) autoSemiNuevoRepository.findAllById(ids);
    }

    @Override
    public List<AutoSemiNuevo> getByPlaca(String placa) {
        return autoSemiNuevoRepository.findAllByPlaca(placa);
    }

    @Override
    public void setVinAndColorByPlaca(String placa, String color, String vin) {
        autoSemiNuevoRepository.setVinAndColorByPlaca(placa,color,vin);
    }

    @Override
    public Boolean existsById(Long id) {
        return autoSemiNuevoRepository.existsById(id);
    }

    @Override
    public Long getAllVendidos() {
        return autoSemiNuevoRepository.countAllByComprado(true);
    }

    @Override
    public Long getAllNoVendidos() {
        return autoSemiNuevoRepository.countAllByEnabledAndValidadoAndComprado(true,true,false);
    }

    @Override
    public Long getAllMarcas() {
        return autoSemiNuevoRepository.countMarcas();
    }

    @Override
    public List<Tuple> getFilters() {
        return autoSemiNuevoRepository.filters();
    }

    @Override
    public List<String> getAllMarcasString() {
        return autoSemiNuevoRepository.findAllMarcasDistinct() ;
    }

    @Override
    public List<AutoSemiNuevo> getAutosNoValidados() {
        return autoSemiNuevoRepository.findAllByValidadoAndEnabled(false,true);
    }

    @Override
    public void setRevisado(Boolean revisado, Long id) {
        autoSemiNuevoRepository.setRevisado(revisado,id);
    }

    @Override
    public void borrarAuto(Long id) {
        autoSemiNuevoRepository.setRevisadoAndEnabled(true,false,id);
    }

    @Override
    public List<Long> getAllAutosVendidosByUsuario(Long userId) {
        return autoSemiNuevoRepository.findAllAutosNotComprados(userId);
    }


}
