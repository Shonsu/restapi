package pl.shonsu.clientserver.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.shonsu.clientserver.dtos.PersonDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonDtoPageableResponse extends PageImpl<PersonDto> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PersonDtoPageableResponse(@JsonProperty("content") List<PersonDto> content,
                          @JsonProperty("number") int number,
                          @JsonProperty("size") int size,
                          @JsonProperty("totalElements") Long totalElements,
                          @JsonProperty("pageable") JsonNode pageable,
                          @JsonProperty("last") boolean last,
                          @JsonProperty("totalPages") int totalPages,
                          @JsonProperty("sort") JsonNode sort,
                          @JsonProperty("first") boolean first,
                          @JsonProperty("numberOfElements") int numberOfElements){
        super(content, PageRequest.of(number, size) , totalElements);
    }
    public PersonDtoPageableResponse(List<PersonDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PersonDtoPageableResponse(List<PersonDto> content) {
        super(content);
    }

    public PersonDtoPageableResponse() {
        super(new ArrayList<>());
    }
}
