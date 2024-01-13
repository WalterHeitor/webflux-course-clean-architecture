package br.com.softwalter.webfluxcourse.data.mongodb.mapper;

import br.com.softwalter.webfluxcourse.core.domain.User;
import br.com.softwalter.webfluxcourse.data.mongodb.entity.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapperData {

    @Mapping(target = "id", ignore = true)
    UserData toEntity(final User user);
    User toUser(final UserData userData);
}
