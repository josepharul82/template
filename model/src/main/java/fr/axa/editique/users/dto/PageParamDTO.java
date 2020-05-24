package fr.axa.editique.users.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PROTECTED)
public class PageParamDTO {
    Integer page;
    Integer size;
    SortField sortFiled;
}
