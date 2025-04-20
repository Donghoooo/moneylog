package bitc.fullstack503.moneylog_spring.service.kms;

import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;
import bitc.fullstack503.moneylog_spring.mapper.kms.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListMapper listMapper;

    @Override
    public List<TodoListDTO> selectTodoList() throws Exception {
        return listMapper.selectTodoList();
    }

    @Override
    public List<TodoListDTO> selectDoneList() throws Exception {
        return listMapper.selectDoneList();
    }

    @Override
    public int addTodo(TodoListDTO title) throws Exception {
        return listMapper.addTodo(title);
    }

    @Override
    public int todoUpdate(TodoListDTO todo)throws Exception {
        return listMapper.todoUpdate(todo);
    }

    @Override
    public int todoDelete(int todoSeq) throws Exception {
        return listMapper.todoDelete(todoSeq);
    }

    @Override
    public int statusUpdate(TodoListDTO todo) throws Exception {
        return listMapper.statusUpdate(todo);
    }

}
