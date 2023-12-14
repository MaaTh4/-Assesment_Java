package JavaProject.At_java.Exceptions;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ResponsePayload {

    String message;
    LocalDateTime dateTime;

    public ResponsePayload(String message){
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

}
