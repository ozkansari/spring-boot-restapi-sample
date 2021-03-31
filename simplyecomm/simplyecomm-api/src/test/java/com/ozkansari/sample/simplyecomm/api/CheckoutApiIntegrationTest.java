package com.ozkansari.sample.simplyecomm.api;

import static org.mockito.ArgumentMatchers.anySet;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ozkansari.sample.simplyecomm.db.DiscountDAO;
import com.ozkansari.sample.simplyecomm.db.model.dto.ProductDiscountDTO;
import com.ozkansari.sample.simplyecomm.testhelper.CommonTestBase;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
class CheckoutApiIntegrationTest extends CommonTestBase {

    @Autowired private MockMvc mockMvc;
    @InjectMocks private CheckoutApi checkoutApi;
    @MockBean private DiscountDAO discountDAO;
    
    @Test
    @DisplayName("Test Perform Checkout APi Call With Empty Input -> Should Get Exception ")
    final void testPerformCheckoutWithEmptyInput() throws Exception {

        // When
        String postRequestContent = "[]";
        ResultActions restApiCall = this.mockMvc
                .perform(
                    post(CheckoutApi.API_CHECKOUT_BASE_URL + "/")
                    .content(postRequestContent)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                );
        
        restApiCall.andDo(print()).andExpect(status().isBadRequest());

    }
    
    @Test
    @DisplayName("Test Perform Checkout APi Call -> Should Get ")
    final void testPerformCheckout() throws Exception {
        
        // Given
        List<ProductDiscountDTO> productsAndDiscounts = createDefaultProductDiscountDTO();
        Mockito
            .when(discountDAO.findAllProductAndDiscountBySerialNos(anySet()))
            .thenReturn(productsAndDiscounts);
        String postRequestContent = "[" + 
                "  \"001\"," + 
                "  \"002\","
                + "\"002\"," + 
                "  \"001\","
                + "\"001\","
                + "\"001\"," + 
                "  \"004\"," + 
                "  \"003\"," +
                "  \"\"," +
                "  \" \"" + 
                "]";
        
        // When
        ResultActions restApiCall = this.mockMvc
                .perform(
                    post(CheckoutApi.API_CHECKOUT_BASE_URL + "/")
                    .content(postRequestContent)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                );
        
        // Then
        restApiCall
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("price").value(500))
            .andExpect(
                content()
                .json(
                    "  {\n" + 
                    "    \"price\": 500\n" + 
                    "  }\n"
                )
            );
        
            // Document
            restApiCall.andDo(
                document(
                        "{class-name}/{method-name}",
                        preprocessRequest(removeHeaders("Authorization")), 
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("price")
                                .description("Checkout total price")
                                .type(JsonFieldType.NUMBER)
                        )
                )
            );
    }

}
