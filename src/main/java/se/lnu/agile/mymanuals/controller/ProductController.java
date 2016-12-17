package se.lnu.agile.mymanuals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.dto.category.CategoryCreateDto;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.product.ProductCreateDto;
import se.lnu.agile.mymanuals.dto.product.ProductDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;
import se.lnu.agile.mymanuals.dto.subscription.SubscriptionDto;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by ilyakruikov on 11/11/16.
 */
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public interface ProductController {

    @RequestMapping(value = "/category", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto);

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    List<CategoryDto> listCategories();

    @RequestMapping(value="/product", method=RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.CREATED)
    void createProduct(@Valid ProductCreateDto productCreateDto, @AuthenticationPrincipal Principal principal);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    List<ProductListDto> listProducts(@RequestParam(value="categories", required = false) List<Long> categories,
                                      @RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "count", required = false) Integer count);

    @RequestMapping(value = "/products/search", method= RequestMethod.GET)
    List<ProductListDto> searchProducts(@RequestParam(value="query") String query,
                                        @RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "count", required = false) Integer count);

    @RequestMapping(value="/products/favourites", method=RequestMethod.POST)
    void addToFavourites(@RequestParam(value ="productId") Long productId, @AuthenticationPrincipal Principal principal);

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    ProductDto getProduct(@RequestParam(value = "productId") Long productId);

    @RequestMapping(value = "/consumer/products", method = RequestMethod.GET)
    List<ProductListDto> listConsumerProducts(@AuthenticationPrincipal Principal principal);

    @RequestMapping(value = "/company/products", method = RequestMethod.GET)
    List<ProductListDto> listCompanyProducts(@AuthenticationPrincipal Principal principal);

    @RequestMapping(value = "/manual/{manualId}", method = RequestMethod.GET)
    void getManual(@PathVariable("manualId") Long manualId, HttpServletResponse response) throws IOException;

    @RequestMapping(value = "product/{productId}/subscribe/{subscriptionId}", method = RequestMethod.POST)
    void subscribe(@PathVariable("productId") Long productId, @PathVariable("subscriptionId") Long subscriptionId,
                   @AuthenticationPrincipal Principal principal);

    @RequestMapping(value = "product/{productId}/subscribe/{subscriptionId}", method = RequestMethod.DELETE)
    void unsubscribe(@PathVariable("productId") Long productId, @PathVariable("subscriptionId") Long subscriptionId,
                     @AuthenticationPrincipal Principal principal);

    @RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
    List<SubscriptionDto> listSubscriptions();

    @RequestMapping(value = "/consumer/product/{productId}/subscriptions", method = RequestMethod.GET)
    List<Long> listConsumerSubscriptions(@PathVariable("productId") Long productId,
                                         @AuthenticationPrincipal Principal principal);

}