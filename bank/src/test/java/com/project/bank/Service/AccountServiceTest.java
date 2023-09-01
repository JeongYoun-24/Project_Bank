package com.project.bank.Service;

import com.project.bank.BankApplication;
import com.project.bank.entity.Account;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Random;

@Log4j2
@AutoConfigureMockMvc
@SpringBootTest(classes = BankApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class AccountServiceTest {


    
    @Test
    @DisplayName(value = "계좌생성")
    public void AccountTest(){
        Random ran = new Random();

//       int account = (int)Math.random()*100+1;

        int account1= (int)(Math.random() * 0)-1;
        int account2= (int)(Math.random() * 10)-1;
        int account3= (int)(Math.random() * 10)-1;
        int account4= (int)(Math.random() * 10)-1;
        int account5= (int)(Math.random() * 10)-1;
        int account6= (int)(Math.random() * 10)-1;
        int account7= (int)(Math.random() * 10)-1;
        int account8= (int)(Math.random() * 10)-1;
        int account9= (int)(Math.random() * 10)-1;

        int account10= (int)(Math.random() * 2000000)+1000000;


        String Acount = (Integer.toString(account2));
        String Acount2 = (Integer.toString(account3+account4+account5+account6+account7+account8+account9));

        String acount002 = "3333";
        String m = "-";

        String AAA = acount002+m+0+Acount+m+account10;

        log.info("계좌번호"+AAA);
        log.info("계좌번호"+account2+account3+account4+account5+account6+account7+account8);


        log.info("계좌생성"+acount002+m+0+Acount+m+account10);

    }
    
    

}
