package fr.society.template.repository.users;

import com.querydsl.jpa.JPQLQuery;
import fr.society.template.dto.SearchUserDTO;
import fr.society.template.model.QRole;
import fr.society.template.model.QUser;
import fr.society.template.model.User;
import fr.society.template.repository.util.RepositoryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;


public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Page<User> findByCriteria(SearchUserDTO searchUserDTO) {
        QUser user =  QUser.user;
        QRole role = QRole.role;

        JPQLQuery<User> jpqlQuery = from(user)
                                    .innerJoin(user.roleList, QRole.role)
                                    .where(user.firstName.equalsIgnoreCase(searchUserDTO.getUserDTO().getFirstName())
                                            .and(user.lastName.equalsIgnoreCase(searchUserDTO.getUserDTO().getLastName()))
                                            .and(role.in(new ArrayList(searchUserDTO.getUserDTO().getRoleList()))));
        return getPage(jpqlQuery , RepositoryUtil.getPageRequest(searchUserDTO.getPageParamDTO()));
    }
}

