package fr.society.template.repository.users;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import fr.society.template.dto.UserDTO;
import fr.society.template.model.QRole;
import fr.society.template.model.QUser;
import fr.society.template.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Collection;


@Getter
@Builder
@FieldDefaults(level=AccessLevel.PRIVATE) // passe tous les champs en private
public class UserBuilderQuery {

    UserDTO userDTO;
    JPQLQuery<User> jpqlQuery;
    BooleanBuilder booleanBuilder;

    public UserBuilderQuery andFirstName() {
        if (userDTO.getFirstName() != null) {
            booleanBuilder.and(QUser.user.firstName.equalsIgnoreCase(userDTO.getFirstName()));
        }
        return this;
    }

    public UserBuilderQuery andLastName() {
        if (userDTO.getLastName() != null) {
            booleanBuilder.and(QUser.user.lastName.equalsIgnoreCase(userDTO.getLastName()));
        }
        return this;
    }

    public UserBuilderQuery andRole() {
        if (userDTO.getRoleList() != null) {
            booleanBuilder.and(QRole.role.in((Collection) userDTO.getRoleList()));
        }
        return this;
    }

    public UserBuilderQuery innerJoinRole() {
        if (userDTO.getRoleList() != null) {
            jpqlQuery.innerJoin(QUser.user.roleList,QRole.role);
        }
        return this;
    }


}
