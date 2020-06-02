package fr.society.template.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PROTECTED)
public class PageParamDTO extends AbstractDTO{
    Integer page;
    Integer size;
    SortField sortFiled;
}
