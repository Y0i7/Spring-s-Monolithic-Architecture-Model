package com.yoi.application.Service;

import com.yoi.application.Model.Deliver;

import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description DeliverService interface for delivery-related operations.
 */
public interface DeliverService {
    List<Deliver> getAllDelivers();
    Deliver getDeliverById(Long id);
    Deliver saveDeliver(Deliver deliver);
    Deliver updateDeliver(Long id, Deliver deliver);
    Deliver deleteDeliver(Long ide);
}
