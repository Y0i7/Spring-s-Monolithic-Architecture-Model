package com.yoi.application.service;

import com.yoi.application.controller.ProductController;
import com.yoi.application.model.ProductDto;
import com.yoi.application.service.impl.ProductServiceDBImpl;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    private static final String PRODUCTS_ENDPOINT_URL = "/products";
    private static final String GET_ALL_PRODUCTS_VIEW = "products/list";
    private static final String CREATE_PRODUCT_URL = "products/creat";
    private static final String PRODUCTS_MODEL_ATTRIBUTE = "products";
    private static final String PRODUCT_MODEL_ATTRIBUTE = "products";


    @Mock
    private ProductServiceDBImpl productServiceDB;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void berforeAll() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();
    }

    @Test
    @DisplayName("PROD_GET_ALL:shouldGetAllProductsAndReturnViewModel")
    void shouldGetAllProductsAndReturnViewModel() throws Exception {
        //Given
        ProductDto product1 = new ProductDto(1L, "Ice Cream", 25.3);
        ProductDto product2 = new ProductDto(2L, "Can of Beans", 50.3);
        ProductDto product3 = new ProductDto(3L, "Pizza", 10.3);

        //When
        when(productServiceDB.getAllProducts()).thenReturn(List.of(product1, product2, product3));
        var resultActions = mockMvc.perform(MockMvcRequestBuilders.get(PRODUCTS_ENDPOINT_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(GET_ALL_PRODUCTS_VIEW))
                .andExpect(model().attributeExists(PRODUCT_MODEL_ATTRIBUTE))
                .andExpect(model().attribute(
                        CREATE_PRODUCT_URL,
                        List.of(product1, product2, product3)));

        //Then
        MvcResult mvcResult = resultActions.andReturn();
        ModelAndView mav = mvcResult.getModelAndView();
        assertNotNull(mav, "Debe devolver un ModelAndView válido");
        assertEquals("Nombre de la vista correcto", GET_ALL_PRODUCTS_VIEW, mav.getViewName());

        @SuppressWarnings("unchecked")
        List<ProductDto> productsModel =
                (List<ProductDto>) mav.getModel().get(PRODUCTS_MODEL_ATTRIBUTE);

        assertNotNull(productsModel, "El atributo 'products' no debe ser nulo");
        assertAll("Validar lista de productos en el modelo",
                () -> assertEquals("Debe haber 2 Productos", 3, productsModel.size()),
                () -> assertEquals("Primer producto coincide", product1, productsModel.get(0)),
                () -> assertEquals("Segundo producto coincide", product2, productsModel.get(1)),
                () -> assertEquals("Tercer producto coincide", product3, productsModel.get(2))
        );

    }

    @Test
    @DisplayName("PROD_CREATE_ERROR:shouldReturnBadRequestAndError400")
    void shouldReturnBadRequestAndError400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(PRODUCTS_ENDPOINT_URL)
                        .param("nombre", "")
                        .param("price", "0"))
                .andExpect(status().isBadRequest());
    }
}
