package com.yoi.application.service;

import com.yoi.application.model.DeliverDto;

import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description DeliverService interface for delivery-related operations.
 */
public interface DeliverService {
    List<DeliverDto> getAllDelivers();
    DeliverDto getDeliverById(Long id);
    DeliverDto saveDeliver(DeliverDto deliverDto);
    DeliverDto updateDeliver(Long id, DeliverDto deliverDto);
    DeliverDto deleteDeliver(Long ide);
}
