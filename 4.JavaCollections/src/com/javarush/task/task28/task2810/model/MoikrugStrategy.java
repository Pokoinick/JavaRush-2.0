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
 * Created by Станислав on 24.08.2017.
 */
public class MoikrugStrategy implements Strategy {

    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        try {
            for (Document doc = getDocument(searchString, page); doc != null; doc = getDocument(searchString, ++page)) {


                String html = doc.html();
                Elements elements = doc.select("[id^=job_]");
                if (elements.size() == 0)
                    break;
                for (Element e : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(e.getElementsByClass("title").first().text());
                    vacancy.setSiteName("https://moikrug.ru");
                    Elements location = e.getElementsByClass("location");
                    vacancy.setCity(location.size() != 0 ? location.first().text() : "");
                    vacancy.setUrl(vacancy.getSiteName() + "/" + e.getElementsByClass("title").first().getElementsByTag("a").attr("href").substring(1));
                    vacancy.setCompanyName(e.getElementsByClass("company_name").first().text());
                    vacancy.setSalary(e.getElementsByClass("salary").first().text());
                    vacancies.add(vacancy);
                }
            }
        } catch (IOException e) {}
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document html;
        html = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (jsoup)")
                .referrer("https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2")
                .get();

        return html;
    }
}
