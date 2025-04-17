package bitc.fullstack503.moneylog_spring.service.kms;

import bitc.fullstack503.moneylog_spring.dto.ExpenseLogDTO;
import bitc.fullstack503.moneylog_spring.dto.IncomeLogDTO;
import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;

import java.util.List;

public interface ListService {
    List<TodoListDTO> selectincomeList() throws Exception;

    List<ExpenseLogDTO> selectExpenseList(String memberId) throws Exception;
}
