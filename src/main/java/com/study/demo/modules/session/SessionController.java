package com.study.demo.modules.session;

import com.study.demo.modules.session.model.DemoSession;
import com.study.demo.modules.session.model.SessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("session")
public class SessionController {

    @Autowired
    //@Qualifier("propertyJson")
    private SessionService sessionService;
    
    @GetMapping
    public ResponseEntity<List<SessionModel>> getAll() {
        return ResponseEntity.ok(sessionService.getAll());
    }

    @GetMapping("/by-owner/{owner}")
    public ResponseEntity<?> getByOwner(@PathVariable String owner) {
        return sessionService.getByOwner(owner);
    }

    @PostMapping
    public ResponseEntity<?> postProperty(@RequestBody SessionModel property) {
        URI location = sessionService.create(property);
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/code-demo")
    public ResponseEntity<?> codeDemo(@RequestBody DemoSession scriptDemo) {
        return sessionService.codeDemo(scriptDemo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable String id) {
        return sessionService.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProperty(@PathVariable String id, @RequestBody SessionModel property) {
        return ResponseEntity.ok("not_ok");//sessionService.modify(id, property);
    }
}
