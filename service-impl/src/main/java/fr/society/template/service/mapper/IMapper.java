package fr.society.template.service.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface IMapper<C,D> {
    D entityToDTO(C c);
    List<D> entityToDTO(final List<C> cList);
    C dtoToEntity(D c);
    C dtoToEntity(D d, @MappingTarget C c);
}
