package com.yoi.application.mapper;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description This class is used to map between Product and ProductDAO objects.
 */

import com.yoi.application.model.ProductDto;
import com.yoi.application.persistence.dao.ProductDAO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDto toDto(ProductDAO entity){ if (entity == null){ return null; }
    ProductDto dto = new ProductDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPrice(entity.getPrice());
    return dto;
    }

    public static List<ProductDto> toDtoList(List<ProductDAO> entities){ if (entities == null){ return null; }
        return entities.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public static ProductDAO toEntity(ProductDto dto){ if (dto == null){ return null;}
        ProductDAO entity = new ProductDAO();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public static List<ProductDAO> toEntityList(List<ProductDto> dtos){ if (dtos == null){ return null; }
        return dtos.stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList());
    }

}
