package com.yoi.application.mapper;

import com.yoi.application.model.DeliverDto;
import com.yoi.application.persistence.dao.DeliverDAO;

import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description DeliveryMapper.java
 */

public class DeliverMapper {

    public static DeliverDto toDto(DeliverDAO entity){ if (entity == null){ return null; }
        return new DeliverDto(
                entity.getId(),
                UserMapper.toDto(entity.getUser()),
                ProductMapper.toDtoList(entity.getProduct()),
                entity.getTotal(),
                entity.getTaxes(),
                entity.getDiscount(),
                entity.getDate()
        );
    }

    public static List<DeliverDto> toDtoList(List<DeliverDAO> entities){ if (entities == null){ return null; }
        return entities.stream()
                .map(DeliverMapper::toDto)
                .toList();
    }

    public static DeliverDAO toEntity(DeliverDto dto){ if (dto == null){ return null; }
        return new DeliverDAO(
                dto.getId(),
                UserMapper.toEntity(dto.getUser()),
                ProductMapper.toEntityList(dto.getProduct()),
                dto.getTotal(),
                dto.getTaxes(),
                dto.getDiscount(),
                dto.getDate()
        );
    }

    public static List<DeliverDAO> toEntityList(List<DeliverDto> dtos){ if (dtos == null){ return null; }
        return dtos.stream()
                .map(DeliverMapper::toEntity)
                .toList();
    }
}
