package com.yoi.application.service.impl;

import com.yoi.application.mapper.DeliverMapper;
import com.yoi.application.mapper.ProductMapper;
import com.yoi.application.mapper.UserMapper;
import com.yoi.application.model.DeliverDto;
import com.yoi.application.persistence.dao.DeliverDAO;
import com.yoi.application.persistence.dao.ProductDAO;
import com.yoi.application.persistence.repository.DeliverRepository;
import com.yoi.application.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description DeliverServiceDBImpl class for delivery-related operations.
 */

@Service
public class DeliverServiceDBImpl implements DeliverService {

    @Autowired
    private final DeliverRepository deliverRepository;

    // This class implements the DeliverService interface for database operations.
    public DeliverServiceDBImpl(DeliverRepository deliverRepository) {
        this.deliverRepository = deliverRepository;
    }

    /*
     * @description This method retrieves all deliveries from the database.
     * @return List<Deliver> - A list of Deliver objects.
     */
    @Override
    public List<DeliverDto> getAllDelivers() {
        return deliverRepository.findAll().stream()
                .map(DeliverMapper::toDto)
                .collect(Collectors.toList());
    }

    /*
     * @description This method retrieves a delivery by its ID from the database.
     * @param id - The ID of the delivery to retrieve.
     * @return Deliver - The Deliver object with the specified ID, or null if not found.
     */
    @Override
    public DeliverDto getDeliverById(Long id) {
        return deliverRepository.findById(id)
                .map(DeliverMapper::toDto)
                .orElse(null);
    }

    /*
     * @description This method saves a new delivery to the database.
     * @param deliver - The Deliver object to save.
     * @return Deliver - The saved Deliver object.
     */
    @Override
    public DeliverDto saveDeliver(DeliverDto deliverDto) {
        calculateTotals(deliverDto);
        DeliverDAO optionalEntity = DeliverMapper.toEntity(deliverDto);
        return DeliverMapper.toDto(deliverRepository.save(optionalEntity));
    }

    /*
     * @description This method updates an existing delivery in the database.
     * @param id - The ID of the delivery to update.
     * @param deliver - The Deliver object with updated information.
     * @return Deliver - The updated Deliver object, or null if not found.
     */
    @Override
    public DeliverDto updateDeliver(Long id, DeliverDto deliverDto) {
        return deliverRepository.findById(id)
                .map((entity) -> {
                    entity.setUser(UserMapper.toEntity(deliverDto.getUser()));
                    entity.setProduct(ProductMapper.toEntityList(deliverDto.getProduct()));
                    entity.setDate(deliverDto.getDate());

                    //Calculate totals again
                    DeliverDto tempDeliverDto = new DeliverDto();
                    tempDeliverDto.setProduct(deliverDto.getProduct());
                    calculateTotals(tempDeliverDto);

                    entity.setTotal(tempDeliverDto.getTotal());
                    entity.setTaxes(tempDeliverDto.getTaxes());
                    entity.setDiscount(tempDeliverDto.getDiscount());

                    return DeliverMapper.toDto(deliverRepository.save(entity));
                })
                .orElse(null);
    }

    /*
     * @description This method deletes a delivery by its ID from the database.
     * @param id - The ID of the delivery to delete.
     * @return Deliver - The deleted Deliver object, or null if not found.
     */
    @Override
    public DeliverDto deleteDeliver(Long id) {
        return deliverRepository.findById(id)
                                .map((entity) -> {
                                    deliverRepository.deleteById(id);
                                    return DeliverMapper.toDto(entity);
                                })
                                .orElse(null);
    }

    /*
     * @description This method calculates the total, taxes, and discount for a delivery.
     * @param deliver - The Deliver object to calculate totals for.
     */
    private void calculateTotals(DeliverDto deliverDto) {
        List<ProductDAO> products = ProductMapper.toEntityList(deliverDto.getProduct());
        double subtotal = products.stream()
                .mapToDouble(ProductDAO::getPrice)
                .sum();
        double discout = products.size() > 5 ? subtotal * 0.10 : 0.0;
        double taxes = (subtotal -discout) * 0.15;
        double total = subtotal - discout + taxes;

        deliverDto.setDiscount(discout);
        deliverDto.setTaxes(taxes);
        deliverDto.setTotal(total);
    }
}
