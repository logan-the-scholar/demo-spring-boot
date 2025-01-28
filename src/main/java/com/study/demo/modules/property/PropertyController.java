package com.study.demo.modules.property;

import com.study.demo.model.PropertyModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("property")
public class PropertyController {

    private List<PropertyModel> properties = new ArrayList(Arrays.asList(
            new PropertyModel(128932, "pepito", "Calle ficticia 123", 2),
            new PropertyModel(428932,"ludovico", "Peluche street", 5))
    );

    @GetMapping("/get-all")
    public ResponseEntity<List<PropertyModel>> getAll() {
          return ResponseEntity.ok(properties);
    }

    @GetMapping("/get/{owner}")
    public ResponseEntity<?> getByOwner(@PathVariable String owner) {
        Optional<PropertyModel> optional = properties.stream().filter(p -> p.getOwner().equalsIgnoreCase(owner))
                .reduce((p, c) -> p).stream().findFirst();

        if(optional.isPresent()) {
            return ResponseEntity.ok(optional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
        }
    }

    @PostMapping("/new")
    public ResponseEntity<PropertyModel> postProperty(@RequestBody PropertyModel property) {
        properties.add(property);
        return ResponseEntity.ok(property);
    }

    @PutMapping("/modify-all/{id}")
    public ResponseEntity<?> putProperty(@PathVariable int id, @RequestBody PropertyModel property) {
        Optional<PropertyModel> optional = properties.stream().filter(p -> p.getID() == id).findFirst().map(p -> {
            p.setAddress(property.getAddress());
            p.setOwner(property.getOwner());
            p.setRooms(property.getRooms());
            return p;
        });

        if(optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Modified successfully");
        } else {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Property not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable int id) {
        Optional<Boolean> optional = (new ArrayList<>(properties)).stream().filter(p -> p.getID() == id)
                .findFirst().map(p -> properties.remove(p));

        if(optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
        }
    }

    @PatchMapping("/modify/{id}")
    public Optional<PropertyModel> patchProperty(@PathVariable int id, @RequestBody PropertyModel property) {
        return properties.stream().reduce((p, i) -> {
            if(p.getID() == id) {
                if(property.getOwner() != null){
                    p.setOwner(property.getOwner());
                }
//                if(property.getRooms() != null){
//                    p.setRooms(property.getRooms());
//                }
                System.out.println(property.getRooms());
                if(property.getAddress() != null){
                    p.setAddress(property.getAddress());
                }

                return p;
            } else {
                return null;
            }
        });
    }
}
