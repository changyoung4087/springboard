package org.board.commons.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.board.entites.Configs;
import org.board.repositories.ConfigsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class ConfigInfoService {

    private final ConfigsRepository repository;

    public <T> T get(String code, Class<T> clazz){
        return get(code, clazz, null);
    }
    public <T> T get(String code, TypeReference<T> type){
        return get(code, null, type);
    }
    public <T> T get(String code, Class<T> clazz, TypeReference<T> typeReference) {
        Configs configs = repository.findById(code).orElse(null);
        if(configs == null || configs.getValue() == null || configs.getValue().isBlank()) {
            return null;
        }

        String value = configs.getValue();

        // 변하는 값은 ObjectMapper 변환해줌.
        ObjectMapper om = new ObjectMapper();
        T data = null;
        try {
            // db에는 문자열 데이터(JSONdata)가 있어서 가져오는데 실제 jacson이 내장되어있고 핵심 클랙스가 objectMapper 이다
            // 여기엔 readValue, writeValue 라는 메서드가 있음 제이슨을 자바객체로 바꾸어줌(readValue) 자바객체를 문자열로 바꾸어줌(writeValue)
            if(clazz == null) data = om.readValue(value, typeReference);
            else data = om.readValue(value, clazz);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return data;
    }
}
