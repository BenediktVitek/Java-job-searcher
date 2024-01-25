//package benediktvitek.javajobsearcher.services;
//
//import benediktvitek.javajobsearcher.entities.JobStackOffer;
//import benediktvitek.javajobsearcher.repositories.JobStackOfferRepository;
//import benediktvitek.javajobsearcher.utils.webscrapers.GlassDoorsWebScraper;
//import benediktvitek.javajobsearcher.utils.webscrapers.JobStackScraper;
//import benediktvitek.javajobsearcher.utils.webscrapers.WebScraper;
//import benediktvitek.javajobsearcher.utils.webscrapers.factories.WebScraperFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class JobStackService {
//
//    private final WebScraperFactory webScraperFactory;
//
//    private JobStackScraper getJobStackScraper() {
//        WebScraper glassDoorsScraper = webScraperFactory.getWebScrapers().stream()
//                .filter(webScraper -> webScraper instanceof JobStackScraper)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("No Glass Doors Web Scraper Found"));
//        return (JobStackScraper) glassDoorsScraper;
//    }
//
//    public List<String> getAllUrls() {
//        return jobStackOfferRepository.findAll()
//                .stream()
//                .map(jobStackOffer -> jobStackOffer.getLink())
//                .collect(Collectors.toList());
//    }
//
//    public void saveNew(String offerUrl) {
//        jobStackOfferRepository.save(new JobStackOffer(offerUrl));
//    }
//
//    public List<String> getNewOffers() throws IOException {
//        JobStackScraper jobStackScraper = getJobStackScraper();
//
//        List<String> actualOffers = jobStackScraper.getOfferLinks(jobStackScraper.getURL());
//        List<String> oldOffers = getAllUrls();
//
//        actualOffers.removeAll(oldOffers);
//
//        for (String offer: actualOffers) {
//            saveNew(offer);
//        }
//
//        return actualOffers;
//    }
//
//
//}
