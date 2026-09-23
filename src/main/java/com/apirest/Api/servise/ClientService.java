package com.apirest.Api.servise;

import com.apirest.Api.dto.ClientDto;
import com.apirest.Api.entities.Client;
import com.apirest.Api.exceptions.DataBaseException;
import com.apirest.Api.exceptions.ResourceNotFoundException;
import com.apirest.Api.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired  ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAllPaged(Pageable pageable){
        Page<Client> list = clientRepository.findAll(pageable);
        return list.map(x -> new ClientDto(x));
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id){
        Optional<Client> obj = clientRepository.findById(id);
         Client entity = obj.orElseThrow( () -> new ResourceNotFoundException("Recurso não encontrado"));
         return new ClientDto(entity);
    }

    @Transactional
    public ClientDto insert(ClientDto dto){
       Client entity = new Client();
       copyDtoToEntity(entity,dto);
      entity=  clientRepository.save(entity);
       return new ClientDto(entity);
    }

    @Transactional
    public ClientDto update(Long id,ClientDto dto){
        try{
        Client entity = clientRepository.getReferenceById(id);
        copyDtoToEntity(entity,dto);
        entity = clientRepository.save(entity);
        return new ClientDto(entity);}
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }try{
        clientRepository.deleteById(id);}
        catch (DataIntegrityViolationException e){
             throw new DataBaseException("Entidade não pode ser deletada");
        }
    }



    private void copyDtoToEntity(Client entity, ClientDto dto){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());

    }
}
