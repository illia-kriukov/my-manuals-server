package se.lnu.agile.mymanuals.service;

import org.springframework.web.bind.annotation.PathVariable;
import se.lnu.agile.mymanuals.dto.annotation.ManualAnnotationDto;
import se.lnu.agile.mymanuals.dto.annotation.VideoAnnotationDto;
import se.lnu.agile.mymanuals.dto.category.CategoryCreateDto;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.comment.CommentDto;
import se.lnu.agile.mymanuals.dto.manual.ManualDto;
import se.lnu.agile.mymanuals.dto.product.ProductCreateDto;
import se.lnu.agile.mymanuals.dto.product.ProductDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;
import se.lnu.agile.mymanuals.dto.product.ProductUpdateDto;
import se.lnu.agile.mymanuals.dto.rating.AvgRatingDto;
import se.lnu.agile.mymanuals.dto.subscription.SubscriptionDto;

import java.util.List;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface ProductService {

    void createCategory(CategoryCreateDto dto);

    List<CategoryDto> listCategories();

    void createProduct(ProductCreateDto dto, String representativeEmail);

    void updateProduct(ProductUpdateDto productUpdateDto, String representativeEmail);

    List<ProductListDto> listProducts(List<Long> categoryIds, Integer page, Integer count, String consumerEmail);

    List<ProductListDto> searchProducts(String query, Integer page, Integer count, String consumerEmail);

    void addToFavourites(Long productId, String consumerEmail);

    ProductDto getProduct(Long productId, String consumerEmail);

    List<ProductListDto> listConsumerProducts(String consumerEmail);

    List<ProductListDto> listCompanyProducts(String representativeEmail);

    ManualDto getManual(Long manualId);

    void subscribe(Long productId, Long subscriptionId, String consumerEmail);

    void unsubscribe(Long productId, Long subscriptionId, String consumerEmail);

    List<SubscriptionDto> listSubscriptions();

    List<Long> listConsumerSubscriptions(Long productId, String consumerEmail);

    void addAnnotationToManual(Long manualId, String consumerEmail, String annotation);

    void addAnnotationToVideo(Long videoId, String consumerEmail, String annotation);

    List<ManualAnnotationDto> listAnnotationsForManual(Long manualId, String consumerEmail);

    List<VideoAnnotationDto> listAnnotationsForVideo(Long videoId, String consumerEmail);

    void addCommentToProduct(Long productId, String userEmail, String comment);

    List<CommentDto> listCommentsForProduct(Long productId, Integer page, Integer count);

    void createRatingForManual(Long manualId, String consumerEmail, int rating);

    void createRatingForVideo(Long videoId, String consumerEmail, int rating);

    void updateRatingForManual(Long manualId, String consumerEmail, int rating);

    void updateRatingForVideo(Long videoId, String consumerEmail, int rating);

    Integer getMyRatingForManual(Long manualId, String consumerEmail);

    Integer getMyRatingForVideo(Long manualId, String consumerEmail);

    AvgRatingDto getAvgRatingForManual(Long manualId);

    AvgRatingDto getAvgRatingForVideo(Long videoId);

}