package com.apirest.Api.servise;

import com.apirest.Api.dto.ClientDto;
import com.apirest.Api.entities.Client;
import com.apirest.Api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired  ClientRepository clientRepository;

    public Page<ClientDto> findAllPaged(Pageable pageable){
        Page<Client> list = clientRepository.findAll(pageable);
        return list.map(x -> new ClientDto(x));
    }

}
