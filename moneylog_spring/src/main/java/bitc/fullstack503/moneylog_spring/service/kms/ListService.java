package bitc.fullstack503.moneylog_spring.service.kms;

import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;

import java.util.List;

public interface ListService {

    List<TodoListDTO> selectTodoList() throws Exception;

    List<TodoListDTO> selectDoneList() throws Exception;

    int addTodo(TodoListDTO title) throws Exception;

    int todoUpdate(TodoListDTO todo)throws Exception;

    int todoDelete(int todoSeq) throws Exception;

    int statusUpdate(TodoListDTO todo) throws Exception;

}
