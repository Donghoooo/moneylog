package bitc.example.app

import bitc.example.app.dto.TodoListDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AppServerInterface {

//  todo 입력
  @POST("todoList/addTodo")
  fun postSignUp(@Body title: TodoListDTO): Call<Int>

  //  수입 수정
  @POST("todoList/update")
  fun updateTodo(@Body todo: TodoListDTO): Call<Int>

  //  상태 수정
  @POST("todoList/status")
  fun updateStatus(@Body todo: TodoListDTO): Call<Int>

  // 투두 삭제
  @DELETE("todoList/delete")
  fun deleteTodo(@Query("todoSeq") todoSeq: Int): Call<Int>

  //  투두 리스트
  @GET("/todoList/todo")
  fun getTodoList(): Call<List<TodoListDTO>>

  //  완료된 투두 리스트
  @GET("/todoList/done")
  fun getDoneList(): Call<List<TodoListDTO>>

}