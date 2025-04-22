package bitc.fullstack503.moneylog_spring.controller.kms;

import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;
import bitc.fullstack503.moneylog_spring.service.kms.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListController {

    @Autowired
    private ListService listService;

    //    todo 리스트 보기
    @GetMapping({"/todoList/todo"})
    public List<TodoListDTO> selectTodolist() throws Exception{

        List<TodoListDTO> selectTodolist = listService.selectTodoList();

        return selectTodolist;
    }

    //    완료된 리스트 보기
    @GetMapping({"/todoList/done"})
    public List<TodoListDTO> selectDonelist() throws Exception{

        List<TodoListDTO> selectDonelist = listService.selectDoneList();

        return selectDonelist;
    }

    //    todo 내용 입력하기
    @PostMapping("todoList/addTodo")
    public int addTodo (@RequestBody TodoListDTO title) throws Exception {
        return listService.addTodo(title);
    }

    //    todo 내용 수정하기
    @PostMapping("todoList/update")
    public int todoUpdate(@RequestBody TodoListDTO todo) throws Exception {
        return listService.todoUpdate(todo);
    }

    //    todo 내용 삭제하기
    @DeleteMapping("todoList/delete")
    public int todoDelete(@RequestParam("todoSeq") int todoSeq) throws Exception{
        return listService.todoDelete(todoSeq);
    }

    //    todo 상태 수정하기
    @PostMapping("todoList/status")
    public int statusUpdate(@RequestBody TodoListDTO todo) throws Exception {
        return listService.statusUpdate(todo);
    }
}
