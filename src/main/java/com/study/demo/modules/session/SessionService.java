package com.study.demo.modules.session;

import com.study.demo.model.SessionModel;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

public interface SessionService {

    List<SessionModel> getAll();
    ResponseEntity<?> getByOwner(String owner);
    URI create(SessionModel property);
    ResponseEntity<?> modifyAll(String id, SessionModel property);
    ResponseEntity<?> delete(String id);
    ResponseEntity<?> modify(String id, SessionModel property);

}
