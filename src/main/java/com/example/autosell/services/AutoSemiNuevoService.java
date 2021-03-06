package com.example.autosell.services;

import com.example.autosell.controllers.beans.AutoSemiNuevoBean;
import com.example.autosell.entities.AutoSemiNuevo;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
import java.util.List;

public interface AutoSemiNuevoService {

    AutoSemiNuevo save(AutoSemiNuevo autoSemiNuevo);

    void delete(AutoSemiNuevo autoSemiNuevo);

    AutoSemiNuevo getById(Long id );

    List<AutoSemiNuevo> getAll();

    List<AutoSemiNuevo> getAllEnabled(Boolean enabled,Boolean validado,Boolean comprado, Pageable pageable);

    Long getPages(Boolean enabled, Boolean validado, Boolean comprado);

    List<AutoSemiNuevoBean> getAllEnabled(Boolean enabled, Boolean validado, Boolean comprado) throws ParseException, JsonProcessingException;

    List<AutoSemiNuevo> getAllFromIdList(List<Long> ids);


    Long getAllVendidos();

    Long getAllNoVendidos();

    Long getAllMarcas();

    List<Tuple> getFilters();

    List<String> getAllMarcasString();

    List<AutoSemiNuevo> getAutosNoValidados();

    void setRevisado(Boolean revisado,Long id);

    void borrarAuto(Long id);

    List<Long> getAllAutosVendidosByUsuario(Long userId);

    List<AutoSemiNuevo> getByPlaca(String placa);

    void setVinAndColorByPlaca(String placa, String color ,String vin );

    Boolean existsById(Long id);
}
