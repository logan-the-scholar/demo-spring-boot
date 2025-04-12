package com.study.demo.modules.session;

import com.study.demo.modules.session.model.DemoSession;
import com.study.demo.modules.session.model.SessionModel;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

public interface SessionService {

    List<SessionModel> getAll();
    ResponseEntity<?> getByOwner(String owner);
    URI create(SessionModel property);
    ResponseEntity<?> codeDemo(DemoSession scriptDemo);
    ResponseEntity<?> delete(String id);
    //ResponseEntity<?> modify(String id, SessionModel property);
}
