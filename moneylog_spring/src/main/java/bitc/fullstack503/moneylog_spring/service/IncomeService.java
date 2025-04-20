package bitc.fullstack503.moneylog_spring.service;

import bitc.fullstack503.moneylog_spring.dto.IncomeLogDTO;
import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;

public interface IncomeService {
//    todo 내용 입력하기

//    todo 내용 수정하기
    int todoUpdate(TodoListDTO todo)throws Exception;

//    todo 내용 삭제하기
    int todoDelete(int todoSeq) throws Exception;

    int signUpProcess(TodoListDTO title) throws Exception;
}
