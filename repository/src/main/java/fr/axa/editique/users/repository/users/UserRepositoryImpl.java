package fr.axa.editique.users.repository.users;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import fr.axa.editique.users.dto.SearchUserDTO;
import fr.axa.editique.users.dto.UserDTO;
import fr.axa.editique.users.model.QUser;
import fr.axa.editique.users.model.User;
import fr.axa.editique.users.repository.util.RepositoryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Page<User> findByCriteria(SearchUserDTO searchUserDTO) {
        QUser users =  QUser.user;
        JPQLQuery<User> jpqlQuery = from(users);
        UserBuilderQuery userComposeQuery = getUserComposerQuery(searchUserDTO.getUserDTO(),jpqlQuery);
        jpqlQuery = userComposeQuery.innerJoinRole().getJpqlQuery();
        BooleanBuilder booleanBuilder = userComposeQuery.andFirstName().andLastName().andRole().getBooleanBuilder();
        jpqlQuery.where(booleanBuilder);
        return getPage(jpqlQuery ,RepositoryUtil.getPageRequest(searchUserDTO.getPageParamDTO()));
    }

    private UserBuilderQuery getUserComposerQuery(UserDTO userDTO, JPQLQuery<User> jpqlQuery) {
        return UserBuilderQuery.builder().userDTO(userDTO).jpqlQuery(jpqlQuery).booleanBuilder(new BooleanBuilder()).build();
    }

}

