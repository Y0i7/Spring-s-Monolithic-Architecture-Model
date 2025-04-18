package com.yoi.application.Mapper;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description This class is used to map between Product and ProductDAO objects.
 */

import com.yoi.application.Model.Product;
import com.yoi.application.Persistence.DAO.ProductDAO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toDto(ProductDAO entity){ if (entity == null){ return null; }
    Product dto = new Product();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPrice(entity.getPrice());
    return dto;
    }

    public static List<Product> toDtoList(List<ProductDAO> entities){ if (entities == null){ return null; }
        return entities.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public static ProductDAO toEntity(Product dto){ if (dto == null){ return null;}
        ProductDAO entity = new ProductDAO();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public static List<ProductDAO> toEntityList(List<Product> dtos){ if (dtos == null){ return null; }
        return dtos.stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList());
    }

}
