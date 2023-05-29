package org.board.commons.configs;

import lombok.RequiredArgsConstructor;
import org.board.entites.Configs;
import org.board.repositories.ConfigsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigDeleteService {
    private final ConfigsRepository repository;

    public void delete(String code) {
        Configs configs = repository.findById(code).orElse(null);
        if(configs == null) {
            return;
        }
        
        repository.delete(configs);
        repository.flush();
    }
}
