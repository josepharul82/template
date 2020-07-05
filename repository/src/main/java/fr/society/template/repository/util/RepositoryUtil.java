package fr.society.template.repository.util;

import fr.society.template.constante.OrderSQL;
import fr.society.template.dto.PageParamDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class RepositoryUtil {
    public static final int DEFAULT_SIZE = 10;
    public static final int DEFAULT_PAGE = 0;

    public static Pageable getPageRequest(PageParamDTO pageParamDTO) {
        Pageable defaultPageable = PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE);
        if(pageParamDTO != null){
            OrderSQL orderSQL = getDirection(pageParamDTO.getSortFiled().getOrder());
            defaultPageable = PageRequest.of(pageParamDTO.getPage(),
                    pageParamDTO.getSize(),
                    Sort.Direction.valueOf(orderSQL.name()),
                    pageParamDTO.getSortFiled().getField().getValue());
        }
        return defaultPageable;
    }

    public static OrderSQL getDirection(OrderSQL orderSQL) {
        OrderSQL orderSQLToApply = null;
        if(orderSQL != null) {
            orderSQLToApply =  OrderSQL.ASC;
            if (orderSQL == OrderSQL.DESC) {
                orderSQLToApply = OrderSQL.DESC;
            }
        }
        return orderSQLToApply;
    }
}
