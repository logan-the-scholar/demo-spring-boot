package com.study.demo.modules.property;

import com.study.demo.model.PropertyModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("property")
public class PropertyController {
    PropertyServiceImpl propertyService = new PropertyServiceImpl();

    @GetMapping
    public ResponseEntity<List<PropertyModel>> getAll() {
        return ResponseEntity.ok(propertyService.getAll());
    }

    @GetMapping("/by-owner/{owner}")
    public ResponseEntity<?> getByOwner(@PathVariable String owner) {
        return propertyService.getByOwner(owner);
    }

    @PostMapping
    public ResponseEntity<?> postProperty(@RequestBody PropertyModel property) {
        URI location = propertyService.create(property);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putProperty(@PathVariable int id, @RequestBody PropertyModel property) {
        return propertyService.modifyAll(id, property);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable int id) {
        return propertyService.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProperty(@PathVariable int id, @RequestBody PropertyModel property) {
        return propertyService.modify(id, property);
    }
}
