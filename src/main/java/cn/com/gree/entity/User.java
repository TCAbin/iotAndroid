package cn.com.gree.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class User {
    /**  id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 用户名 */
    @Column(length = 30)
    private String userName;

    /** 登录密码 */
    @Column(length = 30)
    private String loginPassWord;

    /** 设置密码 */
    @Column(length = 30)
    private String optionPassWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginPassWord() {
        return loginPassWord;
    }

    public void setLoginPassWord(String loginPassWord) {
        this.loginPassWord = loginPassWord;
    }

    public String getOptionPassWord() {
        return optionPassWord;
    }

    public void setOptionPassWord(String optionPassWord) {
        this.optionPassWord = optionPassWord;
    }
}
