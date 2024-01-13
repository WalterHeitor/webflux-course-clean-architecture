package br.com.softwalter.webfluxcourse.presenter.controller.mapper;

import br.com.softwalter.webfluxcourse.core.usecase.user.CreateUserUseCase;
import br.com.softwalter.webfluxcourse.core.usecase.user.GetUserUseCase;
import br.com.softwalter.webfluxcourse.core.usecase.user.UpdateUserUseCase;
import br.com.softwalter.webfluxcourse.data.mongodb.entity.UserData;
import br.com.softwalter.webfluxcourse.presenter.controller.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserData toEntity(final UserRequest request);

    @Mapping(target = "id", ignore = true)
    UserData toEntity(final UserRequest request, @MappingTarget final UserData entity);


    CreateUserUseCase.InputValues toInputValues(UserRequest request);
    GetUserUseCase.InputValues toInputValues(String id);

    UpdateUserUseCase.InputValues toInputValues(String id, UserRequest request);

//    GetUserUseCase.InputValues toInputValues(String id);

    
}

