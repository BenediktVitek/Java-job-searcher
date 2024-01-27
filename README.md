<h1>Java-job-searcher</h1>

<h2>Jobstack.it</h2>

Jobstack.it lacks the time of publishing for job offers. To address this, all offer links are scraped and stored in the database. During subsequent scrapes, the system compares new links with the existing ones. Only new offers are scraped, and if they are suitable for a junior Java developer, they are printed. All newly appeared offers, including unsuitable ones, are saved for future comparison. Scraping is performed using Apache HttpClient.

<h2>Glassdoors.com</h2>

Glassdoors offers a Selenium-powered scraper. Due to the efficiency of the Glassdoors searcher, there's no need to scrape each offer individually. Instead, all offers less than 24 hours old are printed.

<h2>Jobs.cz </h2>
  
Jobs.cz provides both the time of publishing and an efficient system for showing the newest offers. Leveraging this information, the scraper selectively scrapes necessary offers, which are then filtered based on keywords and printed out.

This project aims to streamline the job search process by automating the retrieval of relevant job offers from multiple platforms. The optimization involves avoiding unnecessary scraping, filtering offers based on suitability, and saving data for future comparisons.

Links and filtering mechanisms are currently hardcoded. Future plans include addressing these hardcoded elements for a more flexible and scalable solution.
