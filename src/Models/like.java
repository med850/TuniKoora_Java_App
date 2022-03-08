/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author wassim
 */
public class like {
    int like;
    int user_id;
    int article_id;

    public like(int like, int user_id, int article_id) {
        this.like = like;
        this.user_id = user_id;
        this.article_id = article_id;
    }
    public like(){};

    public int getLike() {
        return like;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "like{" + "like=" + like + ", user_id=" + user_id + ", article_id=" + article_id + '}';
    }
    
    
    
}
