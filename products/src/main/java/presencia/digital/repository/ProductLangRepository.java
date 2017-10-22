package presencia.digital.repository;

import presencia.digital.domain.ProductLang;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ProductLang entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductLangRepository extends MongoRepository<ProductLang, String> {

}
