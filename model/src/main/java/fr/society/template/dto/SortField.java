package fr.society.template.dto;

import fr.society.template.constante.Field;
import fr.society.template.constante.OrderSQL;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PROTECTED)
public class SortField {
    Field field;
    OrderSQL order;
}
