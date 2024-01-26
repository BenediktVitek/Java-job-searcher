package benediktvitek.javajobsearcher.services;

import benediktvitek.javajobsearcher.entities.JobStackOffer;
import benediktvitek.javajobsearcher.repositories.JobStackOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JobStackOfferService {

    private final JobStackOfferRepository jobStackOfferRepository;

    public List<String> getAllUrls() {
        return jobStackOfferRepository.findAll()
                .stream()
                .map(JobStackOffer::getLink)
                .collect(Collectors.toList());
    }

    public void saveNew(String offerUrl) {
        jobStackOfferRepository.save(new JobStackOffer(offerUrl));
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
