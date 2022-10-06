package pl.pranagal.bartosz.lcmsapp.service.converters;

import pl.pranagal.bartosz.lcmsapp.model.dao.UserEntity;
import pl.pranagal.bartosz.lcmsapp.model.dto.UserResponse;

//Why mark this as service and not component?
public final class UserConverter {
    //Anyway, it's bad practice. Checkout mapstruct, it'll do most of the job for you
    public static UserResponse convertUserEntityToDto(UserEntity entity){
        return UserResponse.builder()
                .name(entity.getName())
                .surname(entity.getSurname())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .mail(entity.getMail())
                .id(entity.getId())
                .build();
    }
}
