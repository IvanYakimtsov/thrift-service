package by.bsuir.iit.aipos.service;

import by.bsuir.iit.aipos.thrift.Article;
import by.bsuir.iit.aipos.thrift.WebPatternsService;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class ServiceHandler implements WebPatternsService.Iface {
    private Article article;

    @Override
    public boolean addArticle(Article article) throws TException {
        System.out.println();
        this.article = article;
        return true;
    }

    @Override
    public boolean removeArticle(String articleName) throws TException {
        return false;
    }

    @Override
    public Article getArticle(String articleName) throws TException {
        return article;
    }

    @Override
    public boolean update(Article article) throws TException {
        return false;
    }

    @Override
    public List<String> getArticleList() throws TException {
        List<String> list = new ArrayList<>();
        list.add("kek");
        list.add("lol");
        return list;
    }
}
