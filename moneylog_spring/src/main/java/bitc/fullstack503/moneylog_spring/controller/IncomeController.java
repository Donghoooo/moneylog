package bitc.fullstack503.moneylog_spring.controller;

import bitc.fullstack503.moneylog_spring.dto.IncomeLogDTO;
import bitc.fullstack503.moneylog_spring.dto.MemberDTO;
import bitc.fullstack503.moneylog_spring.dto.TodoListDTO;
import bitc.fullstack503.moneylog_spring.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    //    todo 내용 입력하기
    @PostMapping ("todoList/addTodo")
    public int signUpProcess (@RequestBody TodoListDTO title) throws Exception {
        return incomeService.signUpProcess(title);
    }

    //    수입 내용 수정하기
    @PostMapping("todoList/update")
    public int todoUpdate(@RequestBody TodoListDTO todo) throws Exception {
        return incomeService.todoUpdate(todo);
    }

    //    수입 내용 삭제하기
    @DeleteMapping("todoList/delete")
    public int todoDelete(@RequestParam("todoSeq") int todoSeq) throws Exception{
        return incomeService.todoDelete(todoSeq);
    }

    //    상태 수정하기
    @PostMapping("todoList/status")
    public int statusUpdate(@RequestBody TodoListDTO todo) throws Exception {
        return incomeService.statusUpdate(todo);
    }


//    //    수입 내용 입력하기
//    @PostMapping("income/process")
//    public void incomeProcess (@RequestBody IncomeLogDTO income) throws Exception
//    {
//        income.setMemberId ("test1");
//        incomeService.income (income);
//    }

////    수입 내용 수정하기
//    @PostMapping("income/update")
//    public int incomeUpdate(@RequestBody IncomeLogDTO incomeLog) throws Exception {
//    return incomeService.incomeUpdate(incomeLog);
//}
//
////    수입 내용 삭제하기
//    @DeleteMapping("income/delete")
//    public int incomeDelete(@RequestParam("incomeLogSeq") int incomeLogSeq) throws Exception{
//        return incomeService.incomeDelete(incomeLogSeq);
//    }
}
