namespace java by.bsuir.iit.aipos.thrift

struct Article {
    1 : string name
    2 : binary photo
    3 : string body
}

enum ArticleParameters {
    NAME,
    PHOTO,
    BODY
}

service WebPatternsService {
    bool addArticle(1 : Article article),
    bool removeArticle(1 : string articleName),
    Article getArticle(1 : string articleName),
    bool update(1 : Article article)
    list<string> getArticleList()
}