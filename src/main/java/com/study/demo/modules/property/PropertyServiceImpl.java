package com.study.demo.modules.property;

import com.study.demo.model.PropertyModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PropertyServiceImpl {

    private List<PropertyModel> properties = new ArrayList(Arrays.asList(
            new PropertyModel(128932, "pepito", "Calle ficticia 123", 2),
            new PropertyModel(428932, "ludovico", "Peluche street", 5))
    );

    public List<PropertyModel> getAll() {
        return properties;
    }

    public ResponseEntity<?> getByOwner(String owner) {
        Optional<PropertyModel> optional = properties.stream().filter(p -> p.getOwner().equalsIgnoreCase(owner))
                .reduce((p, c) -> p).stream().findFirst();

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
    }

    public URI create(PropertyModel property) {
        properties.add(property);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/by-owner/{owner}")
                .buildAndExpand(property.getOwner())
                .toUri();
    }

    public ResponseEntity<?> modifyAll(Integer id, PropertyModel property) {
        return properties.stream()
                .filter(p -> p.getID() == id)
                .findFirst()
                .<ResponseEntity<?>>map(p -> {
                    p.setAddress(property.getAddress());
                    p.setOwner(property.getOwner());
                    p.setRooms(property.getRooms());
                    return ResponseEntity.ok(p);
                }).orElseGet(() -> ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Property not found"));
    }

    public ResponseEntity<?> delete(Integer id) {
        return (new ArrayList<>(properties)).stream()
                .filter(p -> p.getID() == id)
                .findFirst()
                .<ResponseEntity<?>>map(p -> {
                    properties.remove(p);
                    return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found"));
    }

    public ResponseEntity<?> modify(Integer id, PropertyModel property) {

        return properties.stream().filter(p -> p.getID() == id)
                .findFirst()
                .<ResponseEntity<?>>map(p -> {
                    if (property.getOwner() != null) {
                        p.setOwner(property.getOwner());
                    }
                    if (property.getRooms() != null) {
                        p.setRooms(property.getRooms());
                    }
                    if (property.getAddress() != null) {
                        p.setAddress(property.getAddress());
                    }

                    return ResponseEntity.ok(p);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found"));
    }
}
