package se.lnu.agile.mymanuals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.dto.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ilyakruikov on 11/11/16.
 */
public interface ProductController {

    @RequestMapping(value = "/category", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createCategory(@RequestBody @Valid CategorySignUpDto categorySignUpDto);

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    List<CategoryDto> listCategories();

    @RequestMapping(value="/product", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value= HttpStatus.CREATED)
    void createProduct(@RequestBody ProductDto productDto);

}