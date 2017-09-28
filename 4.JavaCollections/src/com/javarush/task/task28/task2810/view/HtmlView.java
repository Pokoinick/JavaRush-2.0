package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Станислав on 22.08.2017.
 */
public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "C:\\Users\\Станислав\\Desktop\\Java\\JavaRush2.0\\JavaRushTasks\\4.JavaCollections" + "/src/" + this.getClass().getPackage().getName().toString().replaceAll("\\.", "/") + "/vacancies.html";
    //private final String filePath = "./src/" + this.getClass().getPackage().getName().toString().replaceAll("\\.", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
            String fileContent = getUpdatedFileContent(vacancies);
            updateFile(fileContent);
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String result = null;

        try {
            Document document = getDocument();
            Element elementOriginal = document.getElementsByClass("template").first();
            Element element = elementOriginal.clone();
            element.removeClass("template");
            element.removeAttr("style");
            document.select("tr[class=vacancy]").remove();

            for (Vacancy vacancy : vacancies) {
                Element elVacancy = element.clone();
                elVacancy.getElementsByAttributeValue("class", "city").first().text(vacancy.getCity());
                elVacancy.getElementsByAttributeValue("class", "companyName").first().text(vacancy.getCompanyName());
                elVacancy.getElementsByAttributeValue("class", "salary").first().text(vacancy.getSalary());
                Element link = elVacancy.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                elementOriginal.before(elVacancy.outerHtml());
            }
            result = document.html();
        } catch (Exception e) {
            e.printStackTrace();
            result = "Some exception occurred";
        }

        return result;
    }

    private void updateFile(String fileContent) {
        try (FileWriter fileWriter = new FileWriter(filePath)){
            fileWriter.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Sankt-Peterburg");
    }
}
