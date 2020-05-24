package fr.society.template.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Iterator;
import java.util.List;

@NoRepositoryBean
public interface IRepository {

    default <T> JPQLQuery<T> applyPagination(JPQLQuery<T> query, Pageable pageable) {
        if (pageable == null) {
            return query;
        }
        OrderSpecifier orderSpecifier = getOrderSpecifier(pageable);
        if(orderSpecifier != null){
            return query
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(orderSpecifier);
        }else{
            return query
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
        }

    }

    default OrderSpecifier getOrderSpecifier(Pageable pageable) {
        Iterator<Sort.Order> iterator = pageable.getSort().iterator();
        String property = iterator.hasNext() ? iterator.next().getProperty() : null;
        OrderSpecifier orderSpecifier = null;
        if(property != null){
            orderSpecifier = new OrderSpecifier(Order.valueOf(property),Expressions.asString(property));
        }
        return orderSpecifier;
    }

    default <T> Page<T> getPage(JPQLQuery<T> query, Pageable pageable) {
        List<T> resultList = applyPagination(query,pageable).fetch();
        Long totalCount = query.fetchCount();
        return new PageImpl(resultList,pageable, totalCount);
    }
}
