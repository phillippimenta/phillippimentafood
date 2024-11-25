package br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.config;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.exception.DomainException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void testBaseException_ShouldReturnBadRequest() {
        // Arrange
        String errorMessage = "Domain exception occurred";
        DomainException domainException = new DomainException(errorMessage);
        // Act
        ResponseEntity<String> response = exceptionHandler.baseException(domainException);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void testHandleValidationErrors_ShouldReturnErrorResponse() {
        // Arrange
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "Field is invalid");
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(
                null,
                bindingResult
        );
        // Act
        ResponseEntity<GlobalExceptionHandler.ErrorResponse> response = exceptionHandler.handleValidationErrors(ex);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErros().size());
        assertEquals("Field is invalid", response.getBody().getErros().get(0));
    }

    @Test
    void testErrorResponseSetErros() {
        // Arrange
        GlobalExceptionHandler.ErrorResponse errorResponse = new GlobalExceptionHandler.ErrorResponse(Collections.emptyList());
        List<String> newErrors = List.of("New error message");
        // Act
        errorResponse.setErros(newErrors);
        // Assert
        assertEquals(newErrors, errorResponse.getErros());
    }

}

