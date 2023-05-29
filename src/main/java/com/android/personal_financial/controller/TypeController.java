package com.android.personal_financial.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.android.personal_financial.model.Type;
import com.android.personal_financial.service.TypeService;

@RestController
@RequestMapping("/types")
public class TypeController {
    
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/{type_id}")
    public ResponseEntity<Type> getTypeById(@PathVariable("type_id") int typeId) {
        Type type = typeService.getTypeById(typeId);
        if (type == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(type);
    }

    @GetMapping
    public ResponseEntity<List<Type>> getAllTypes() {
        List<Type> types = typeService.getAllTypes();
        if (types.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(types);
    }
}

