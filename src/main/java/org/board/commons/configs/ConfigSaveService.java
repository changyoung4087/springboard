package org.board.commons.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.board.entites.Configs;
import org.board.repositories.ConfigsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigSaveService {

    private final ConfigsRepository repository;
    public <T> void save(String code, T t) {

        // 코드가 기존에 있으면 재사용 없으면 생성
        Configs configs = repository.findById(code).orElseGet(Configs::new);

        ObjectMapper om = new ObjectMapper();
        String value = null;
        try {
            value = om.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        configs.setCode(code);
        configs.setValue(value);

        repository.saveAndFlush(configs);
    }
}
