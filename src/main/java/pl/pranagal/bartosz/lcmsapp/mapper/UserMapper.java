package pl.pranagal.bartosz.lcmsapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.pranagal.bartosz.lcmsapp.model.dao.UserEntity;
import pl.pranagal.bartosz.lcmsapp.model.dto.UserRequest;
import pl.pranagal.bartosz.lcmsapp.model.dto.UserResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {
    UserEntity dtoToEntity(UserRequest userRequest); // to jest jak dostaje z frontu i mapuje do bazy
    UserResponse entityToResponse(UserEntity userEntity); // to jest jak cos wysylam na front
}
