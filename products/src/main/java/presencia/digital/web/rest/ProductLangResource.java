package presencia.digital.web.rest;

import com.codahale.metrics.annotation.Timed;
import presencia.digital.domain.ProductLang;

import presencia.digital.repository.ProductLangRepository;
import presencia.digital.web.rest.errors.BadRequestAlertException;
import presencia.digital.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProductLang.
 */
@RestController
@RequestMapping("/api")
public class ProductLangResource {

    private final Logger log = LoggerFactory.getLogger(ProductLangResource.class);

    private static final String ENTITY_NAME = "productLang";

    private final ProductLangRepository productLangRepository;

    public ProductLangResource(ProductLangRepository productLangRepository) {
        this.productLangRepository = productLangRepository;
    }

    /**
     * POST  /product-langs : Create a new productLang.
     *
     * @param productLang the productLang to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productLang, or with status 400 (Bad Request) if the productLang has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-langs")
    @Timed
    public ResponseEntity<ProductLang> createProductLang(@RequestBody ProductLang productLang) throws URISyntaxException {
        log.debug("REST request to save ProductLang : {}", productLang);
        if (productLang.getId() != null) {
            throw new BadRequestAlertException("A new productLang cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductLang result = productLangRepository.save(productLang);
        return ResponseEntity.created(new URI("/api/product-langs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-langs : Updates an existing productLang.
     *
     * @param productLang the productLang to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productLang,
     * or with status 400 (Bad Request) if the productLang is not valid,
     * or with status 500 (Internal Server Error) if the productLang couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-langs")
    @Timed
    public ResponseEntity<ProductLang> updateProductLang(@RequestBody ProductLang productLang) throws URISyntaxException {
        log.debug("REST request to update ProductLang : {}", productLang);
        if (productLang.getId() == null) {
            return createProductLang(productLang);
        }
        ProductLang result = productLangRepository.save(productLang);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productLang.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-langs : get all the productLangs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of productLangs in body
     */
    @GetMapping("/product-langs")
    @Timed
    public List<ProductLang> getAllProductLangs() {
        log.debug("REST request to get all ProductLangs");
        return productLangRepository.findAll();
        }

    /**
     * GET  /product-langs/:id : get the "id" productLang.
     *
     * @param id the id of the productLang to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productLang, or with status 404 (Not Found)
     */
    @GetMapping("/product-langs/{id}")
    @Timed
    public ResponseEntity<ProductLang> getProductLang(@PathVariable String id) {
        log.debug("REST request to get ProductLang : {}", id);
        ProductLang productLang = productLangRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productLang));
    }

    /**
     * DELETE  /product-langs/:id : delete the "id" productLang.
     *
     * @param id the id of the productLang to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-langs/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductLang(@PathVariable String id) {
        log.debug("REST request to delete ProductLang : {}", id);
        productLangRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
