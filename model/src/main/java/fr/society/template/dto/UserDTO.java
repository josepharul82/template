package fr.society.template.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level=AccessLevel.PROTECTED)
public class UserDTO extends AbstractDTO{

    Long id;
    Long version;
    String firstName;
    String lastName;
    String mail;

    @Singular("addRole")
    Set<RoleDTO> roleList;
}
