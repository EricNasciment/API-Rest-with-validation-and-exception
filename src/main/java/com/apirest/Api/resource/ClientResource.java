package com.apirest.Api.resource;


import com.apirest.Api.dto.ClientDto;
import com.apirest.Api.servise.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping(value = "/client")
public class ClientResource {

    @Autowired
    public ClientService clientService;


    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAllPaged(Pageable pageable){
        Page<ClientDto> list = clientService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id){
         ClientDto entity = clientService.findById(id);
         return ResponseEntity.ok().body(entity);
    }

    @PostMapping
    public ResponseEntity<ClientDto> insert(@Valid @RequestBody ClientDto dto){
         ClientDto entity = clientService.insert(dto);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                 .buildAndExpand(dto.getId()).toUri();
         return ResponseEntity.created(uri).body(entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> update( @PathVariable Long id, @Valid @RequestBody ClientDto dto){
        ClientDto entity = clientService.update(id,dto);
        return  ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
