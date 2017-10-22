package presencia.digital.web.rest;

import presencia.digital.ProductsApp;

import presencia.digital.domain.Product;
import presencia.digital.repository.ProductRepository;
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
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import presencia.digital.domain.enumeration.RedirectType;
import presencia.digital.domain.enumeration.Condition;
import presencia.digital.domain.enumeration.Visibility;
/**
 * Test class for the ProductResource REST controller.
 *
 * @see ProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductsApp.class)
public class ProductResourceIntTest {

    private static final Long DEFAULT_IDSUPPLIER = 1L;
    private static final Long UPDATED_IDSUPPLIER = 2L;

    private static final Long DEFAULT_IDMANUFACTURER = 1L;
    private static final Long UPDATED_IDMANUFACTURER = 2L;

    private static final Long DEFAULT_IDCATEGORYDEFAULT = 1L;
    private static final Long UPDATED_IDCATEGORYDEFAULT = 2L;

    private static final Long DEFAULT_IDSHOPDEFAULT = 1L;
    private static final Long UPDATED_IDSHOPDEFAULT = 2L;

    private static final Long DEFAULT_IDTAXTRULESGROUP = 1L;
    private static final Long UPDATED_IDTAXTRULESGROUP = 2L;

    private static final Boolean DEFAULT_ONSALE = false;
    private static final Boolean UPDATED_ONSALE = true;

    private static final Boolean DEFAULT_ONLINEONLY = false;
    private static final Boolean UPDATED_ONLINEONLY = true;

    private static final String DEFAULT_EAN_13 = "AAAAAAAAAA";
    private static final String UPDATED_EAN_13 = "BBBBBBBBBB";

    private static final String DEFAULT_ISBN = "AAAAAAAAAA";
    private static final String UPDATED_ISBN = "BBBBBBBBBB";

    private static final String DEFAULT_UPC = "AAAAAAAAAA";
    private static final String UPDATED_UPC = "BBBBBBBBBB";

    private static final Float DEFAULT_ECOTAX = 1F;
    private static final Float UPDATED_ECOTAX = 2F;

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Integer DEFAULT_MINIMALQUANTITY = 1;
    private static final Integer UPDATED_MINIMALQUANTITY = 2;

    private static final Float DEFAULT_PRICE = 1F;
    private static final Float UPDATED_PRICE = 2F;

    private static final Float DEFAULT_WHOLESALEPRICE = 1F;
    private static final Float UPDATED_WHOLESALEPRICE = 2F;

    private static final String DEFAULT_UNITY = "AAAAAAAAAA";
    private static final String UPDATED_UNITY = "BBBBBBBBBB";

    private static final Float DEFAULT_UNITPRICERATIO = 1F;
    private static final Float UPDATED_UNITPRICERATIO = 2F;

    private static final Float DEFAULT_ADDITIONALSHIPPINGCOST = 1F;
    private static final Float UPDATED_ADDITIONALSHIPPINGCOST = 2F;

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIERREFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIERREFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final Float DEFAULT_WIDTH = 1F;
    private static final Float UPDATED_WIDTH = 2F;

    private static final Float DEFAULT_HEIGHT = 1F;
    private static final Float UPDATED_HEIGHT = 2F;

    private static final Float DEFAULT_DEPTH = 1F;
    private static final Float UPDATED_DEPTH = 2F;

    private static final Float DEFAULT_WEIGHT = 1F;
    private static final Float UPDATED_WEIGHT = 2F;

    private static final Integer DEFAULT_OUTOFSTOCK = 1;
    private static final Integer UPDATED_OUTOFSTOCK = 2;

    private static final Boolean DEFAULT_QUANTITYDISCOUNT = false;
    private static final Boolean UPDATED_QUANTITYDISCOUNT = true;

    private static final Integer DEFAULT_CUSTOMIZABLE = 1;
    private static final Integer UPDATED_CUSTOMIZABLE = 2;

    private static final Integer DEFAULT_UPLOADABLEFILES = 1;
    private static final Integer UPDATED_UPLOADABLEFILES = 2;

    private static final Integer DEFAULT_TEXTFIELDS = 1;
    private static final Integer UPDATED_TEXTFIELDS = 2;

    private static final Integer DEFAULT_ACTIVE = 1;
    private static final Integer UPDATED_ACTIVE = 2;

    private static final RedirectType DEFAULT_REDIRECTTYPE = RedirectType.NONE;
    private static final RedirectType UPDATED_REDIRECTTYPE = RedirectType.NOT_FOUND;

    private static final Integer DEFAULT_IDTYPEREDIRECTED = 1;
    private static final Integer UPDATED_IDTYPEREDIRECTED = 2;

    private static final Boolean DEFAULT_AVAILABLEFORORDER = false;
    private static final Boolean UPDATED_AVAILABLEFORORDER = true;

    private static final LocalDate DEFAULT_AVAILABLEDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AVAILABLEDATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_SHOWCONDITION = false;
    private static final Boolean UPDATED_SHOWCONDITION = true;

    private static final Condition DEFAULT_CONDITION = Condition.NEW;
    private static final Condition UPDATED_CONDITION = Condition.USED;

    private static final Boolean DEFAULT_SHOWPRICE = false;
    private static final Boolean UPDATED_SHOWPRICE = true;

    private static final Boolean DEFAULT_INDEXED = false;
    private static final Boolean UPDATED_INDEXED = true;

    private static final Visibility DEFAULT_VISIBILITY = Visibility.BOTH;
    private static final Visibility UPDATED_VISIBILITY = Visibility.CATALOG;

    private static final Boolean DEFAULT_CACHEISPACK = false;
    private static final Boolean UPDATED_CACHEISPACK = true;

    private static final Boolean DEFAULT_CACHEHASATACHMENTS = false;
    private static final Boolean UPDATED_CACHEHASATACHMENTS = true;

    private static final Boolean DEFAULT_ISVIRTUAL = false;
    private static final Boolean UPDATED_ISVIRTUAL = true;

    private static final Integer DEFAULT_CACHEDEFAULTATTRIBUTE = 1;
    private static final Integer UPDATED_CACHEDEFAULTATTRIBUTE = 2;

    private static final LocalDate DEFAULT_DATEADD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEADD = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATEUPD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEUPD = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_ADVANCEDSTOCKMANAGEMENT = false;
    private static final Boolean UPDATED_ADVANCEDSTOCKMANAGEMENT = true;

    private static final Boolean DEFAULT_PACKSTOCKTYPE = false;
    private static final Boolean UPDATED_PACKSTOCKTYPE = true;

    private static final Boolean DEFAULT_STATE = false;
    private static final Boolean UPDATED_STATE = true;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restProductMockMvc;

    private Product product;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductResource productResource = new ProductResource(productRepository);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
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
    public static Product createEntity() {
        Product product = new Product()
            .idsupplier(DEFAULT_IDSUPPLIER)
            .idmanufacturer(DEFAULT_IDMANUFACTURER)
            .idcategorydefault(DEFAULT_IDCATEGORYDEFAULT)
            .idshopdefault(DEFAULT_IDSHOPDEFAULT)
            .idtaxtrulesgroup(DEFAULT_IDTAXTRULESGROUP)
            .onsale(DEFAULT_ONSALE)
            .onlineonly(DEFAULT_ONLINEONLY)
            .ean13(DEFAULT_EAN_13)
            .isbn(DEFAULT_ISBN)
            .upc(DEFAULT_UPC)
            .ecotax(DEFAULT_ECOTAX)
            .quantity(DEFAULT_QUANTITY)
            .minimalquantity(DEFAULT_MINIMALQUANTITY)
            .price(DEFAULT_PRICE)
            .wholesaleprice(DEFAULT_WHOLESALEPRICE)
            .unity(DEFAULT_UNITY)
            .unitpriceratio(DEFAULT_UNITPRICERATIO)
            .additionalshippingcost(DEFAULT_ADDITIONALSHIPPINGCOST)
            .reference(DEFAULT_REFERENCE)
            .supplierreference(DEFAULT_SUPPLIERREFERENCE)
            .location(DEFAULT_LOCATION)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .depth(DEFAULT_DEPTH)
            .weight(DEFAULT_WEIGHT)
            .outofstock(DEFAULT_OUTOFSTOCK)
            .quantitydiscount(DEFAULT_QUANTITYDISCOUNT)
            .customizable(DEFAULT_CUSTOMIZABLE)
            .uploadablefiles(DEFAULT_UPLOADABLEFILES)
            .textfields(DEFAULT_TEXTFIELDS)
            .active(DEFAULT_ACTIVE)
            .redirecttype(DEFAULT_REDIRECTTYPE)
            .idtyperedirected(DEFAULT_IDTYPEREDIRECTED)
            .availablefororder(DEFAULT_AVAILABLEFORORDER)
            .availabledate(DEFAULT_AVAILABLEDATE)
            .showcondition(DEFAULT_SHOWCONDITION)
            .condition(DEFAULT_CONDITION)
            .showprice(DEFAULT_SHOWPRICE)
            .indexed(DEFAULT_INDEXED)
            .visibility(DEFAULT_VISIBILITY)
            .cacheispack(DEFAULT_CACHEISPACK)
            .cachehasatachments(DEFAULT_CACHEHASATACHMENTS)
            .isvirtual(DEFAULT_ISVIRTUAL)
            .cachedefaultattribute(DEFAULT_CACHEDEFAULTATTRIBUTE)
            .dateadd(DEFAULT_DATEADD)
            .dateupd(DEFAULT_DATEUPD)
            .advancedstockmanagement(DEFAULT_ADVANCEDSTOCKMANAGEMENT)
            .packstocktype(DEFAULT_PACKSTOCKTYPE)
            .state(DEFAULT_STATE);
        return product;
    }

    @Before
    public void initTest() {
        productRepository.deleteAll();
        product = createEntity();
    }

    @Test
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getIdsupplier()).isEqualTo(DEFAULT_IDSUPPLIER);
        assertThat(testProduct.getIdmanufacturer()).isEqualTo(DEFAULT_IDMANUFACTURER);
        assertThat(testProduct.getIdcategorydefault()).isEqualTo(DEFAULT_IDCATEGORYDEFAULT);
        assertThat(testProduct.getIdshopdefault()).isEqualTo(DEFAULT_IDSHOPDEFAULT);
        assertThat(testProduct.getIdtaxtrulesgroup()).isEqualTo(DEFAULT_IDTAXTRULESGROUP);
        assertThat(testProduct.isOnsale()).isEqualTo(DEFAULT_ONSALE);
        assertThat(testProduct.isOnlineonly()).isEqualTo(DEFAULT_ONLINEONLY);
        assertThat(testProduct.getEan13()).isEqualTo(DEFAULT_EAN_13);
        assertThat(testProduct.getIsbn()).isEqualTo(DEFAULT_ISBN);
        assertThat(testProduct.getUpc()).isEqualTo(DEFAULT_UPC);
        assertThat(testProduct.getEcotax()).isEqualTo(DEFAULT_ECOTAX);
        assertThat(testProduct.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testProduct.getMinimalquantity()).isEqualTo(DEFAULT_MINIMALQUANTITY);
        assertThat(testProduct.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testProduct.getWholesaleprice()).isEqualTo(DEFAULT_WHOLESALEPRICE);
        assertThat(testProduct.getUnity()).isEqualTo(DEFAULT_UNITY);
        assertThat(testProduct.getUnitpriceratio()).isEqualTo(DEFAULT_UNITPRICERATIO);
        assertThat(testProduct.getAdditionalshippingcost()).isEqualTo(DEFAULT_ADDITIONALSHIPPINGCOST);
        assertThat(testProduct.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testProduct.getSupplierreference()).isEqualTo(DEFAULT_SUPPLIERREFERENCE);
        assertThat(testProduct.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testProduct.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testProduct.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testProduct.getDepth()).isEqualTo(DEFAULT_DEPTH);
        assertThat(testProduct.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testProduct.getOutofstock()).isEqualTo(DEFAULT_OUTOFSTOCK);
        assertThat(testProduct.isQuantitydiscount()).isEqualTo(DEFAULT_QUANTITYDISCOUNT);
        assertThat(testProduct.getCustomizable()).isEqualTo(DEFAULT_CUSTOMIZABLE);
        assertThat(testProduct.getUploadablefiles()).isEqualTo(DEFAULT_UPLOADABLEFILES);
        assertThat(testProduct.getTextfields()).isEqualTo(DEFAULT_TEXTFIELDS);
        assertThat(testProduct.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testProduct.getRedirecttype()).isEqualTo(DEFAULT_REDIRECTTYPE);
        assertThat(testProduct.getIdtyperedirected()).isEqualTo(DEFAULT_IDTYPEREDIRECTED);
        assertThat(testProduct.isAvailablefororder()).isEqualTo(DEFAULT_AVAILABLEFORORDER);
        assertThat(testProduct.getAvailabledate()).isEqualTo(DEFAULT_AVAILABLEDATE);
        assertThat(testProduct.isShowcondition()).isEqualTo(DEFAULT_SHOWCONDITION);
        assertThat(testProduct.getCondition()).isEqualTo(DEFAULT_CONDITION);
        assertThat(testProduct.isShowprice()).isEqualTo(DEFAULT_SHOWPRICE);
        assertThat(testProduct.isIndexed()).isEqualTo(DEFAULT_INDEXED);
        assertThat(testProduct.getVisibility()).isEqualTo(DEFAULT_VISIBILITY);
        assertThat(testProduct.isCacheispack()).isEqualTo(DEFAULT_CACHEISPACK);
        assertThat(testProduct.isCachehasatachments()).isEqualTo(DEFAULT_CACHEHASATACHMENTS);
        assertThat(testProduct.isIsvirtual()).isEqualTo(DEFAULT_ISVIRTUAL);
        assertThat(testProduct.getCachedefaultattribute()).isEqualTo(DEFAULT_CACHEDEFAULTATTRIBUTE);
        assertThat(testProduct.getDateadd()).isEqualTo(DEFAULT_DATEADD);
        assertThat(testProduct.getDateupd()).isEqualTo(DEFAULT_DATEUPD);
        assertThat(testProduct.isAdvancedstockmanagement()).isEqualTo(DEFAULT_ADVANCEDSTOCKMANAGEMENT);
        assertThat(testProduct.isPackstocktype()).isEqualTo(DEFAULT_PACKSTOCKTYPE);
        assertThat(testProduct.isState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkIdcategorydefaultIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setIdcategorydefault(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdshopdefaultIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setIdshopdefault(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdtaxtrulesgroupIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setIdtaxtrulesgroup(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOnsaleIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setOnsale(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOnlineonlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setOnlineonly(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEcotaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setEcotax(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setQuantity(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkMinimalquantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setMinimalquantity(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setPrice(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkWholesalepriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setWholesaleprice(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkUnitpriceratioIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setUnitpriceratio(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAdditionalshippingcostIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setAdditionalshippingcost(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setWidth(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setHeight(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDepthIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setDepth(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setWeight(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkOutofstockIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setOutofstock(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCustomizableIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setCustomizable(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkUploadablefilesIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setUploadablefiles(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTextfieldsIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setTextfields(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setActive(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRedirecttypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setRedirecttype(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIdtyperedirectedIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setIdtyperedirected(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAvailablefororderIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setAvailablefororder(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkShowconditionIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setShowcondition(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkConditionIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setCondition(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkShowpriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setShowprice(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIndexedIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setIndexed(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkVisibilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setVisibility(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCacheispackIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setCacheispack(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCachehasatachmentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setCachehasatachments(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkIsvirtualIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setIsvirtual(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDateaddIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setDateadd(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDateupdIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setDateupd(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAdvancedstockmanagementIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setAdvancedstockmanagement(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPackstocktypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setPackstocktype(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setState(null);

        // Create the Product, which fails.

        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.save(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId())))
            .andExpect(jsonPath("$.[*].idsupplier").value(hasItem(DEFAULT_IDSUPPLIER.intValue())))
            .andExpect(jsonPath("$.[*].idmanufacturer").value(hasItem(DEFAULT_IDMANUFACTURER.intValue())))
            .andExpect(jsonPath("$.[*].idcategorydefault").value(hasItem(DEFAULT_IDCATEGORYDEFAULT.intValue())))
            .andExpect(jsonPath("$.[*].idshopdefault").value(hasItem(DEFAULT_IDSHOPDEFAULT.intValue())))
            .andExpect(jsonPath("$.[*].idtaxtrulesgroup").value(hasItem(DEFAULT_IDTAXTRULESGROUP.intValue())))
            .andExpect(jsonPath("$.[*].onsale").value(hasItem(DEFAULT_ONSALE.booleanValue())))
            .andExpect(jsonPath("$.[*].onlineonly").value(hasItem(DEFAULT_ONLINEONLY.booleanValue())))
            .andExpect(jsonPath("$.[*].ean13").value(hasItem(DEFAULT_EAN_13.toString())))
            .andExpect(jsonPath("$.[*].isbn").value(hasItem(DEFAULT_ISBN.toString())))
            .andExpect(jsonPath("$.[*].upc").value(hasItem(DEFAULT_UPC.toString())))
            .andExpect(jsonPath("$.[*].ecotax").value(hasItem(DEFAULT_ECOTAX.doubleValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].minimalquantity").value(hasItem(DEFAULT_MINIMALQUANTITY)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].wholesaleprice").value(hasItem(DEFAULT_WHOLESALEPRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].unity").value(hasItem(DEFAULT_UNITY.toString())))
            .andExpect(jsonPath("$.[*].unitpriceratio").value(hasItem(DEFAULT_UNITPRICERATIO.doubleValue())))
            .andExpect(jsonPath("$.[*].additionalshippingcost").value(hasItem(DEFAULT_ADDITIONALSHIPPINGCOST.doubleValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].supplierreference").value(hasItem(DEFAULT_SUPPLIERREFERENCE.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].depth").value(hasItem(DEFAULT_DEPTH.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].outofstock").value(hasItem(DEFAULT_OUTOFSTOCK)))
            .andExpect(jsonPath("$.[*].quantitydiscount").value(hasItem(DEFAULT_QUANTITYDISCOUNT.booleanValue())))
            .andExpect(jsonPath("$.[*].customizable").value(hasItem(DEFAULT_CUSTOMIZABLE)))
            .andExpect(jsonPath("$.[*].uploadablefiles").value(hasItem(DEFAULT_UPLOADABLEFILES)))
            .andExpect(jsonPath("$.[*].textfields").value(hasItem(DEFAULT_TEXTFIELDS)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE)))
            .andExpect(jsonPath("$.[*].redirecttype").value(hasItem(DEFAULT_REDIRECTTYPE.toString())))
            .andExpect(jsonPath("$.[*].idtyperedirected").value(hasItem(DEFAULT_IDTYPEREDIRECTED)))
            .andExpect(jsonPath("$.[*].availablefororder").value(hasItem(DEFAULT_AVAILABLEFORORDER.booleanValue())))
            .andExpect(jsonPath("$.[*].availabledate").value(hasItem(DEFAULT_AVAILABLEDATE.toString())))
            .andExpect(jsonPath("$.[*].showcondition").value(hasItem(DEFAULT_SHOWCONDITION.booleanValue())))
            .andExpect(jsonPath("$.[*].condition").value(hasItem(DEFAULT_CONDITION.toString())))
            .andExpect(jsonPath("$.[*].showprice").value(hasItem(DEFAULT_SHOWPRICE.booleanValue())))
            .andExpect(jsonPath("$.[*].indexed").value(hasItem(DEFAULT_INDEXED.booleanValue())))
            .andExpect(jsonPath("$.[*].visibility").value(hasItem(DEFAULT_VISIBILITY.toString())))
            .andExpect(jsonPath("$.[*].cacheispack").value(hasItem(DEFAULT_CACHEISPACK.booleanValue())))
            .andExpect(jsonPath("$.[*].cachehasatachments").value(hasItem(DEFAULT_CACHEHASATACHMENTS.booleanValue())))
            .andExpect(jsonPath("$.[*].isvirtual").value(hasItem(DEFAULT_ISVIRTUAL.booleanValue())))
            .andExpect(jsonPath("$.[*].cachedefaultattribute").value(hasItem(DEFAULT_CACHEDEFAULTATTRIBUTE)))
            .andExpect(jsonPath("$.[*].dateadd").value(hasItem(DEFAULT_DATEADD.toString())))
            .andExpect(jsonPath("$.[*].dateupd").value(hasItem(DEFAULT_DATEUPD.toString())))
            .andExpect(jsonPath("$.[*].advancedstockmanagement").value(hasItem(DEFAULT_ADVANCEDSTOCKMANAGEMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].packstocktype").value(hasItem(DEFAULT_PACKSTOCKTYPE.booleanValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.booleanValue())));
    }

    @Test
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.save(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId()))
            .andExpect(jsonPath("$.idsupplier").value(DEFAULT_IDSUPPLIER.intValue()))
            .andExpect(jsonPath("$.idmanufacturer").value(DEFAULT_IDMANUFACTURER.intValue()))
            .andExpect(jsonPath("$.idcategorydefault").value(DEFAULT_IDCATEGORYDEFAULT.intValue()))
            .andExpect(jsonPath("$.idshopdefault").value(DEFAULT_IDSHOPDEFAULT.intValue()))
            .andExpect(jsonPath("$.idtaxtrulesgroup").value(DEFAULT_IDTAXTRULESGROUP.intValue()))
            .andExpect(jsonPath("$.onsale").value(DEFAULT_ONSALE.booleanValue()))
            .andExpect(jsonPath("$.onlineonly").value(DEFAULT_ONLINEONLY.booleanValue()))
            .andExpect(jsonPath("$.ean13").value(DEFAULT_EAN_13.toString()))
            .andExpect(jsonPath("$.isbn").value(DEFAULT_ISBN.toString()))
            .andExpect(jsonPath("$.upc").value(DEFAULT_UPC.toString()))
            .andExpect(jsonPath("$.ecotax").value(DEFAULT_ECOTAX.doubleValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.minimalquantity").value(DEFAULT_MINIMALQUANTITY))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.wholesaleprice").value(DEFAULT_WHOLESALEPRICE.doubleValue()))
            .andExpect(jsonPath("$.unity").value(DEFAULT_UNITY.toString()))
            .andExpect(jsonPath("$.unitpriceratio").value(DEFAULT_UNITPRICERATIO.doubleValue()))
            .andExpect(jsonPath("$.additionalshippingcost").value(DEFAULT_ADDITIONALSHIPPINGCOST.doubleValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.supplierreference").value(DEFAULT_SUPPLIERREFERENCE.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.depth").value(DEFAULT_DEPTH.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.outofstock").value(DEFAULT_OUTOFSTOCK))
            .andExpect(jsonPath("$.quantitydiscount").value(DEFAULT_QUANTITYDISCOUNT.booleanValue()))
            .andExpect(jsonPath("$.customizable").value(DEFAULT_CUSTOMIZABLE))
            .andExpect(jsonPath("$.uploadablefiles").value(DEFAULT_UPLOADABLEFILES))
            .andExpect(jsonPath("$.textfields").value(DEFAULT_TEXTFIELDS))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE))
            .andExpect(jsonPath("$.redirecttype").value(DEFAULT_REDIRECTTYPE.toString()))
            .andExpect(jsonPath("$.idtyperedirected").value(DEFAULT_IDTYPEREDIRECTED))
            .andExpect(jsonPath("$.availablefororder").value(DEFAULT_AVAILABLEFORORDER.booleanValue()))
            .andExpect(jsonPath("$.availabledate").value(DEFAULT_AVAILABLEDATE.toString()))
            .andExpect(jsonPath("$.showcondition").value(DEFAULT_SHOWCONDITION.booleanValue()))
            .andExpect(jsonPath("$.condition").value(DEFAULT_CONDITION.toString()))
            .andExpect(jsonPath("$.showprice").value(DEFAULT_SHOWPRICE.booleanValue()))
            .andExpect(jsonPath("$.indexed").value(DEFAULT_INDEXED.booleanValue()))
            .andExpect(jsonPath("$.visibility").value(DEFAULT_VISIBILITY.toString()))
            .andExpect(jsonPath("$.cacheispack").value(DEFAULT_CACHEISPACK.booleanValue()))
            .andExpect(jsonPath("$.cachehasatachments").value(DEFAULT_CACHEHASATACHMENTS.booleanValue()))
            .andExpect(jsonPath("$.isvirtual").value(DEFAULT_ISVIRTUAL.booleanValue()))
            .andExpect(jsonPath("$.cachedefaultattribute").value(DEFAULT_CACHEDEFAULTATTRIBUTE))
            .andExpect(jsonPath("$.dateadd").value(DEFAULT_DATEADD.toString()))
            .andExpect(jsonPath("$.dateupd").value(DEFAULT_DATEUPD.toString()))
            .andExpect(jsonPath("$.advancedstockmanagement").value(DEFAULT_ADVANCEDSTOCKMANAGEMENT.booleanValue()))
            .andExpect(jsonPath("$.packstocktype").value(DEFAULT_PACKSTOCKTYPE.booleanValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.booleanValue()));
    }

    @Test
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateProduct() throws Exception {
        // Initialize the database
        productRepository.save(product);
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findOne(product.getId());
        updatedProduct
            .idsupplier(UPDATED_IDSUPPLIER)
            .idmanufacturer(UPDATED_IDMANUFACTURER)
            .idcategorydefault(UPDATED_IDCATEGORYDEFAULT)
            .idshopdefault(UPDATED_IDSHOPDEFAULT)
            .idtaxtrulesgroup(UPDATED_IDTAXTRULESGROUP)
            .onsale(UPDATED_ONSALE)
            .onlineonly(UPDATED_ONLINEONLY)
            .ean13(UPDATED_EAN_13)
            .isbn(UPDATED_ISBN)
            .upc(UPDATED_UPC)
            .ecotax(UPDATED_ECOTAX)
            .quantity(UPDATED_QUANTITY)
            .minimalquantity(UPDATED_MINIMALQUANTITY)
            .price(UPDATED_PRICE)
            .wholesaleprice(UPDATED_WHOLESALEPRICE)
            .unity(UPDATED_UNITY)
            .unitpriceratio(UPDATED_UNITPRICERATIO)
            .additionalshippingcost(UPDATED_ADDITIONALSHIPPINGCOST)
            .reference(UPDATED_REFERENCE)
            .supplierreference(UPDATED_SUPPLIERREFERENCE)
            .location(UPDATED_LOCATION)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .depth(UPDATED_DEPTH)
            .weight(UPDATED_WEIGHT)
            .outofstock(UPDATED_OUTOFSTOCK)
            .quantitydiscount(UPDATED_QUANTITYDISCOUNT)
            .customizable(UPDATED_CUSTOMIZABLE)
            .uploadablefiles(UPDATED_UPLOADABLEFILES)
            .textfields(UPDATED_TEXTFIELDS)
            .active(UPDATED_ACTIVE)
            .redirecttype(UPDATED_REDIRECTTYPE)
            .idtyperedirected(UPDATED_IDTYPEREDIRECTED)
            .availablefororder(UPDATED_AVAILABLEFORORDER)
            .availabledate(UPDATED_AVAILABLEDATE)
            .showcondition(UPDATED_SHOWCONDITION)
            .condition(UPDATED_CONDITION)
            .showprice(UPDATED_SHOWPRICE)
            .indexed(UPDATED_INDEXED)
            .visibility(UPDATED_VISIBILITY)
            .cacheispack(UPDATED_CACHEISPACK)
            .cachehasatachments(UPDATED_CACHEHASATACHMENTS)
            .isvirtual(UPDATED_ISVIRTUAL)
            .cachedefaultattribute(UPDATED_CACHEDEFAULTATTRIBUTE)
            .dateadd(UPDATED_DATEADD)
            .dateupd(UPDATED_DATEUPD)
            .advancedstockmanagement(UPDATED_ADVANCEDSTOCKMANAGEMENT)
            .packstocktype(UPDATED_PACKSTOCKTYPE)
            .state(UPDATED_STATE);

        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduct)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getIdsupplier()).isEqualTo(UPDATED_IDSUPPLIER);
        assertThat(testProduct.getIdmanufacturer()).isEqualTo(UPDATED_IDMANUFACTURER);
        assertThat(testProduct.getIdcategorydefault()).isEqualTo(UPDATED_IDCATEGORYDEFAULT);
        assertThat(testProduct.getIdshopdefault()).isEqualTo(UPDATED_IDSHOPDEFAULT);
        assertThat(testProduct.getIdtaxtrulesgroup()).isEqualTo(UPDATED_IDTAXTRULESGROUP);
        assertThat(testProduct.isOnsale()).isEqualTo(UPDATED_ONSALE);
        assertThat(testProduct.isOnlineonly()).isEqualTo(UPDATED_ONLINEONLY);
        assertThat(testProduct.getEan13()).isEqualTo(UPDATED_EAN_13);
        assertThat(testProduct.getIsbn()).isEqualTo(UPDATED_ISBN);
        assertThat(testProduct.getUpc()).isEqualTo(UPDATED_UPC);
        assertThat(testProduct.getEcotax()).isEqualTo(UPDATED_ECOTAX);
        assertThat(testProduct.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testProduct.getMinimalquantity()).isEqualTo(UPDATED_MINIMALQUANTITY);
        assertThat(testProduct.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProduct.getWholesaleprice()).isEqualTo(UPDATED_WHOLESALEPRICE);
        assertThat(testProduct.getUnity()).isEqualTo(UPDATED_UNITY);
        assertThat(testProduct.getUnitpriceratio()).isEqualTo(UPDATED_UNITPRICERATIO);
        assertThat(testProduct.getAdditionalshippingcost()).isEqualTo(UPDATED_ADDITIONALSHIPPINGCOST);
        assertThat(testProduct.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testProduct.getSupplierreference()).isEqualTo(UPDATED_SUPPLIERREFERENCE);
        assertThat(testProduct.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testProduct.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testProduct.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testProduct.getDepth()).isEqualTo(UPDATED_DEPTH);
        assertThat(testProduct.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testProduct.getOutofstock()).isEqualTo(UPDATED_OUTOFSTOCK);
        assertThat(testProduct.isQuantitydiscount()).isEqualTo(UPDATED_QUANTITYDISCOUNT);
        assertThat(testProduct.getCustomizable()).isEqualTo(UPDATED_CUSTOMIZABLE);
        assertThat(testProduct.getUploadablefiles()).isEqualTo(UPDATED_UPLOADABLEFILES);
        assertThat(testProduct.getTextfields()).isEqualTo(UPDATED_TEXTFIELDS);
        assertThat(testProduct.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testProduct.getRedirecttype()).isEqualTo(UPDATED_REDIRECTTYPE);
        assertThat(testProduct.getIdtyperedirected()).isEqualTo(UPDATED_IDTYPEREDIRECTED);
        assertThat(testProduct.isAvailablefororder()).isEqualTo(UPDATED_AVAILABLEFORORDER);
        assertThat(testProduct.getAvailabledate()).isEqualTo(UPDATED_AVAILABLEDATE);
        assertThat(testProduct.isShowcondition()).isEqualTo(UPDATED_SHOWCONDITION);
        assertThat(testProduct.getCondition()).isEqualTo(UPDATED_CONDITION);
        assertThat(testProduct.isShowprice()).isEqualTo(UPDATED_SHOWPRICE);
        assertThat(testProduct.isIndexed()).isEqualTo(UPDATED_INDEXED);
        assertThat(testProduct.getVisibility()).isEqualTo(UPDATED_VISIBILITY);
        assertThat(testProduct.isCacheispack()).isEqualTo(UPDATED_CACHEISPACK);
        assertThat(testProduct.isCachehasatachments()).isEqualTo(UPDATED_CACHEHASATACHMENTS);
        assertThat(testProduct.isIsvirtual()).isEqualTo(UPDATED_ISVIRTUAL);
        assertThat(testProduct.getCachedefaultattribute()).isEqualTo(UPDATED_CACHEDEFAULTATTRIBUTE);
        assertThat(testProduct.getDateadd()).isEqualTo(UPDATED_DATEADD);
        assertThat(testProduct.getDateupd()).isEqualTo(UPDATED_DATEUPD);
        assertThat(testProduct.isAdvancedstockmanagement()).isEqualTo(UPDATED_ADVANCEDSTOCKMANAGEMENT);
        assertThat(testProduct.isPackstocktype()).isEqualTo(UPDATED_PACKSTOCKTYPE);
        assertThat(testProduct.isState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteProduct() throws Exception {
        // Initialize the database
        productRepository.save(product);
        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Get the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = new Product();
        product1.setId("id1");
        Product product2 = new Product();
        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);
        product2.setId("id2");
        assertThat(product1).isNotEqualTo(product2);
        product1.setId(null);
        assertThat(product1).isNotEqualTo(product2);
    }
}
