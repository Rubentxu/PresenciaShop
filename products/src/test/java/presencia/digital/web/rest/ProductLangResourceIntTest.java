package presencia.digital.web.rest;

import presencia.digital.ProductsApp;

import presencia.digital.domain.ProductLang;
import presencia.digital.repository.ProductLangRepository;
import presencia.digital.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductLangResource REST controller.
 *
 * @see ProductLangResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductsApp.class)
public class ProductLangResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTIONSHORT = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTIONSHORT = "BBBBBBBBBB";

    private static final String DEFAULT_LINKREWRITE = "AAAAAAAAAA";
    private static final String UPDATED_LINKREWRITE = "BBBBBBBBBB";

    private static final String DEFAULT_METADESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_METADESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_METAKEYWORDS = "AAAAAAAAAA";
    private static final String UPDATED_METAKEYWORDS = "BBBBBBBBBB";

    private static final String DEFAULT_METATITLE = "AAAAAAAAAA";
    private static final String UPDATED_METATITLE = "BBBBBBBBBB";

    private static final String DEFAULT_AVAILABLENOW = "AAAAAAAAAA";
    private static final String UPDATED_AVAILABLENOW = "BBBBBBBBBB";

    private static final String DEFAULT_AVAILABLELATER = "AAAAAAAAAA";
    private static final String UPDATED_AVAILABLELATER = "BBBBBBBBBB";

    private static final String DEFAULT_LANGCODE = "AAAAAAAAAA";
    private static final String UPDATED_LANGCODE = "BBBBBBBBBB";

    @Autowired
    private ProductLangRepository productLangRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProductLangMockMvc;

    private ProductLang productLang;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductLangResource productLangResource = new ProductLangResource(productLangRepository);
        this.restProductLangMockMvc = MockMvcBuilders.standaloneSetup(productLangResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductLang createEntity() {
        ProductLang productLang = new ProductLang()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .descriptionshort(DEFAULT_DESCRIPTIONSHORT)
            .linkrewrite(DEFAULT_LINKREWRITE)
            .metadescription(DEFAULT_METADESCRIPTION)
            .metakeywords(DEFAULT_METAKEYWORDS)
            .metatitle(DEFAULT_METATITLE)
            .availablenow(DEFAULT_AVAILABLENOW)
            .availablelater(DEFAULT_AVAILABLELATER)
            .langcode(DEFAULT_LANGCODE);
        return productLang;
    }

    @Before
    public void initTest() {
        productLangRepository.deleteAll();
        productLang = createEntity();
    }

    @Test
    public void createProductLang() throws Exception {
        int databaseSizeBeforeCreate = productLangRepository.findAll().size();

        // Create the ProductLang
        restProductLangMockMvc.perform(post("/api/product-langs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productLang)))
            .andExpect(status().isCreated());

        // Validate the ProductLang in the database
        List<ProductLang> productLangList = productLangRepository.findAll();
        assertThat(productLangList).hasSize(databaseSizeBeforeCreate + 1);
        ProductLang testProductLang = productLangList.get(productLangList.size() - 1);
        assertThat(testProductLang.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProductLang.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProductLang.getDescriptionshort()).isEqualTo(DEFAULT_DESCRIPTIONSHORT);
        assertThat(testProductLang.getLinkrewrite()).isEqualTo(DEFAULT_LINKREWRITE);
        assertThat(testProductLang.getMetadescription()).isEqualTo(DEFAULT_METADESCRIPTION);
        assertThat(testProductLang.getMetakeywords()).isEqualTo(DEFAULT_METAKEYWORDS);
        assertThat(testProductLang.getMetatitle()).isEqualTo(DEFAULT_METATITLE);
        assertThat(testProductLang.getAvailablenow()).isEqualTo(DEFAULT_AVAILABLENOW);
        assertThat(testProductLang.getAvailablelater()).isEqualTo(DEFAULT_AVAILABLELATER);
        assertThat(testProductLang.getLangcode()).isEqualTo(DEFAULT_LANGCODE);
    }

    @Test
    public void createProductLangWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productLangRepository.findAll().size();

        // Create the ProductLang with an existing ID
        productLang.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductLangMockMvc.perform(post("/api/product-langs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productLang)))
            .andExpect(status().isBadRequest());

        // Validate the ProductLang in the database
        List<ProductLang> productLangList = productLangRepository.findAll();
        assertThat(productLangList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllProductLangs() throws Exception {
        // Initialize the database
        productLangRepository.save(productLang);

        // Get all the productLangList
        restProductLangMockMvc.perform(get("/api/product-langs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productLang.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].descriptionshort").value(hasItem(DEFAULT_DESCRIPTIONSHORT.toString())))
            .andExpect(jsonPath("$.[*].linkrewrite").value(hasItem(DEFAULT_LINKREWRITE.toString())))
            .andExpect(jsonPath("$.[*].metadescription").value(hasItem(DEFAULT_METADESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].metakeywords").value(hasItem(DEFAULT_METAKEYWORDS.toString())))
            .andExpect(jsonPath("$.[*].metatitle").value(hasItem(DEFAULT_METATITLE.toString())))
            .andExpect(jsonPath("$.[*].availablenow").value(hasItem(DEFAULT_AVAILABLENOW.toString())))
            .andExpect(jsonPath("$.[*].availablelater").value(hasItem(DEFAULT_AVAILABLELATER.toString())))
            .andExpect(jsonPath("$.[*].langcode").value(hasItem(DEFAULT_LANGCODE.toString())));
    }

    @Test
    public void getProductLang() throws Exception {
        // Initialize the database
        productLangRepository.save(productLang);

        // Get the productLang
        restProductLangMockMvc.perform(get("/api/product-langs/{id}", productLang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productLang.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.descriptionshort").value(DEFAULT_DESCRIPTIONSHORT.toString()))
            .andExpect(jsonPath("$.linkrewrite").value(DEFAULT_LINKREWRITE.toString()))
            .andExpect(jsonPath("$.metadescription").value(DEFAULT_METADESCRIPTION.toString()))
            .andExpect(jsonPath("$.metakeywords").value(DEFAULT_METAKEYWORDS.toString()))
            .andExpect(jsonPath("$.metatitle").value(DEFAULT_METATITLE.toString()))
            .andExpect(jsonPath("$.availablenow").value(DEFAULT_AVAILABLENOW.toString()))
            .andExpect(jsonPath("$.availablelater").value(DEFAULT_AVAILABLELATER.toString()))
            .andExpect(jsonPath("$.langcode").value(DEFAULT_LANGCODE.toString()));
    }

    @Test
    public void getNonExistingProductLang() throws Exception {
        // Get the productLang
        restProductLangMockMvc.perform(get("/api/product-langs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProductLang() throws Exception {
        // Initialize the database
        productLangRepository.save(productLang);
        int databaseSizeBeforeUpdate = productLangRepository.findAll().size();

        // Update the productLang
        ProductLang updatedProductLang = productLangRepository.findOne(productLang.getId());
        updatedProductLang
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .descriptionshort(UPDATED_DESCRIPTIONSHORT)
            .linkrewrite(UPDATED_LINKREWRITE)
            .metadescription(UPDATED_METADESCRIPTION)
            .metakeywords(UPDATED_METAKEYWORDS)
            .metatitle(UPDATED_METATITLE)
            .availablenow(UPDATED_AVAILABLENOW)
            .availablelater(UPDATED_AVAILABLELATER)
            .langcode(UPDATED_LANGCODE);

        restProductLangMockMvc.perform(put("/api/product-langs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductLang)))
            .andExpect(status().isOk());

        // Validate the ProductLang in the database
        List<ProductLang> productLangList = productLangRepository.findAll();
        assertThat(productLangList).hasSize(databaseSizeBeforeUpdate);
        ProductLang testProductLang = productLangList.get(productLangList.size() - 1);
        assertThat(testProductLang.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProductLang.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProductLang.getDescriptionshort()).isEqualTo(UPDATED_DESCRIPTIONSHORT);
        assertThat(testProductLang.getLinkrewrite()).isEqualTo(UPDATED_LINKREWRITE);
        assertThat(testProductLang.getMetadescription()).isEqualTo(UPDATED_METADESCRIPTION);
        assertThat(testProductLang.getMetakeywords()).isEqualTo(UPDATED_METAKEYWORDS);
        assertThat(testProductLang.getMetatitle()).isEqualTo(UPDATED_METATITLE);
        assertThat(testProductLang.getAvailablenow()).isEqualTo(UPDATED_AVAILABLENOW);
        assertThat(testProductLang.getAvailablelater()).isEqualTo(UPDATED_AVAILABLELATER);
        assertThat(testProductLang.getLangcode()).isEqualTo(UPDATED_LANGCODE);
    }

    @Test
    public void updateNonExistingProductLang() throws Exception {
        int databaseSizeBeforeUpdate = productLangRepository.findAll().size();

        // Create the ProductLang

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductLangMockMvc.perform(put("/api/product-langs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productLang)))
            .andExpect(status().isCreated());

        // Validate the ProductLang in the database
        List<ProductLang> productLangList = productLangRepository.findAll();
        assertThat(productLangList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProductLang() throws Exception {
        // Initialize the database
        productLangRepository.save(productLang);
        int databaseSizeBeforeDelete = productLangRepository.findAll().size();

        // Get the productLang
        restProductLangMockMvc.perform(delete("/api/product-langs/{id}", productLang.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductLang> productLangList = productLangRepository.findAll();
        assertThat(productLangList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductLang.class);
        ProductLang productLang1 = new ProductLang();
        productLang1.setId("id1");
        ProductLang productLang2 = new ProductLang();
        productLang2.setId(productLang1.getId());
        assertThat(productLang1).isEqualTo(productLang2);
        productLang2.setId("id2");
        assertThat(productLang1).isNotEqualTo(productLang2);
        productLang1.setId(null);
        assertThat(productLang1).isNotEqualTo(productLang2);
    }
}
