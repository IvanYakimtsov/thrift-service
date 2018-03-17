namespace java by.bsuir.iit.aipos.thrift

struct Header {
    1 : string patternName
    2 : string authorEmail
}

struct Content {
    1 : string body
    2 : binary image
    3 : string imageFormat
}

struct Article {
    1 : Header header
    2 : Content content
}

exception ServiceServerException {
    1 : string message;
}

service WebPatternsService {
    bool addArticle(1 : Article article) throws (1 : ServiceServerException e),
    bool removeArticle(1 : Header header) throws (1 : ServiceServerException e),
    Content getArticle(1 : Header header) throws (1 : ServiceServerException e),
    bool updateArticle(1 : Header header, 2 : Article modifyArticle) throws (1 : ServiceServerException e),
    list<Header> getArticleList() throws (1 : ServiceServerException e)
}