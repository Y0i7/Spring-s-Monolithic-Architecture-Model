package com.yoi.application.Service.Impl;

import com.yoi.application.Mapper.DeliverMapper;
import com.yoi.application.Mapper.ProductMapper;
import com.yoi.application.Mapper.UserMapper;
import com.yoi.application.Model.Deliver;
import com.yoi.application.Persistence.DAO.DeliverDAO;
import com.yoi.application.Persistence.Repository.DeliverRepository;
import com.yoi.application.Service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description DeliverServiceDBImpl class for delivery-related operations.
 */

@Service
public class DeliverServiceDBImpl implements DeliverService {

    @Autowired // Constructor-based dependency injection
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
    public List<Deliver> getAllDelivers() {
        List<DeliverDAO> entities = deliverRepository.findAll();
        return DeliverMapper.toDtoList(entities);
    }

    /*
     * @description This method retrieves a delivery by its ID from the database.
     * @param id - The ID of the delivery to retrieve.
     * @return Deliver - The Deliver object with the specified ID, or null if not found.
     */
    @Override
    public Deliver getDeliverById(Long id) {
        Optional<DeliverDAO> optionalEntity = deliverRepository.findById(id);
        if (optionalEntity.isPresent()){
            return optionalEntity
                    .map(DeliverMapper::toDto)
                    .orElse(null);
        }
        return null;
    }

    /*
     * @description This method saves a new delivery to the database.
     * @param deliver - The Deliver object to save.
     * @return Deliver - The saved Deliver object.
     */
    @Override
    public Deliver saveDeliver(Deliver deliver) {
        DeliverDAO optionalEntity = DeliverMapper.toEntity(deliver);
        return DeliverMapper.toDto(deliverRepository.save(optionalEntity));
    }

    /*
     * @description This method updates an existing delivery in the database.
     * @param id - The ID of the delivery to update.
     * @param deliver - The Deliver object with updated information.
     * @return Deliver - The updated Deliver object, or null if not found.
     */
    @Override
    public Deliver updateDeliver(Long id, Deliver deliver) {
        Optional<DeliverDAO> optionalEntity = deliverRepository.findById(id);
        if (optionalEntity.isPresent()){
            DeliverDAO entity = optionalEntity.get();
            entity.setUser(UserMapper.toEntity(deliver.getUser()));
            entity.setProduct(ProductMapper.toEntity(deliver.getProduct()));
            entity.setDate(deliver.getDate());
            return DeliverMapper.toDto(deliverRepository.save(entity));
        }
        return null;
    }

    /*
     * @description This method deletes a delivery by its ID from the database.
     * @param id - The ID of the delivery to delete.
     * @return Deliver - The deleted Deliver object, or null if not found.
     */
    @Override
    public Deliver deleteDeliver(Long id) {
        Optional<DeliverDAO> optionalEntity = deliverRepository.findById(id);
        if (optionalEntity.isPresent()) {
            deliverRepository.deleteById(id);
            return optionalEntity
                    .map(DeliverMapper::toDto)
                    .orElse(null);
        }
        return null;
    }
}
