package by.koronatech.office.core.mapper;

import org.mapstruct.*;
import java.util.List;

@MapperConfig(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)

public interface BaseMapper<E, D> {
    D toDto(E e);

    E toEntity(D d);

    List<D> toDtos(Iterable<E> list);

    List<E> toEntities(Iterable<D> list);

    void merge(@MappingTarget E entity, D dto);

}
