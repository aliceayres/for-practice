package pers.ayres.daily.ch3;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ye on 2019/11/19.
 */

public class LimitRate {
    public static ConcurrentHashMap<String, RateLimiter> limiters = new ConcurrentHashMap<>();

    public static void createApplicationiLimiter(String appId,double qps){
        if (limiters.contains(appId)){
            limiters.get(appId).setRate(qps);
        }else{
            RateLimiter rl = RateLimiter.create(qps);
            limiters.putIfAbsent(appId,rl);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger doCnt = new AtomicInteger(0);
        AtomicInteger refuseCnt = new AtomicInteger(0);
        int total = 50000;
        CountDownLatch latch = new CountDownLatch(total);
        String appId = "dcae";
        createApplicationiLimiter(appId,500);
        for (int i = 0;i < total; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (limiters.get(appId).tryAcquire(10, TimeUnit.MILLISECONDS)){
                        System.out.println("do");
                        doCnt.getAndIncrement();
                    }else{
                        System.out.println("refuse");
                        refuseCnt.getAndIncrement();
                    }
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.out.println(doCnt.get());
        System.out.println(refuseCnt.get());
        System.out.println(doCnt.get()/total);
        assert(doCnt.get()+refuseCnt.get() == total);
    }

}
