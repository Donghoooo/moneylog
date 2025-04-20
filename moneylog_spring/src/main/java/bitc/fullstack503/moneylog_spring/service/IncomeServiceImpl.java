package bitc.fullstack503.moneylog_spring.service;

import bitc.fullstack503.moneylog_spring.dto.IncomeLogDTO;
import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;
import bitc.fullstack503.moneylog_spring.mapper.IncomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

//    todo 내용 입력하기


//    todo 내용 수정하기
    @Override
    public int todoUpdate(TodoListDTO todo)throws Exception {
    return incomeMapper.todoUpdate(todo);
    }
//    todo 내용 삭제하기
    @Override
    public int todoDelete(int todoSeq) throws Exception {
        return incomeMapper.todoDelete(todoSeq);
    }

    @Override
    public int signUpProcess(TodoListDTO title) throws Exception {
        return incomeMapper.signUpProcess(title);
    }


}
