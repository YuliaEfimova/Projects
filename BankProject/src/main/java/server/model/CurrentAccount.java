//package server.model;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "current_account")
//public class CurrentAccount {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//
//    private String name;
//    private String sername;
//    private Integer currentAccount;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User author;
//
//    public CurrentAccount(String name, String sername, Integer currentAccount, User user) {
//        this.author = user;
//        this.name = name;
//        this.sername = sername;
//        this.currentAccount = currentAccount;
//    }
//
//    public CurrentAccount() {
//    }
//
//    public String getAuthorName(){
//        return author != null ? author.getUsername() : "<none>";
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getSername() {
//        return sername;
//    }
//
//    public void setSername(String sername) {
//        this.sername = sername;
//    }
//
//    public Integer getCurrentAccount() {
//        return currentAccount;
//    }
//
//    public void setCurrentAccount(Integer currentAccount) {
//        this.currentAccount = currentAccount;
//    }
//}