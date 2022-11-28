package min.project.tms.daodemo;

import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    public void addAccount(int abc){

        System.out.println("I'm doing my job - add account.");
        int a = 10/0;
    }
    public boolean createAccount(){
        System.out.println("I'm doing my job - create account.");
        return true;
    }
}
