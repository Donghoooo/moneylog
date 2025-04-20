package bitc.fullstack503.moneylog_spring.controller.kms;

import bitc.fullstack503.moneylog_spring.dto.ExpenseLogDTO;
import bitc.fullstack503.moneylog_spring.dto.IncomeLogDTO;
import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;
import bitc.fullstack503.moneylog_spring.service.kms.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListController {

    @Autowired
    private ListService listService;

    @GetMapping({"/todoList/todo"})
    public List<TodoListDTO> selectTodolist() throws Exception{

        List<TodoListDTO> selectTodolist = listService.selectTodoList();

        return selectTodolist;
    }

    @GetMapping({"/todoList/done"})
    public List<TodoListDTO> selectDonelist() throws Exception{

        List<TodoListDTO> selectDonelist = listService.selectDoneList();

        return selectDonelist;
    }
}
