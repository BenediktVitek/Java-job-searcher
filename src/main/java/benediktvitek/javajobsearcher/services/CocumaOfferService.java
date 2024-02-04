package benediktvitek.javajobsearcher.services;

import benediktvitek.javajobsearcher.entities.CocumaOffer;
import benediktvitek.javajobsearcher.entities.JobStackOffer;
import benediktvitek.javajobsearcher.repositories.CocumaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CocumaOfferService {

    private final CocumaRepository cocumaRepository;

    public List<String> getAllUrls() {
        return cocumaRepository.findAll()
                .stream()
                .map(CocumaOffer::getLink)
                .collect(Collectors.toList());
    }

    public void saveNew(String link) {
        cocumaRepository.save(new CocumaOffer(link));
    }

    public List<String> saveAndGetNewOffers(List<String> offerLinks) {

        List<String> oldOffers = getAllUrls();

        offerLinks.removeAll(oldOffers);

        for (String offer : offerLinks) {
            saveNew(offer);
        }

        return offerLinks;
    }
}
