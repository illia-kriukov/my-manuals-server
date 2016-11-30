package se.lnu.agile.mymanuals.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.controller.ProductController;
import se.lnu.agile.mymanuals.dto.category.CategoryCreateDto;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.product.ProductCreateDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;
import se.lnu.agile.mymanuals.error.ValidationError;
import se.lnu.agile.mymanuals.error.ValidationErrorBuilder;
import se.lnu.agile.mymanuals.exception.ProductException;
import se.lnu.agile.mymanuals.exception.RegistrationException;
import se.lnu.agile.mymanuals.service.ProductService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RestController
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    @RequestMapping(value = "/category", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto) {
        productService.createCategory(categoryCreateDto);
    }

    @Override
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<CategoryDto> listCategories() {
        return productService.listCategories();
    }

    @Override
    @RequestMapping(value="/products", method= RequestMethod.GET)
    public List<ProductListDto> listProducts(@RequestParam(value="categories", required = false) List<Long> categories,
                                             @RequestParam(value = "page", required = false) Integer page,
                                             @RequestParam(value = "count", required = false) Integer count) {
        return productService.listProducts(categories, page, count);
    }

    @Override
    @RequestMapping(value="/product", method=RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.CREATED)
    public void createProduct(@Valid ProductCreateDto productCreateDto, @AuthenticationPrincipal Principal principal) {
        productService.createProduct(productCreateDto, principal.getName());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException e) {
        return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(BindException e) {
        return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(RegistrationException e) {
        return ValidationErrorBuilder.fromException(e);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(ProductException e) {
        return ValidationErrorBuilder.fromException(e);
    }

}