package bitc.example.app.dto

import com.google.gson.annotations.SerializedName

data class TodoListDTO(
  @SerializedName("todoSeq")
  var todoSeq: Int? = null,
  @SerializedName("todoTitle")
  var todoTitle: String? = null,
  @SerializedName("todoStatus")
  var todoStatus: String? = null,
  @SerializedName("todoMemo")
  var todoMemo: String? = null
)
