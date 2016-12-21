package se.lnu.agile.mymanuals.controller.impl;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import se.lnu.agile.mymanuals.controller.ProductController;
import se.lnu.agile.mymanuals.dto.annotation.AnnotationCreateDto;
import se.lnu.agile.mymanuals.dto.annotation.ManualAnnotationDto;
import se.lnu.agile.mymanuals.dto.annotation.VideoAnnotationDto;
import se.lnu.agile.mymanuals.dto.category.CategoryCreateDto;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.comment.CommentCreateDto;
import se.lnu.agile.mymanuals.dto.manual.ManualDto;
import se.lnu.agile.mymanuals.dto.product.ProductCreateDto;
import se.lnu.agile.mymanuals.dto.product.ProductDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;
import se.lnu.agile.mymanuals.dto.subscription.SubscriptionDto;
import se.lnu.agile.mymanuals.error.ValidationError;
import se.lnu.agile.mymanuals.error.ValidationErrorBuilder;
import se.lnu.agile.mymanuals.exception.ProductException;
import se.lnu.agile.mymanuals.exception.RegistrationException;
import se.lnu.agile.mymanuals.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
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
    @RequestMapping(value="/product", method=RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.CREATED)
    public void createProduct(@Valid ProductCreateDto productCreateDto, @AuthenticationPrincipal Principal principal) {
        productService.createProduct(productCreateDto, principal.getName());
    }

    @Override
    @RequestMapping(value="/products", method= RequestMethod.GET)
    public List<ProductListDto> listProducts(@RequestParam(value="categories", required = false) List<Long> categories,
                                             @RequestParam(value = "page", required = false) Integer page,
                                             @RequestParam(value = "count", required = false) Integer count,
                                             @AuthenticationPrincipal Principal principal,
                                             Authentication authentication) {
        return productService.listProducts(categories, page, count, getConsumerEmail(principal, authentication));
    }

    @Override
    @RequestMapping(value="/products/search", method= RequestMethod.GET)
    public List<ProductListDto> searchProducts(@RequestParam(value = "query") String query,
                                               @RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "count", required = false) Integer count,
                                               @AuthenticationPrincipal Principal principal,
                                               Authentication authentication) {
        return productService.searchProducts(query, page, count, getConsumerEmail(principal, authentication));
    }

    @Override
    @RequestMapping(value="/products/favourites", method=RequestMethod.POST)
    public void addToFavourites(@RequestParam(value ="productId") Long productId, @AuthenticationPrincipal Principal principal) {
        productService.addToFavourites(productId, principal.getName());
    }

    @Override
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ProductDto getProduct(@RequestParam(value = "productId") Long productId,
                                 @AuthenticationPrincipal Principal principal,
                                 Authentication authentication) {
        return productService.getProduct(productId, getConsumerEmail(principal, authentication));
    }

    @Override
    @RequestMapping(value = "/consumer/products", method = RequestMethod.GET)
    public List<ProductListDto> listConsumerProducts(@AuthenticationPrincipal Principal principal){
        return productService.listConsumerProducts(principal.getName());
    }

    @Override
    @RequestMapping(value = "/company/products", method = RequestMethod.GET)
    public List<ProductListDto> listCompanyProducts(@AuthenticationPrincipal Principal principal) {
        return productService.listCompanyProducts(principal.getName());
    }

    @Override
    @RequestMapping(value = "/manual/{manualId}", method = RequestMethod.GET)
    public void getManual(@PathVariable("manualId") Long manualId, HttpServletResponse response) {
        ManualDto manual = productService.getManual(manualId);
        if (manual != null) {
            response.addHeader("content-disposition", "inline;filename=" + manual.getName());
            response.setContentType("application/pdf");
            try {
                IOUtils.copy(new ByteArrayInputStream(manual.getFile()), response.getOutputStream());
                response.flushBuffer();
            } catch(IOException e) {
                throw new ProductException("Manual can't be downloaded.");
            }
        }
    }

    @Override
    public void subscribe(@PathVariable("productId") Long productId, @PathVariable("subscriptionId") Long subscriptionId,
                          @AuthenticationPrincipal Principal principal) {
        productService.subscribe(productId, subscriptionId, principal.getName());
    }

    @Override
    public void unsubscribe(@PathVariable("productId") Long productId, @PathVariable("subscriptionId") Long subscriptionId,
                            @AuthenticationPrincipal Principal principal) {
        productService.unsubscribe(productId, subscriptionId, principal.getName());
    }

    @Override
    public List<SubscriptionDto> listSubscriptions() {
        return productService.listSubscriptions();
    }

    @Override
    public List<Long> listConsumerSubscriptions(@PathVariable("productId") Long productId,
                                                @AuthenticationPrincipal Principal principal) {
        return productService.listConsumerSubscriptions(productId, principal.getName());
    }

    @Override
    @RequestMapping(value="/manual/{manualId}/annotation", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAnnotationToManual(@PathVariable("manualId") Long manualId,
                                      @RequestBody @Valid AnnotationCreateDto annotationCreateDto,
                                      @AuthenticationPrincipal Principal principal) {
        productService.addAnnotationToManual(manualId, principal.getName(), annotationCreateDto.getAnnotation());
    }

    @Override
    @RequestMapping(value="/video/{videoId}/annotation", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAnnotationToVideo(@PathVariable("videoId") Long videoId,
                                     @RequestBody @Valid AnnotationCreateDto annotationCreateDto,
                                     @AuthenticationPrincipal Principal principal) {
        productService.addAnnotationToVideo(videoId, principal.getName(), annotationCreateDto.getAnnotation());
    }

    @Override
    @RequestMapping(value="/manual/{manualId}/annotation", method=RequestMethod.GET)
    public List<ManualAnnotationDto> listAnnotationsForManual(@PathVariable("manualId") Long manualId,
                                                              @AuthenticationPrincipal Principal principal) {
        return productService.listAnnotationsForManual(manualId, principal.getName());
    }

    @Override
    @RequestMapping(value="/video/{videoId}/annotation", method=RequestMethod.GET)
    public List<VideoAnnotationDto> listAnnotationsForVideo(@PathVariable("videoId") Long videoId,
                                                            @AuthenticationPrincipal Principal principal) {
        return productService.listAnnotationsForVideo(videoId, principal.getName());
    }

    @Override
    @RequestMapping(value="/product/{id}/comment", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@PathVariable("id") Long productId,
                           @RequestBody @Valid CommentCreateDto commentCreateDto,
                           @AuthenticationPrincipal Principal principal) {
        productService.addComment(productId, /*principal.getName()*/ "test100@test.com" , commentCreateDto.getComment());
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

    private String getConsumerEmail(Principal principal, Authentication authentication) {
        return principal == null || !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")) ?
                null : principal.getName();
    }

}