package microservice.example.account;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="operation-service")
@RibbonClient(name="operation-service")
public interface OperationServiceProxy {
  @GetMapping("/operations/{accountNumber}/history")
  public List<AccountBean> getHistory(@PathVariable("accountNumber") long accountNumber);
}
