package fr.axa.editique.users.dto;

import fr.axa.editique.users.constante.Field;
import fr.axa.editique.users.constante.OrderSQL;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PROTECTED)
public class SortField {
    Field field;
    OrderSQL order;
}
