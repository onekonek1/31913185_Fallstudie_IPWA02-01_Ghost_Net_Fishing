package com.iu.ghostnet.service;

import com.iu.ghostnet.model.*;
import com.iu.ghostnet.repository.GhostNetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GhostNetService {
    @Autowired
    private GhostNetRepository repository;

    public List<GhostNet> findAll() { return repository.findAll(); }
    
    public GhostNet save(GhostNet net) { return repository.save(net); }

    public void startSalvaging(Long netId, Person salvager) {
        GhostNet net = repository.findById(netId).orElseThrow();
        if (net.getSalvagingPerson() == null) {
            net.setSalvagingPerson(salvager);
            net.setStatus(Status.BERGUNG_BEVORSTEHEND);
            repository.save(net);
        }
    }

    public void markAsSalvaged(Long netId) {
        GhostNet net = repository.findById(netId).orElseThrow();
        net.setStatus(Status.GEBORGEN);
        repository.save(net);
    }

    public void markAsMissing(Long netId, Person person) {
        GhostNet net = repository.findById(netId).orElseThrow();
        // Man kann Netze nicht anonym als verschollen melden.
        if (person.getName() != null && !person.getName().isEmpty()) {
            net.setStatus(Status.VERSCHOLLEN);
            repository.save(net);
        }
    }
}