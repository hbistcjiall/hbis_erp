/**
 * FileName: testService
 * Author:   zhangb
 * Date:     2019/3/27 17:28
 */
package cn.hbis.erp.modular.system.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class TestService {
    @Async
    public void sendSms(){
        System.out.println("####sendSms####   2");
        IntStream.range(0, 5).forEach(d -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("####sendSms####   3");
    }
}
