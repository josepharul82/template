package fr.society.template.dto;

import fr.society.template.constante.Field;
import fr.society.template.constante.OrderSQL;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level=AccessLevel.PROTECTED)
public class SortField extends AbstractDTO{
    Field field;
    OrderSQL order;
}
