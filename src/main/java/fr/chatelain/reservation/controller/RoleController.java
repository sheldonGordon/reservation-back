package fr.chatelain.reservation.controller;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.Role;
import fr.chatelain.reservation.model.dto.RoleDto;
import fr.chatelain.reservation.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> listRoleDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try {
            roleService.findAll().forEach(c -> listRoleDto.add(modelMapper.map(c, RoleDto.class)));
            return new ResponseEntity<>(listRoleDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();
        try {
            return new ResponseEntity<>(modelMapper.map(roleService.getById(id).get(), RoleDto.class), HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new RoleDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/role")
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto role) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Role entity = modelMapper.map(role, Role.class);
            roleService.save(entity);
            return new ResponseEntity<>(role, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new RoleDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/role")
    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto role) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            roleService.update(modelMapper.map(role, Role.class));
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new RoleDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable(name = "id") String id) {
        try {
            roleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
