package fr.society.template.repository.users;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import fr.society.template.dto.SearchUserDTO;
import fr.society.template.dto.UserDTO;
import fr.society.template.model.QUser;
import fr.society.template.model.User;
import fr.society.template.repository.util.RepositoryUtil;
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

