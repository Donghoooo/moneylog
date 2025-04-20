package bitc.fullstack503.moneylog_spring.mapper;

import bitc.fullstack503.moneylog_spring.dto.IncomeLogDTO;
import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IncomeMapper {
    //    todo 내용 입력하기

    //    todo 내용 수정하기
    int todoUpdate(TodoListDTO todo)throws Exception;

    //    todo 내용 삭제하기
    int todoDelete(int todoSeq)throws Exception;

    int signUpProcess(TodoListDTO title) throws Exception;
}
