package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Станислав on 22.08.2017.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%s";


    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        String vacancyQuery = "[data-qa='vacancy-serp__vacancy']";
        String titleQuery = "[data-qa='vacancy-serp__vacancy-title']";
        String compensationQuery = "[data-qa='vacancy-serp__vacancy-compensation']";
        String addressQuery = "[data-qa='vacancy-serp__vacancy-address']";
        String employerQuery = "[data-qa='vacancy-serp__vacancy-employer']";
        Document doc = null;

        try {
            int i = 0;
            while (true) {
                doc = getDocument(searchString, i);

                String html = doc.html();
                Elements elements = doc.select(vacancyQuery);
                for (Element e : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(e.select(titleQuery).text());
                    vacancy.setSiteName("https://hh.ua");
                    vacancy.setCity(e.select(addressQuery).text());
                    vacancy.setUrl(e.select(titleQuery).attr("href"));
                    vacancy.setCompanyName(e.select(employerQuery).text());
                    vacancy.setSalary(e.select(compensationQuery).text());
                    vacancies.add(vacancy);
                }
                if (elements.isEmpty())
                    break;
                i++;
            }
        } catch (IOException e) {}
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0 (jsoup)").referrer("https://hh.ua").get();
    }
}
