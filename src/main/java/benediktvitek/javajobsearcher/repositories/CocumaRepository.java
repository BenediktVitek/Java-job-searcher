package benediktvitek.javajobsearcher.repositories;

import benediktvitek.javajobsearcher.entities.CocumaOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocumaRepository extends JpaRepository<CocumaOffer, String> {
}
