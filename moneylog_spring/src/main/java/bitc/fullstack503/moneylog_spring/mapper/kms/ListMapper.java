package bitc.fullstack503.moneylog_spring.mapper.kms;

import bitc.fullstack503.moneylog_spring.dto.ExpenseLogDTO;
import bitc.fullstack503.moneylog_spring.dto.IncomeLogDTO;
import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListMapper {
    List<TodoListDTO> selectTodoList() throws Exception;

    List<TodoListDTO> selectDoneList() throws Exception;


//    List<ExpenseLogDTO> selectExpenseList(String memberId) throws Exception;
}
