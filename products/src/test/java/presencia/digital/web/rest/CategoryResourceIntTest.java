package presencia.digital.web.rest;

import presencia.digital.ProductsApp;

import presencia.digital.domain.Category;
import presencia.digital.repository.CategoryRepository;
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

import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CategoryResource REST controller.
 *
 * @see CategoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductsApp.class)
public class CategoryResourceIntTest {

    private static final Instant DEFAULT_IDPARENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_IDPARENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_IDSHOPDEFAULT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_IDSHOPDEFAULT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LEVELDEPTH = 1;
    private static final Integer UPDATED_LEVELDEPTH = 2;

    private static final Integer DEFAULT_NLEFT = 1;
    private static final Integer UPDATED_NLEFT = 2;

    private static final Integer DEFAULT_NRIGHT = 1;
    private static final Integer UPDATED_NRIGHT = 2;

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final LocalDate DEFAULT_DATEADD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEADD = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATEUPD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEUPD = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_POSITION = 1;
    private static final Integer UPDATED_POSITION = 2;

    private static final Boolean DEFAULT_ISROOTCATEGORY = false;
    private static final Boolean UPDATED_ISROOTCATEGORY = true;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restCategoryMockMvc;

    private Category category;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategoryResource categoryResource = new CategoryResource(categoryRepository);
        this.restCategoryMockMvc = MockMvcBuilders.standaloneSetup(categoryResource)
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
    public static Category createEntity() {
        Category category = new Category()
            .idparent(DEFAULT_IDPARENT)
            .idshopdefault(DEFAULT_IDSHOPDEFAULT)
            .leveldepth(DEFAULT_LEVELDEPTH)
            .nleft(DEFAULT_NLEFT)
            .nright(DEFAULT_NRIGHT)
            .active(DEFAULT_ACTIVE)
            .dateadd(DEFAULT_DATEADD)
            .dateupd(DEFAULT_DATEUPD)
            .position(DEFAULT_POSITION)
            .isrootcategory(DEFAULT_ISROOTCATEGORY);
        return category;
    }

    @Before
    public void initTest() {
        categoryRepository.deleteAll();
        category = createEntity();
    }

    @Test
    public void createCategory() throws Exception {
        int databaseSizeBeforeCreate = categoryRepository.findAll().size();

        // Create the Category
        restCategoryMockMvc.perform(post("/api/categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(category)))
            .andExpect(status().isCreated());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeCreate + 1);
        Category testCategory = categoryList.get(categoryList.size() - 1);
        assertThat(testCategory.getIdparent()).isEqualTo(DEFAULT_IDPARENT);
        assertThat(testCategory.getIdshopdefault()).isEqualTo(DEFAULT_IDSHOPDEFAULT);
        assertThat(testCategory.getLeveldepth()).isEqualTo(DEFAULT_LEVELDEPTH);
        assertThat(testCategory.getNleft()).isEqualTo(DEFAULT_NLEFT);
        assertThat(testCategory.getNright()).isEqualTo(DEFAULT_NRIGHT);
        assertThat(testCategory.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testCategory.getDateadd()).isEqualTo(DEFAULT_DATEADD);
        assertThat(testCategory.getDateupd()).isEqualTo(DEFAULT_DATEUPD);
        assertThat(testCategory.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testCategory.isIsrootcategory()).isEqualTo(DEFAULT_ISROOTCATEGORY);
    }

    @Test
    public void createCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoryRepository.findAll().size();

        // Create the Category with an existing ID
        category.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryMockMvc.perform(post("/api/categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(category)))
            .andExpect(status().isBadRequest());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllCategories() throws Exception {
        // Initialize the database
        categoryRepository.save(category);

        // Get all the categoryList
        restCategoryMockMvc.perform(get("/api/categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(category.getId())))
            .andExpect(jsonPath("$.[*].idparent").value(hasItem(DEFAULT_IDPARENT.toString())))
            .andExpect(jsonPath("$.[*].idshopdefault").value(hasItem(DEFAULT_IDSHOPDEFAULT.toString())))
            .andExpect(jsonPath("$.[*].leveldepth").value(hasItem(DEFAULT_LEVELDEPTH)))
            .andExpect(jsonPath("$.[*].nleft").value(hasItem(DEFAULT_NLEFT)))
            .andExpect(jsonPath("$.[*].nright").value(hasItem(DEFAULT_NRIGHT)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].dateadd").value(hasItem(DEFAULT_DATEADD.toString())))
            .andExpect(jsonPath("$.[*].dateupd").value(hasItem(DEFAULT_DATEUPD.toString())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].isrootcategory").value(hasItem(DEFAULT_ISROOTCATEGORY.booleanValue())));
    }

    @Test
    public void getCategory() throws Exception {
        // Initialize the database
        categoryRepository.save(category);

        // Get the category
        restCategoryMockMvc.perform(get("/api/categories/{id}", category.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(category.getId()))
            .andExpect(jsonPath("$.idparent").value(DEFAULT_IDPARENT.toString()))
            .andExpect(jsonPath("$.idshopdefault").value(DEFAULT_IDSHOPDEFAULT.toString()))
            .andExpect(jsonPath("$.leveldepth").value(DEFAULT_LEVELDEPTH))
            .andExpect(jsonPath("$.nleft").value(DEFAULT_NLEFT))
            .andExpect(jsonPath("$.nright").value(DEFAULT_NRIGHT))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.dateadd").value(DEFAULT_DATEADD.toString()))
            .andExpect(jsonPath("$.dateupd").value(DEFAULT_DATEUPD.toString()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.isrootcategory").value(DEFAULT_ISROOTCATEGORY.booleanValue()));
    }

    @Test
    public void getNonExistingCategory() throws Exception {
        // Get the category
        restCategoryMockMvc.perform(get("/api/categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCategory() throws Exception {
        // Initialize the database
        categoryRepository.save(category);
        int databaseSizeBeforeUpdate = categoryRepository.findAll().size();

        // Update the category
        Category updatedCategory = categoryRepository.findOne(category.getId());
        updatedCategory
            .idparent(UPDATED_IDPARENT)
            .idshopdefault(UPDATED_IDSHOPDEFAULT)
            .leveldepth(UPDATED_LEVELDEPTH)
            .nleft(UPDATED_NLEFT)
            .nright(UPDATED_NRIGHT)
            .active(UPDATED_ACTIVE)
            .dateadd(UPDATED_DATEADD)
            .dateupd(UPDATED_DATEUPD)
            .position(UPDATED_POSITION)
            .isrootcategory(UPDATED_ISROOTCATEGORY);

        restCategoryMockMvc.perform(put("/api/categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCategory)))
            .andExpect(status().isOk());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate);
        Category testCategory = categoryList.get(categoryList.size() - 1);
        assertThat(testCategory.getIdparent()).isEqualTo(UPDATED_IDPARENT);
        assertThat(testCategory.getIdshopdefault()).isEqualTo(UPDATED_IDSHOPDEFAULT);
        assertThat(testCategory.getLeveldepth()).isEqualTo(UPDATED_LEVELDEPTH);
        assertThat(testCategory.getNleft()).isEqualTo(UPDATED_NLEFT);
        assertThat(testCategory.getNright()).isEqualTo(UPDATED_NRIGHT);
        assertThat(testCategory.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testCategory.getDateadd()).isEqualTo(UPDATED_DATEADD);
        assertThat(testCategory.getDateupd()).isEqualTo(UPDATED_DATEUPD);
        assertThat(testCategory.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testCategory.isIsrootcategory()).isEqualTo(UPDATED_ISROOTCATEGORY);
    }

    @Test
    public void updateNonExistingCategory() throws Exception {
        int databaseSizeBeforeUpdate = categoryRepository.findAll().size();

        // Create the Category

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCategoryMockMvc.perform(put("/api/categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(category)))
            .andExpect(status().isCreated());

        // Validate the Category in the database
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteCategory() throws Exception {
        // Initialize the database
        categoryRepository.save(category);
        int databaseSizeBeforeDelete = categoryRepository.findAll().size();

        // Get the category
        restCategoryMockMvc.perform(delete("/api/categories/{id}", category.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category.class);
        Category category1 = new Category();
        category1.setId("id1");
        Category category2 = new Category();
        category2.setId(category1.getId());
        assertThat(category1).isEqualTo(category2);
        category2.setId("id2");
        assertThat(category1).isNotEqualTo(category2);
        category1.setId(null);
        assertThat(category1).isNotEqualTo(category2);
    }
}
