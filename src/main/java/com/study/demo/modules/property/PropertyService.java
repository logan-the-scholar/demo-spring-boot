package com.study.demo.modules.property;

import com.study.demo.model.PropertyModel;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

public interface PropertyService {

    List<PropertyModel> getAll();
    ResponseEntity<?> getByOwner(String owner);
    URI create(PropertyModel property);
    ResponseEntity<?> modifyAll(String id, PropertyModel property);
    ResponseEntity<?> delete(String id);
    ResponseEntity<?> modify(String id, PropertyModel property);

}
