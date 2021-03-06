package com.example.autosell.repositories;

import com.example.autosell.entities.AutoSemiNuevo;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;



public interface AutoSemiNuevoRepository extends PagingAndSortingRepository<AutoSemiNuevo,Long> {

    Page<AutoSemiNuevo> findByEnabledAndValidadoAndComprado(Boolean enabled,Boolean validado,Boolean comprado, Pageable pageable);

    Long countAllByEnabledAndValidadoAndComprado(Boolean enabled,Boolean validado,Boolean comprado);

    @Query(nativeQuery = true,value = "select * from  auto_semi_nuevo a where enabled=:enabled and validado=:validado and comprado=:comprado")
    List<JSONObject> findAllByEnabledAndValidadoAndComprado(Boolean enabled, Boolean validado, Boolean comprado);

    List<AutoSemiNuevo> findAllByPlaca(String placa);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update auto_semi_nuevo a set color=:color, vin=:vin where a.placa=:placa ")
    void setVinAndColorByPlaca(String placa,String color, String vin);


    Long countAllByComprado(Boolean comprado);

    @Query(nativeQuery = true,value = "select count(distinct(a.marca)) from auto_semi_nuevo a where a.validado=true and a.enabled=true")
    Long countMarcas();

    @Query(nativeQuery = true,value = "select count(a.id_auto_semi_nuevo),a.marca,a.modelo,a.tipo_carroceria  from auto_semi_nuevo a  where a.validado=true and a.enabled=true group by (a.marca,a.modelo,a.tipo_carroceria )")
    List<Tuple> filters();

    @Query(nativeQuery = true,value = "select distinct (a.marca)from auto_semi_nuevo a  where a.validado=true and a.enabled=true")
    List<String> findAllMarcasDistinct();

    List<AutoSemiNuevo> findAllByValidadoAndEnabled(Boolean validado,Boolean enabled);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update auto_semi_nuevo a set revisado=:revisado where a.id_auto_semi_nuevo=:id ")
    void setRevisado(Boolean revisado,Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update auto_semi_nuevo a set revisado=:revisado, enabled=:enabled where a.id_auto_semi_nuevo=:id ")
    void setRevisadoAndEnabled(Boolean revisado,Boolean enabled,Long id);


    @Query(nativeQuery = true,value = "select a.id_auto_semi_nuevo from auto_semi_nuevo a where a.id_usuario=:userId and a.comprado=true ")
    List<Long> findAllAutosNotComprados(Long userId);

}
