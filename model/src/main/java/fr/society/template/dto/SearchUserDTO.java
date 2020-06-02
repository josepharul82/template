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
public class SearchUserDTO extends AbstractDTO{
    UserDTO userDTO;
    PageParamDTO pageParamDTO;
}
