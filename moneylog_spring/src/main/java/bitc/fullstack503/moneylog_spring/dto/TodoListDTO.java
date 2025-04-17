package bitc.fullstack503.moneylog_spring.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TodoListDTO
{
  @JsonProperty ("todoSeq")
  private int todoSeq;
  @JsonProperty ("todoTitle")
  private String todoTitle;
  @JsonProperty ("todoStatus")
  private String todoStatus;
  @JsonProperty ("todoMemo")
  private String todoMemo;
}
