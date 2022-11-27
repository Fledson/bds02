package com.devsuperior.bds02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

  @Autowired
  private EventRepository repository;

  @Transactional
  public EventDTO update(Long id, EventDTO entityDto) {
    Event event = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));

    event.setName(entityDto.getName());
    event.setDate(entityDto.getDate());
    event.setUrl(entityDto.getUrl());
    event.setCity(new City(entityDto.getCityId(), null));

    event = repository.save(event);

    return new EventDTO(event);
  }

}
