package com.yoi.application.Mapper;

import com.yoi.application.Model.User;
import com.yoi.application.Persistence.DAO.UserDAO;

import java.util.List;
import java.util.stream.Collectors;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description This class is used to map between User and UserDAO objects.
 */

public class UserMapper {

    /*
     * This class is used to map between User and UserDAO objects.
     * It is used to convert between the DTO and DAO layers.
     * It is also used to convert between the entity and DTO layers.
     */
    public static User toDto(UserDAO entity){ if (entity == null){ return  null; }
        User dto = new User();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public static List<User> toDtoList(List<UserDAO> entities){ if (entities == null){ return null;}
        return entities.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    /*
     * This method is used to convert a User object to a UserDAO object.
     * It is used to convert between the DTO and DAO layers.
     * It is also used to convert between the entity and DTO layers.
     */
    public static UserDAO toEntity(User dto){ if (dto == null){ return null;}
        UserDAO entity = new UserDAO();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return entity;
    }
    public static List<UserDAO> toEntityList(List<User> dtos){if (dtos == null){ return null;}
        return dtos.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
    }


}
