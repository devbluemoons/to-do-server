package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoResponse {

    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url;

    public ToDoResponse(ToDoEntity toDoEntity) {

        this.id = toDoEntity.getId();
        this.title = toDoEntity.getTitle();
        this.order = toDoEntity.getOrder();
        this.completed = toDoEntity.getCompleted();

        this.url = "http://localhost:8080/" + this.id;
    }
}
