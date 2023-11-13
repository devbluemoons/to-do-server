package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.ToDoEntity;
import org.example.model.ToDoRequest;
import org.example.model.ToDoResponse;
import org.example.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class ToDoController {

    private final ToDoService service;

    @PostMapping
    public ResponseEntity<ToDoResponse> create(@RequestBody ToDoRequest request) {
        System.out.println("create");

        if (ObjectUtils.isEmpty(request.getTitle())) {
            return ResponseEntity.badRequest().build();
        }


        if (ObjectUtils.isEmpty(request.getOrder())) {
            request.setOrder(0L);
        }

        if (ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }

        ToDoEntity result = this.service.add(request);
        return ResponseEntity.ok(new ToDoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDoResponse> readOne(@PathVariable Long id) {
        System.out.println("read one");
        ToDoEntity result = this.service.searchById(id);
        return ResponseEntity.ok(new ToDoResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<ToDoResponse>> readAll() {
        System.out.println("read all");
        List<ToDoEntity> list = this.service.searchAll();
        List<ToDoResponse> responses = list.stream()
                .map(ToDoResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ToDoResponse> update(@PathVariable Long id, @RequestBody ToDoRequest request) {
        System.out.println("update");

        ToDoEntity result = this.service.updateById(id, request);

        return ResponseEntity.ok(new ToDoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        System.out.println("delete");

        this.service.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        System.out.println("delete all");

        this.service.deleteAll();

        return ResponseEntity.ok().build();
    }
}
