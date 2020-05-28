import com.hf.payment.service.PaymentDisplay;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class PaymentTest {
    public static void main(String args[]) throws Exception{
        // 1.在指定的时间后，每隔指定的时间，重复运行定时器任务
        Timer timer = new Timer();
        PaymentDisplay task1 = new PaymentDisplay();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        while(true){
            Future<String> taskFuture = executorService.submit(task1);
            final String result = taskFuture.get();
            timer.schedule(new TimerTask(){
                public void run() {
                    System.out.println(result);
                }
            }, new Date(), 60000);//第一个值是程序启动时间，第二个值为周期时长
        }
    }
}
