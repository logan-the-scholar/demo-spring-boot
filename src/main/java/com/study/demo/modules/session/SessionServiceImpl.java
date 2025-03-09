package com.study.demo.modules.session;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.demo.model.SessionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("propertyJson")
public class SessionServiceImpl implements SessionService {

    public List<SessionModel> getAll() {
        List<SessionModel> properties;

        try {
            properties = new ObjectMapper().readValue(
                    //this.getClass().getClassLoader().getResourceAsStream("/properties.json"),
                    //new File(String.valueOf(this.getClass().getResource("properties.json"))),
                    new File("src/main/resources/properties.json"),
                    new TypeReference<List<SessionModel>>() {
                    });

            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeAll(List<SessionModel> properties) {
        ObjectMapper om = new ObjectMapper();

        try {
            om.writerWithDefaultPrettyPrinter()
                    .writeValue(
                            new File("src/main/resources/properties.json"),
                            //new File(String.valueOf(this.getClass().getResource("/properties.json"))),
                            properties);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getByOwner(String owner) {
        Optional<SessionModel> optional = this.getAll().stream().filter(p -> p.getOwner().equalsIgnoreCase(owner))
                .reduce((p, c) -> p).stream().findFirst();

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found");
    }

    public SessionModel getById(String id) {
        Optional<SessionModel> optional = this.getAll().stream().filter(p -> p.getID().equalsIgnoreCase(id))
                .reduce((p, c) -> p).stream().findFirst();

        if (optional.isPresent()) {
            return optional.get();
        }

        throw new RuntimeException("Property not found");
    }

    public URI create(SessionModel property) {
        List<SessionModel> properties;
        ObjectMapper om = new ObjectMapper();

        properties = this.getAll();
        properties.add(property);
        this.writeAll(properties);

        return ServletUriComponentsBuilder.fromCurrentRequest().path("/by-owner/{owner}")
                .buildAndExpand(property.getOwner())
                .toUri();
    }

    public ResponseEntity<?> modifyAll(String id, SessionModel property) {
        List<SessionModel> properties = this.getAll();

        SessionModel modified = properties.stream()
                .filter(p -> p.getID().equalsIgnoreCase(id))
                .findFirst().<SessionModel>map(p -> {
                    p.setAddress(property.getAddress());
                    p.setOwner(property.getOwner());
                    p.setRooms(property.getRooms());
                    return p;
                }).orElseThrow(() -> new RuntimeException("Property not found"));

        this.writeAll(properties);

        return ResponseEntity.ok(modified);
//        return properties.stream()
//                .filter(p -> p.getID().equalsIgnoreCase(id))
//                .findFirst()
//                .<ResponseEntity<?>>map(p -> {
//                    p.setAddress(property.getAddress());
//                    p.setOwner(property.getOwner());
//                    p.setRooms(property.getRooms());
//                    return ResponseEntity.ok(p);
//                }).orElseGet(() -> ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Property not found"));
    }

    public ResponseEntity<?> delete(String id) {
        List<SessionModel> properties = this.getAll();

        return (new ArrayList<>(properties)).stream()
                .filter(p -> p.getID().equalsIgnoreCase(id))
                .findFirst()
                .<ResponseEntity<?>>map(p -> {
                    properties.remove(p);
                    this.writeAll(properties);
                    return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found"));
    }

    public ResponseEntity<?> modify(String id, SessionModel property) {
        List<SessionModel> properties = this.getAll();

        return properties.stream().filter(p -> p.getID().equalsIgnoreCase(id))
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
