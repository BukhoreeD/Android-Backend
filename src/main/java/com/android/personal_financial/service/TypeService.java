package com.android.personal_financial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.android.personal_financial.model.Type;
import com.android.personal_financial.repository.TypeRepository;

@Service
public class TypeService {
    
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type getTypeById(int typeId) {
        Optional<Type> type = typeRepository.findByTypeId(typeId);
        return type.orElse(null);
    }

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }
}

