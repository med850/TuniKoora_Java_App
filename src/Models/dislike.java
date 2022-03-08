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
public class dislike {
    int dislike;
    int user_id;
    int article_id;

    public dislike(int dislike, int user_id, int article_id) {
        this.dislike = dislike;
        this.user_id = user_id;
        this.article_id = article_id;
    }

    public dislike() {
    }

    public int getDislike() {
        return dislike;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "dislike{" + "dislike=" + dislike + ", user_di=" + user_id + ", article_id=" + article_id + '}';
    }
    
    
    
}
