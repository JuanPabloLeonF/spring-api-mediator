package dev.juanleon.spring_api.user.infrastructure.api.input.controller;

import dev.juanleon.spring_api.common.mediator.Mediator;
import dev.juanleon.spring_api.user.application.dto.ResponseUser;
import dev.juanleon.spring_api.user.application.queries.GetAllUserQuery;
import dev.juanleon.spring_api.user.application.queries.GetByUserNameUserQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final Mediator mediator;

    public UserRestController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping
    public ResponseEntity<List<ResponseUser>> getAll() {
        GetAllUserQuery query = new GetAllUserQuery();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mediator.dispatch(query));
    }

    @GetMapping("/{userName}")
    public ResponseEntity<ResponseUser> getByUserName(@PathVariable String userName) {
        GetByUserNameUserQuery query = new GetByUserNameUserQuery(userName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.mediator.dispatch(query));
    }
}
