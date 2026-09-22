package com.apirest.Api.resource;


import com.apirest.Api.dto.ClientDto;
import com.apirest.Api.servise.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
