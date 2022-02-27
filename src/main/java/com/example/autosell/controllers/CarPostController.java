package com.example.autosell.controllers;


import com.example.autosell.Setup;
import com.example.autosell.controllers.beans.FiltrosBean;
import com.example.autosell.controllers.beans.InteresadoCompra;
import com.example.autosell.repositories.UsersRepository;
import com.example.autosell.services.*;
import com.example.autosell.utils.entities.Accesorio;
import com.example.autosell.utils.errors.ResponseService;
import com.example.autosell.utils.services.AccesorioService;
import com.example.autosell.entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;
import java.util.Date;

@RestController
@RequestMapping(value = "/post")
@CrossOrigin(origins = {Setup.local/*,Setup.route*/}, allowedHeaders = "*")
public class CarPostController {

    @Autowired
    AutoSemiNuevoService autoSemiNuevoService;

    @Autowired
    AmazonService amazonService;

    @Autowired
    AccesorioService accesorioService;

    @Autowired
    GoogleService googleService;

    @Autowired
    UsersService usersService;
    @Value("${spreadsheet.autos}")
    String spreadsheetAutosId;
    @Value("${spreadsheet.interesados}")
    String spreadsheetInteresadosId;
    @Value("${spreadsheet.ventas}")
    String spreadsheetVentasId;


    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> post(HttpServletRequest request) {
        try {
            List<MultipartFile> multipartFiles = new ArrayList<>();
            MultipartFile firstFile = null;
            String model = "{}";
            for (Part part : request.getParts()) {
                if (part.getContentType() != null) {
                    if (part.getName().equals("fotoPrincipal")) {
                        firstFile = new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream());
                    } else {
                        multipartFiles.add(new MockMultipartFile(part.getSubmittedFileName(), part.getSubmittedFileName(), part.getContentType(), part.getInputStream()));
                    }
                } else {
                    String theString = IOUtils.toString(part.getInputStream(), StandardCharsets.UTF_8);
                    model = String.valueOf(theString);
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            AutoSemiNuevo autoSemiNuevo = mapper.readValue(model, AutoSemiNuevo.class);
            if (autoSemiNuevo.getMarca()==null || autoSemiNuevo.getModelo()==null || autoSemiNuevo.getTipoCarroceria() ==null){
                return ResponseService.genError("no se enviaron los datos del carro",HttpStatus.BAD_REQUEST);
            }
            if (autoSemiNuevo.getValidado()==null ){
                return ResponseService.genError("no se envio la validacion del carro",HttpStatus.BAD_REQUEST);
            }
            //public ResponseEntity<Object> post(@RequestBody AutoSemiNuevo autoSemiNuevo){
            List<AutoSemiNuevo> temp = autoSemiNuevoService.getByPlaca(autoSemiNuevo.getPlaca());
            if (autoSemiNuevo.getPlaca() == null) {
                return ResponseService.genError("no se mando la placa", HttpStatus.BAD_REQUEST);
            }
            for (AutoSemiNuevo semiNuevo : temp) {
                if (!semiNuevo.getComprado() && semiNuevo.getEnabled()) {
                    return ResponseService.genError("este auto esta siendo vendido", HttpStatus.BAD_REQUEST);
                }
            }
            autoSemiNuevo.setComprado(false);

            autoSemiNuevo.setEnabled(true);
            autoSemiNuevo.setFechaPublicacion(new Date());
            List<String> fotos = new ArrayList<>();
            List<Accesorio> accesoriosList = new ArrayList<>();
            if (autoSemiNuevo.getValidado()) {
                for (Accesorio accesorio : autoSemiNuevo.getAccesorios()) {
                    Accesorio accesorioTemp = accesorioService.getById(accesorio.getId());
                    if (accesorioTemp != null) {
                        accesoriosList.add(accesorioTemp);
                    }
                }
            }

            autoSemiNuevo.setAccesorios(accesoriosList);
            autoSemiNuevo = autoSemiNuevoService.save(autoSemiNuevo);
            final Long id = autoSemiNuevo.getId();
            (multipartFiles).forEach(file -> {
                fotos.add(amazonService.uploadFile(file, "", "fotosAutos/" + id.toString()));
            });
            if (firstFile != null) {
                String fotoPrincipal = amazonService.uploadFile(firstFile, "", "fotosAutos/" + id.toString());
                autoSemiNuevo.setFotoPrincipal(fotoPrincipal);
            }
            autoSemiNuevo.setFotos(fotos);
            autoSemiNuevo = autoSemiNuevoService.save(autoSemiNuevo);
            //TODO POSTULAR AUTOS
            if(!autoSemiNuevo.getValidado()){
                googleService.appendData(autoSemiNuevo.serialize(),spreadsheetAutosId);
            }
            ////////////
            return ResponseService.genSuccess(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseService.genError("fallo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping(value = "/interesadoCompra")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> intCompra(@RequestBody InteresadoCompra interesadoCompra) {
        try{
            if(!autoSemiNuevoService.existsById(interesadoCompra.getAutoSemiNuevoId())){
                return ResponseService.genError("no se encontro el auto o no se envio su id", HttpStatus.BAD_REQUEST);
            }
            googleService.appendData(interesadoCompra.serialize(),spreadsheetInteresadosId);
            return ResponseService.genSuccess(null);
        }catch (Exception e){
            return ResponseService.genError("fallo", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Object> getValidateById(@PathVariable("id") Long id) {
        try{
            if(id==null)
                return ResponseService.genError("no se mando el id",HttpStatus.BAD_REQUEST);
            AutoSemiNuevo autoSemiNuevo = autoSemiNuevoService.getById(id);
            if(autoSemiNuevo==null)
                return ResponseService.genError("no se encontro el auto",HttpStatus.NOT_FOUND);
            return ResponseService.genSuccess(autoSemiNuevo);
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/query")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> getEnabled(@RequestParam("enabled")Boolean enabled,@RequestParam("comprado")Boolean comprado,@RequestParam("validado")Boolean validado){
        try{
            System.out.println(enabled);
            return ResponseService.genSuccess(autoSemiNuevoService.getAllEnabled(enabled,validado,comprado));
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> getAll() throws SQLException {
        try{
            return ResponseService.genSuccess(autoSemiNuevoService.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(value = "/enabled/{pageId}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> getEnabledPaginated(@PathVariable("pageId") Integer pageId,@RequestParam("enabled")Boolean enabled,@RequestParam("comprado")Boolean comprado,@RequestParam("validado")Boolean validado){
        try{
            return ResponseService.genSuccess(autoSemiNuevoService.getAllEnabled(enabled,validado,comprado,PageRequest.of(pageId,8, Sort.by("fechaPublicacion").descending())));
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/enabled/count")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> getEnabledCount(){
        try{
            return ResponseService.genSuccess(autoSemiNuevoService.getPages(true,true,false));
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/validate/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> modify(@PathVariable("id")Long id ){
        AutoSemiNuevo autoSemiNuevo = autoSemiNuevoService.getById(id);
        if(autoSemiNuevo==null){
            return ResponseService.genError("no se encontro el post con ese id",HttpStatus.BAD_REQUEST);
        }
        autoSemiNuevo.setValidado(true);
        try{
            return ResponseService.genSuccess(autoSemiNuevoService.save(autoSemiNuevo));
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> updatePost(HttpServletRequest request) {
//  public ResponseE2ntity<Object> updatePost(@RequestBody AutoSemiNuevo autoSemiNuevo){
        try{

            List<MultipartFile> multipartFiles = new ArrayList<>();
            MultipartFile firstFile = null;
            String model="{}";
            for (Part part:request.getParts()){
                if(part.getContentType()!=null ){
                    if (part.getName().equals("fotoPrincipal")){
                        firstFile = new MockMultipartFile(part.getSubmittedFileName(),part.getSubmittedFileName(),part.getContentType(),part.getInputStream());
                    }else{
                        multipartFiles.add(new MockMultipartFile(part.getSubmittedFileName(),part.getSubmittedFileName(),part.getContentType(),part.getInputStream()) );
                    }
                }else{
                    String theString = IOUtils.toString(part.getInputStream(), StandardCharsets.UTF_8);
                    model = String.valueOf(theString);
                }
            }
            ObjectMapper objectMapper = new ObjectMapper();
            AutoSemiNuevo autoSemiNuevo = objectMapper.readValue(model,AutoSemiNuevo.class);
            if(autoSemiNuevo.getId()==null)return ResponseService.genError("no se envio id",HttpStatus.BAD_REQUEST);
            AutoSemiNuevo temp = autoSemiNuevoService.getById(autoSemiNuevo.getId());
            if(temp==null){
                return ResponseService.genError("no se encontro el auto",HttpStatus.BAD_REQUEST);
            }
            if(temp.getComprado()){
                return ResponseService.genError("el auto no puede ser modificado porque ya se vendio",HttpStatus.BAD_REQUEST);
            }
            List<Accesorio> accesoriosList = new ArrayList<>();
            for (Accesorio accesorio : autoSemiNuevo.getAccesorios()) {
                Accesorio accesorioTemp = accesorioService.getById(accesorio.getId());
                if (accesorioTemp!=null){
                    accesoriosList.add(accesorioTemp);
                }
            }
            temp.setAccesorios(accesoriosList);
            autoSemiNuevo.info(temp);
            Map<String,Integer> fotos = new HashMap<>();
            autoSemiNuevo.getFotos().forEach(foto->fotos.put(foto,0));
            if (temp.getFotoPrincipal()!=null && !autoSemiNuevo.getFotoPrincipal().equals(temp.getFotoPrincipal())) {
                fotos.put(autoSemiNuevo.getFotoPrincipal(), 0);
            }
            temp.setFotoPrincipal(autoSemiNuevo.getFotoPrincipal());
            temp.getFotos().forEach(foto->{
                if(!fotos.containsKey(foto)){
                    amazonService.deleteFileFromS3Bucket(foto);
                }
            });
            temp.setFotos(autoSemiNuevo.getFotos());
            (multipartFiles).forEach(file->temp.getFotos().add(amazonService.uploadFile(file,"","fotosAutos/"+ temp.getId().toString())));
            if(firstFile!=null){
                if(temp.getFotoPrincipal()!=null)amazonService.deleteFileFromS3Bucket(temp.getFotoPrincipal());
                temp.setFotoPrincipal(amazonService.uploadFile(firstFile,"","fotosAutos/"+ temp.getId().toString()));

            }
            autoSemiNuevoService.save(temp);
            return ResponseService.genSuccess(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/vendidos")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> getVendidos(){
        try{
            return ResponseService.genSuccess(autoSemiNuevoService.getAllVendidos());
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/novendidos")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> getNoVendidos()  {
        try{
            Long sumatotal = autoSemiNuevoService.getAllNoVendidos();
            return ResponseService.genSuccess(sumatotal);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/marcas")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> getMarcas() throws SQLException {
        try{
            List<String> marcas = autoSemiNuevoService.getAllMarcasString();
            return ResponseService.genSuccess(marcas.size());
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/filtros")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> getFiltros() {
        try{
            List<FiltrosBean> filtrosBeans = new ArrayList<>();
            List<Tuple> temp = autoSemiNuevoService.getFilters();
            for (Tuple tuple : temp) {
                filtrosBeans.add(new FiltrosBean((String)tuple.get("marca"),(String)tuple.get("modelo"),(String)tuple.get("tipo_carroceria"),"USED"));
            }


            return ResponseService.genSuccess(filtrosBeans);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/novalidados")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> getAutosNoValidados(){
        try{
            return ResponseService.genSuccess(autoSemiNuevoService.getAutosNoValidados());
        }catch (Exception e){
            return ResponseService.genError("fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/venta")
    @ResponseBody
    @Transactional
    public ResponseEntity<Object> venta(@RequestBody AutoSemiNuevo autoSemiNuevo) {
        try{
            if(autoSemiNuevo.getId()==null || autoSemiNuevo.getComision()==null || autoSemiNuevo.getPrecioFinalVenta()==null || autoSemiNuevo.getSellout()==null || autoSemiNuevo.getSellout().getId()==null){
                return ResponseService.genError("no se enviaron los datos correctos",HttpStatus.BAD_REQUEST);
            }
            AutoSemiNuevo temp = autoSemiNuevoService.getById(autoSemiNuevo.getId());
            if(temp == null){
                return ResponseService.genError("no se encontro el auto",HttpStatus.BAD_REQUEST);
            }
            if(temp.getComprado()){
                return ResponseService.genError("el auto ya esta siendo vendido",HttpStatus.CONFLICT);
            }
            temp.setComprado(true);
            autoSemiNuevo.sellInfo(temp);
            temp.setSellout(usersService.getById(temp.getSellout().getId()));
            temp.setMargen(temp.getPrecioFinalVenta()*temp.getComision());
            autoSemiNuevoService.save(temp);
            googleService.appendData(temp.serializeSell(),spreadsheetVentasId);
            return ResponseService.genSuccess(temp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseService.genError("fallo en el servidor",HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}

